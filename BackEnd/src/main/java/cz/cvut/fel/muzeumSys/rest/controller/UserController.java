package cz.cvut.fel.muzeumSys.rest.controller;
//
import cz.cvut.fel.muzeumSys.config.security.JwtUtil;
import cz.cvut.fel.muzeumSys.dto.Record.AdminDto;
import cz.cvut.fel.muzeumSys.dto.Record.AuthRequestDto;
import cz.cvut.fel.muzeumSys.dto.Record.EmployeeDto;
import cz.cvut.fel.muzeumSys.dto.Record.UserDto;
import cz.cvut.fel.muzeumSys.model.User;
import cz.cvut.fel.muzeumSys.repository.UserRepository;
import cz.cvut.fel.muzeumSys.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @PostMapping(value="/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }


    @PostMapping(value ="/registerEmployee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Map<String, String>> registerEmployee(@RequestBody EmployeeDto userDto) {
        String email = userDto.email();
        String password = userDto.password();

        userService.registerEmployee(userDto);

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        final UserDetails userDetails = userService.loadUserByUsername(email);

        String token = jwtUtil.generateToken(userDetails);


        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }


    @PostMapping(value ="/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Map<String, String>> register(@RequestBody AdminDto userDto) {
        String email = userDto.email();
        String password = userDto.password();

        userService.registerAdmin(userDto);

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        final UserDetails userDetails = userService.loadUserByUsername(email);

        String token = jwtUtil.generateToken(userDetails);


        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value ="/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Map<String, String>> login(@RequestBody AuthRequestDto authRequestDto) {
        String email = authRequestDto.email();
        String password = authRequestDto.password();
        User user = userService.login(email, password);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        final UserDetails userDetails = userService.loadUserByUsername(email);

        String token = jwtUtil.generateToken(userDetails);
        String role = user.getRole().toString();

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("role", role);

        return ResponseEntity.ok(response);
    }
   }


//
//    @Override
//    public ResponseEntity<AuthResponse> refreshToken(RefreshTokenRequestDto request) {
//        String email;
//        try {
//            email = jwtUtil.extractUsername(request.getRefreshToken());
//        } catch (Exception e) {
//            throw new RuntimeException("Invalid refresh token.");
//        }
//
//        UserDetails userDetails = userService.loadUserByUsername(email);
//
//        Claims claims = jwtUtil.getClaimsFromRefreshToken(request.getRefreshToken());
//
//        User user = userRepository.findByEmail(email).orElseThrow(()-> new UserException("User not found.", HttpStatus.NOT_FOUND));
//
//        if (!"refresh".equals(claims.get("type"))) {
//            throw new RuntimeException("Invalid token type.");
//        }
//
//        if (!jwtUtil.validateToken(request.getRefreshToken(), userDetails)) {
//            throw new RuntimeException("Refresh token expired or invalid.");
//        }
//
//        String newAccessToken = jwtUtil.generateToken(userDetails);
//        String newRefreshToken = jwtUtil.generateRefreshToken(userDetails);
//
//        return ResponseEntity.ok(new AuthResponse(newAccessToken, newRefreshToken, user.getRole().toString(), user.getFirstName(), user.getLastName()));
//