package cz.cvut.fel.muzeumSys.service;


import cz.cvut.fel.muzeumSys.dto.Record.UserDto;
import cz.cvut.fel.muzeumSys.mapper.AdminMapper;
import cz.cvut.fel.muzeumSys.mapper.EmployeeMapper;
import cz.cvut.fel.muzeumSys.model.Admin;
import cz.cvut.fel.muzeumSys.model.Employee;
import cz.cvut.fel.muzeumSys.model.User;
import cz.cvut.fel.muzeumSys.model.enums.Role;
import cz.cvut.fel.muzeumSys.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final AdminMapper adminMapper;
    private final EmployeeMapper employeeMapper;

    private final PasswordEncoder passwordEncoder;
    private final EmergencyRecordService emergencyRecordService;


    public void registerAdmin(UserDto userDto) {
        Admin user = adminMapper.toEntity(adminMapper.toAdminDto(userDto));

        user.setRole(Role.ROLE_ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public void registerEmployee(UserDto userDto) {
        Employee user = employeeMapper.toEntity(employeeMapper.toEmployeeDto(userDto));

        user.setRole(Role.ROLE_EMPLOYEE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.findByEmail(username).orElseThrow();
    }


    public User login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));

        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }

        throw new BadCredentialsException("Wrong password");
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

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Uživatel s ID " + userId + " nebyl nalezen."));
    }


    public User updateUser(UserDto userDto) {
        if (userDto.id() == null) {
            throw new IllegalArgumentException("ID nesmí být null při aktualizaci Zaměstnance.");
        }
        User existingUser = userRepository.findById(userDto.id())
                .orElseThrow(() -> new EntityNotFoundException("Zaměstnance s ID " + userDto.id() + " nebylo nalezeno."));


        if (existingUser.getRole() == Role.ROLE_ADMIN) {
            adminMapper.updateAdminFromDto(userDto, existingUser);
        } else {
            employeeMapper.updateEmployeeFromDto(userDto, existingUser);
        }

        if (userDto.password() != null && !userDto.password().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(userDto.password()));
        }

        return userRepository.save(existingUser);
    }

    public User deleteUser(UserDto userDto) {
        if (userDto.id() == null) {
            throw new IllegalArgumentException("ID nesmí být null při mazání Zaměstnance.");
        }

        User user = userRepository.findById(userDto.id())
                .orElseThrow(() -> new EntityNotFoundException("Zamestnanec s ID " + userDto.id() + " nebylo nalezeno."));

        emergencyRecordService.clearUserReferences(userDto.id());

        userRepository.delete(user);

        return user;
    }
}