package cz.cvut.fel.muzeumSys.service;


import cz.cvut.fel.muzeumSys.config.security.JwtService;
import cz.cvut.fel.muzeumSys.dto.Record.UserDto;
import cz.cvut.fel.muzeumSys.mapper.UserMapper;
//import cz.cvut.fel.muzeumSys.model.Employee;
import cz.cvut.fel.muzeumSys.model.User;
import cz.cvut.fel.muzeumSys.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private JwtService jwtService;

    AuthenticationManager authManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

//
//    public void registerParent(RegisterRequest request) {
//
//        if (userRepository.findByEmail(request.email()).isPresent()) {
//            throw new UserException("User with email: " + request.email() + " already exists.", HttpStatus.CONFLICT);
//        }
//
//        Parent parent = new Parent(
//                request.firstName(),
//                request.lastName(),
//                request.email(),
//                request.phoneNumber(),
//                passwordEncoder.encode(request.password())
//        );
//
//        try {
//            userRepository.save(parent);
//        } catch (Exception e) {
//            throw new UserException(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    public void registerTrainer(RegisterRequest request) {
//        if (userRepository.findByEmail(request.email()).isPresent()) {
//            throw new UserException("User with email: " + request.email() + " already exists.", HttpStatus.CONFLICT);
//        }
//
//        Trainer trainer = new Trainer(
//                request.firstName(),
//                request.lastName(),
//                request.email(),
//                request.phoneNumber(),
//                passwordEncoder.encode(request.password())
//        );
//
//        try {
//            userRepository.save(trainer);
//        } catch (Exception e) {
//            throw new UserException(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

    public String verify(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if (authentication.isAuthenticated()) {

            return jwtService.generateToken(user.getEmail())  ;
        } else {
            return "fail";
        }
    }

    public User register(UserDto userDto) {
        User user = userMapper.toEntity(userDto);

        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        List<GrantedAuthority> authorities = List.of(() -> user.getRole().toString());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
