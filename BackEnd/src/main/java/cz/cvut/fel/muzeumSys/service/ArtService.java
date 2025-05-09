package cz.cvut.fel.muzeumSys.service;

import cz.cvut.fel.muzeumSys.dto.Record.ArtDto;
import cz.cvut.fel.muzeumSys.dto.Record.EmergencyRecordDto;
import cz.cvut.fel.muzeumSys.mapper.ArtMapper;
import cz.cvut.fel.muzeumSys.model.Art;
import cz.cvut.fel.muzeumSys.model.EmergencyRecord;
import cz.cvut.fel.muzeumSys.model.User;
import cz.cvut.fel.muzeumSys.repository.ArtRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ArtService {

    private static final String UPLOAD_DIR = "files/arts/";


    private final ArtRepository artRepository;
    private final ArtMapper artMapper;
    private final UserService userService;
    private final EmergencyRecordService emergencyService;


    public ArtService(ArtRepository artRepository, ArtMapper artMapper, UserService userService, EmergencyRecordService emergencyRecordService) {
        this.artRepository = artRepository;
        this.artMapper = artMapper;
        this.userService = userService;
        this.emergencyService = emergencyRecordService;
    }


    public Art createArt(ArtDto artDto) {
        Art art = artMapper.toEntity(artDto);

        artRepository.save(art);

        return art;
    }


    public Art getArtById(Long id) {
        return artRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dílo s ID " + id + " nebyl nalezen."));

    }


    public List<Art> getArts() {
        return artRepository.findAll();
    }


    public List<Art> editArt(List<ArtDto> artList) {
        return artList.stream()
                .map(artDto -> {
                    Art existingArt = artRepository.findById(artDto.id())
                            .orElseThrow(() -> new RuntimeException("Art not found with id: " + artDto.id()));

                    artMapper.updateArtFromDto(artDto, existingArt);

                    return artRepository.save(existingArt);
                })
                .toList();
    }


    public List<Art> emergency(List<ArtDto> artList, String note) {
        User currentUser = userService.getCurrentUser();

        List<Art> affected = new ArrayList<>();

        for (ArtDto artDto : artList) {
            Art art = artRepository.findById(artDto.id()).orElseThrow();
            emergencyService.create(art, currentUser, note);
            affected.add(art);
        }

        return affected;
    }

    public EmergencyRecord updateRecord(EmergencyRecordDto emergencyRecordDto) {
        if (emergencyRecordDto.id() == null) {
            throw new IllegalArgumentException("ID nesmí být null při aktualizaci záznamu.");
        }

        Art art = artRepository.findById(emergencyRecordDto.artId())
                .orElseThrow(() -> new EntityNotFoundException("Dílo s ID " + emergencyRecordDto.artId() + " nebylo nalezeno."));

        User user = userService.getUserById(emergencyRecordDto.userId());

        LocalDateTime time = emergencyRecordDto.timestamp();

        String note = emergencyRecordDto.note();


        return emergencyService.update(emergencyRecordDto.id(), art, user, time, note);
    }


    public Art updateArt(ArtDto artDto) {
        if (artDto.id() == null) {
            throw new IllegalArgumentException("ID nesmí být null při aktualizaci díla.");
        }

        Art existingArt = artRepository.findById(artDto.id())
                .orElseThrow(() -> new EntityNotFoundException("Dílo s ID " + artDto.id() + " nebylo nalezeno."));

        artMapper.updateArtFromDto(artDto, existingArt);

        return artRepository.save(existingArt);
    }

    @Transactional
    public Art deleteArt(ArtDto artDto) {
        if (artDto.id() == null) {
            throw new IllegalArgumentException("ID nesmí být null při mazání díla.");
        }

        Art art = artRepository.findById(artDto.id())
                .orElseThrow(() -> new EntityNotFoundException("Dílo s ID " + artDto.id() + " nebylo nalezeno."));


        emergencyService.deleteAllByArtId(art.getId()); // smažeme napojené emergency záznamy

        artRepository.delete(art);

        return art;
    }
//
//    public Art deleteArt(ArtDto artDto) {
//        if (artDto.id() == null) {
//            throw new IllegalArgumentException("ID nesmí být null při mazání díla.");
//        }
//
//        Art art = artRepository.findById(artDto.id())
//                .orElseThrow(() -> new EntityNotFoundException("Dílo s ID " + artDto.id() + " nebylo nalezeno."));
//
//        artRepository.delete(art);
//
//        return art;
//    }


    public void importArtList(List<ArtDto> artDtos) {
        for (ArtDto dto : artDtos) {
            try {
                Art art = artMapper.toEntity(dto);
                artRepository.save(art);
            } catch (Exception e) {
                System.err.println("Chyba při importu díla: " + dto.name() + " – " + e.getMessage());
            }
        }
    }


    public void processAndSaveArtPhoto(MultipartFile file, Long artId) {
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Soubor není přiložen.");
        }

        Optional<Art> optionalArt = artRepository.findById(artId);
        if (optionalArt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dílo nebylo nalezeno.");
        }

        Art art = optionalArt.get();

        try {
            String fileExtension = getFileExtension(file.getOriginalFilename());
            String fileName = art.getName() + art.getId() + "." + fileExtension;
            Path filePath = Paths.get(UPLOAD_DIR + fileName);

            // Vytvoření složky, pokud neexistuje
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());

            // Aktualizace cesty k souboru
            art.setImgPath(fileName);
            artRepository.save(art);

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Chyba při ukládání souboru.");
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName == null) {
            return "";
        }
        int index = fileName.lastIndexOf('.');
        return (index == -1) ? "" : fileName.substring(index + 1);
    }


    public Resource loadFile(String filename) {
        try {
            Path file = Paths.get(UPLOAD_DIR + filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("File not found: " + filename);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while loading file: " + filename, e);
        }
    }
}
