package cz.cvut.fel.muzeumSys.rest.controller;
//
//import cz.cvut.fel.muzeumSys.config.security.JwtUtil;
//import cz.cvut.fel.muzeumSys.model.User;
//import cz.cvut.fel.muzeumSys.repository.UserRepository;
//import cz.cvut.fel.muzeumSys.service.UserService;
//import jdk.jshell.spi.ExecutionControl;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;

public class UserController {

//    private UserService userService;
//
//    private AuthenticationManager authenticationManager;
//
//    private JwtUtil jwtUtil;
//    private UserRepository userRepository;
//
//    @Override
//    public ResponseEntity<AuthResponse> registerParent(RegisterRequest request) {
//        String email = request.email();
//        String password = request.password();
//
//        userService.registerParent(request);
//
//        User user = userRepository.findByEmail(email).orElseThrow(()-> new ExecutionControl.UserException("User not found.", HttpStatus.NOT_FOUND));
//
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
//
//        final UserDetails userDetails = userService.loadUserByUsername(email);
//        final String accessToken = jwtUtil.generateToken(userDetails);
//        final String refreshToken = jwtUtil.generateRefreshToken(userDetails);
//
//        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken, "ROLE_PARENT", user.getFirstName(), user.getLastName()));
//    }
//
//    @Override
//    public ResponseEntity<AuthResponse> registerTrainer(RegisterRequest request) {
//        String email = request.email();
//        String password = request.password();
//
//        userService.registerTrainer(request);
//
//        User user = userRepository.findByEmail(email).orElseThrow(()-> new UserException("User not found.", HttpStatus.NOT_FOUND));
//
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
//
//        final UserDetails userDetails = userService.loadUserByUsername(email);
//        final String accessToken = jwtUtil.generateToken(userDetails);
//        final String refreshToken = jwtUtil.generateRefreshToken(userDetails);
//
//        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken, "ROLE_TRAINER", user.getFirstName(), user.getLastName()));
//    }
//
//    @Override
//    public ResponseEntity<AuthResponse> login(LoginRequest request) {
//        String email = request.email();
//        String password = request.password();
//        User user = userService.login(email, password);
//
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(email, password)
//        );
//
//        final UserDetails userDetails = userService.loadUserByUsername(email);
//        final String accessToken = jwtUtil.generateToken(userDetails);
//        final String refreshToken = jwtUtil.generateRefreshToken(userDetails);
//
//        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken, user.getRole().toString(), user.getFirstName(), user.getLastName()));
//    }
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
//    }
}
