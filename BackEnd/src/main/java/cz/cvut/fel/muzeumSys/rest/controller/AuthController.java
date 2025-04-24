package cz.cvut.fel.muzeumSys.rest.controller;

import cz.cvut.fel.muzeumSys.config.security.JwtUtil;
import cz.cvut.fel.muzeumSys.repository.UserRepository;
import cz.cvut.fel.muzeumSys.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "${FRONTEND_URL}", allowCredentials = "true")
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {


    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;
    @PostMapping(value = "/validateToken", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> validateToken(@RequestHeader("Authorization") String authorizationHeader) {
        Map<String, String> response = new HashMap<>();
        try {
            // Odstranění prefixu "Bearer " z tokenu
            String token = authorizationHeader.substring(7);  // `Authorization: Bearer <token>`

            // Ověření platnosti tokenu
            if (!jwtUtil.validateToken(token, userService.loadUserByUsername(jwtUtil.extractUsername(token)))) {
                response.put("Error", "Token is not valid");
                return ResponseEntity.status(401).body(response);
            }

            response.put("role", jwtUtil.extractRole(token).replace("[{authority=", "").replace("}]", ""));
//            response.put("role", jwtUtil.extractRole(token));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("Error", "Token is not valid");
            return ResponseEntity.status(400).body(response);
        }
    }

//    @PostMapping(value = "/validateToken", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Map<String, String>> validateToken(@RequestBody String token) {
//        Map<String, String> response = new HashMap<>();
//        try {
//
//
//
//            if (!jwtUtil.validateToken(token, userService.loadUserByUsername(jwtUtil.extractUsername(token)))) {
//                response.put("Error", "Token is not valid");
//
//                return ResponseEntity.status(401).body(response);
//            }
//
//            response.put("role",jwtUtil.extractRole(token));
//
//
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            response.put("Error", "Token is not valid");
//
//            return ResponseEntity.status(400).body(response);
//
//        }
//    }
}
