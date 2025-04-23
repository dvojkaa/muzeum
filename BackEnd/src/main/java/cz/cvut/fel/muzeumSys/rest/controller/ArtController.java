package cz.cvut.fel.muzeumSys.rest.controller;

import cz.cvut.fel.muzeumSys.dto.Record.AdminDto;
import cz.cvut.fel.muzeumSys.dto.Record.ArtDto;
import cz.cvut.fel.muzeumSys.model.Admin;
import cz.cvut.fel.muzeumSys.model.Art;
import cz.cvut.fel.muzeumSys.service.ArtService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
@RestController
@RequestMapping("/art")
public class ArtController {

    private final ArtService artService;

    public ArtController(ArtService artService) {
        this.artService = artService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Art> createArt(@RequestBody ArtDto artDto) {
        return ResponseEntity.ok(artService.createArt(artDto));
    }

    @GetMapping
    public String hello() {
        return "Hello";
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Art> getArtById(@PathVariable Long id) {
        return ResponseEntity.ok(artService.getArtById(id));
    }


    @PostMapping(value="/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Art>> getArt() {
        return ResponseEntity.ok(artService.getArts());
    }

    @PostMapping(value="/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Art> updateArt(@RequestBody ArtDto artDto) {
        return ResponseEntity.ok(artService.updateArt(artDto));
    }

    @DeleteMapping(value="/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Art> deleteArt(@RequestBody ArtDto artDto) {
        return ResponseEntity.ok(artService.deleteArt(artDto));
    }

    @PostMapping(value = "/import", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> importArtList(@RequestBody List<ArtDto> artDtos) {
        artService.importArtList(artDtos);
        return ResponseEntity.ok("Import úspěšný");
    }

}
