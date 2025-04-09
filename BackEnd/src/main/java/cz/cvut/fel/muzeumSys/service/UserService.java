
package cz.cvut.fel.muzeumSys.service;


import cz.cvut.fel.muzeumSys.dto.Record.AdminDto;
import cz.cvut.fel.muzeumSys.dto.Record.EmployeeDto;
import cz.cvut.fel.muzeumSys.dto.Record.UserDto;
import cz.cvut.fel.muzeumSys.mapper.AdminMapper;
import cz.cvut.fel.muzeumSys.mapper.EmployeeMapper;
import cz.cvut.fel.muzeumSys.model.Admin;
import cz.cvut.fel.muzeumSys.model.Employee;
import cz.cvut.fel.muzeumSys.model.User;
import cz.cvut.fel.muzeumSys.model.enums.Role;
import cz.cvut.fel.muzeumSys.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
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
//        private final UserMapper userMapper;
        private final AdminMapper adminMapper;
        private final EmployeeMapper employeeMapper;

        private final PasswordEncoder passwordEncoder;



        public void registerAdmin(AdminDto userDto) {
            Admin user = adminMapper.toEntity(userDto);

            user.setRole(Role.ROLE_ADMIN);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }


        public void registerEmployee(EmployeeDto userDto) {
            Employee user = employeeMapper.toEntity(userDto);

            user.setRole(Role.ROLE_EMPLOYEE);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
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
    }