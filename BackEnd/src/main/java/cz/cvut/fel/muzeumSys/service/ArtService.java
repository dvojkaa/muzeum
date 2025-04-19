package cz.cvut.fel.muzeumSys.service;

import cz.cvut.fel.muzeumSys.dto.Record.ArtDto;
import cz.cvut.fel.muzeumSys.mapper.ArtMapper;
import cz.cvut.fel.muzeumSys.model.Art;
import cz.cvut.fel.muzeumSys.model.User;
import cz.cvut.fel.muzeumSys.repository.ArtRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ArtService {

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


    public List<Art> emergency(List<ArtDto> artList) {
        User currentUser = userService.getCurrentUser(); // metoda, která vrátí přihlášeného uživatele

        List<Art> affected = new ArrayList<>();

        for (ArtDto artDto : artList) {
            Art art = artRepository.findById(artDto.id()).orElseThrow();
            emergencyService.create(art, currentUser, null);
            affected.add(art);
        }

        return affected;
    }



//    public List getArts(ArtDto artDto) {
//        return artRepository.findBy(artDto);
//    }
}
