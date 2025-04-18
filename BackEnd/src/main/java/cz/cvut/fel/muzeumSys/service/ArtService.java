package cz.cvut.fel.muzeumSys.service;

import cz.cvut.fel.muzeumSys.dto.Record.ArtDto;
import cz.cvut.fel.muzeumSys.mapper.ArtMapper;
import cz.cvut.fel.muzeumSys.model.Art;
import cz.cvut.fel.muzeumSys.repository.ArtRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class ArtService {

    private final ArtRepository artRepository;
    private final ArtMapper artMapper;

    public ArtService(ArtRepository artRepository, ArtMapper artMapper) {
        this.artRepository = artRepository;
        this.artMapper = artMapper;
    }


    public Art createArt(ArtDto artDto) {
        Art art = artMapper.toEntity(artDto);

        artRepository.save(art);

        return art;
    }

    public Optional<Art> getArtById(Long id) {
        return artRepository.findById(id);
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
        return null;
    }


//    public List getArts(ArtDto artDto) {
//        return artRepository.findBy(artDto);
//    }
}
