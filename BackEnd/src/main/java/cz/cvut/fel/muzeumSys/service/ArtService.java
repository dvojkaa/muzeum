package cz.cvut.fel.muzeumSys.service;

import cz.cvut.fel.muzeumSys.dto.ArtDto;
import cz.cvut.fel.muzeumSys.mapper.ArtMapper;
import cz.cvut.fel.muzeumSys.repository.ArtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
//@RequiredArgsConstructor
public class ArtService {


    public ResponseEntity<ArtDto> createArt(ArtDto artDto) {
       return null;
    }


}
