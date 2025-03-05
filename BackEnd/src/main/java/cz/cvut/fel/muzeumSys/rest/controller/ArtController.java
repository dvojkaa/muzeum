package cz.cvut.fel.muzeumSys.rest.controller;

import cz.cvut.fel.muzeumSys.dto.Record.ArtDto;
import cz.cvut.fel.muzeumSys.model.Art;
import cz.cvut.fel.muzeumSys.service.ArtService;
import jakarta.persistence.Enumerated;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/art")
public class ArtController {

    private final ArtService artService;

    public ArtController(ArtService artService) {
        this.artService = artService;
    }

    @PreAuthorize()
    @PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Art> createArt(@RequestBody ArtDto artDto) {
        return ResponseEntity.ok(artService.createArt(artDto));
    }

    @GetMapping
    public String hello() {
        return "Hello";
    }


}
