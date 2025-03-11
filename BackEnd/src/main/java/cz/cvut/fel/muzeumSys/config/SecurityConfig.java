//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Configuration
//public class SecurityConfig extends WebSecrityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/user/**").hasRole("USER")
//                .anyRequest().authenticated();
//    }
//}
//
//
//    @PreAuthorize("hasRole('ADMIN')")
//    public void someAdminMethod() {
//        // pouze pro administrátory
//    }
//
//    @PreAuthorize("hasRole('USER') and #userId == principal.id")
//    public void updateUser(Long userId) {
//        // Metoda pro aktualizaci uživatele, pokud je uživatel vlastníkem
//    }
//
//    @PreAuthorize("isAuthenticated()")
//    public void someAuthenticatedMethod() {
//        // Pouze pro přihlášené uživatele
//
//}
