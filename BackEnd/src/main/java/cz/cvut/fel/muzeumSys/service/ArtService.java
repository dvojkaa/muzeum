//package cz.cvut.fel.muzeumSys.service;
//
//import cz.cvut.fel.muzeumSys.dto.Record.ArtDto;
//import cz.cvut.fel.muzeumSys.mapper.ArtMapper;
//import cz.cvut.fel.muzeumSys.model.Art;
//import cz.cvut.fel.muzeumSys.repository.ArtRepository;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class ArtService {
//
//    private final ArtRepository artRepository;
//    private final ArtMapper artMapper;
//
//    public ArtService(ArtRepository artRepository, ArtMapper artMapper) {
//        this.artRepository = artRepository;
//        this.artMapper = artMapper;
//    }
//
//
//    public ResponseEntity<Art> createArt(ArtDto artDto) {
//        Art art = artMapper.toEntity(artDto);
//
//        artRepository.save(art);
//
//        return ResponseEntity.ok(art);
//    }
//
//
//}
