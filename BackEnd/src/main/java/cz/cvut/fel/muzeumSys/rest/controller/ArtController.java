package cz.cvut.fel.muzeumSys.rest.controller;

import cz.cvut.fel.muzeumSys.dto.Record.ArtDto;
import cz.cvut.fel.muzeumSys.model.Art;
import cz.cvut.fel.muzeumSys.service.ArtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/art")
public class ArtController {

    private final ArtService artService;

    public ArtController(ArtService artService) {
        this.artService = artService;
    }

    //
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Art> createArt(@RequestBody ArtDto artDto) {
        return ResponseEntity.ok(artService.createArt(artDto));
    }

    @GetMapping
    public String hello() {
        return "Hello";
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Art> getArtById(@PathVariable Long id) {
        return ResponseEntity.ok(artService.getArtById(id));
    }


    @PostMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Art>> getArt() {
        return ResponseEntity.ok(artService.getArts());
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Art> updateArt(@RequestBody ArtDto artDto) {
        return ResponseEntity.ok(artService.updateArt(artDto));
    }

    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Art> deleteArt(@RequestBody ArtDto artDto) {
        return ResponseEntity.ok(artService.deleteArt(artDto));
    }

    @PostMapping(value = "/import", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> importArtList(@RequestBody List<ArtDto> artDtos) {
        artService.importArtList(artDtos);
        return ResponseEntity.ok("Import úspěšný");
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadArtPhoto(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("artId") Long artId) {

        try {
            artService.processAndSaveArtPhoto(file, artId);
            return ResponseEntity.ok("Soubor byl úspěšně nahrán a cesta uložena.");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Chyba při nahrávání souboru.");
        }
    }
}
