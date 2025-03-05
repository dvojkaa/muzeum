@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated();
    }
}


    @PreAuthorize("hasRole('ADMIN')")
    public void someAdminMethod() {
        // pouze pro administrátory
    }

    @PreAuthorize("hasRole('USER') and #userId == principal.id")
    public void updateUser(Long userId) {
        // Metoda pro aktualizaci uživatele, pokud je uživatel vlastníkem
    }

    @PreAuthorize("isAuthenticated()")
    public void someAuthenticatedMethod() {
        // Pouze pro přihlášené uživatele

}
