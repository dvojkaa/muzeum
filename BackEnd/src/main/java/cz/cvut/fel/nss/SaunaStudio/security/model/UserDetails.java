package cz.cvut.fel.nss.SaunaStudio.security.model;

import cz.cvut.fel.nss.SaunaStudio.model.Admin;
import cz.cvut.fel.nss.SaunaStudio.model.Customer;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Uložené uživatelské detaily pro autentizaci a autorizaci v rámci Spring Security.
 * <p>
 * Třída {@link UserDetails} rozšiřuje {@link org.springframework.security.core.userdetails.User} a poskytuje
 * dodatečné informace o uživatelském objektu typu {@link Customer} nebo {@link Admin}.
 * </p>
 */
public class UserDetails extends org.springframework.security.core.userdetails.User {

    /**
     * Zákazník spojený s tímto uživatelským detailem, pokud je to relevantní.
     */
    @Getter
    private final Customer customer;

    /**
     * Administrátor spojený s tímto uživatelským detailem, pokud je to relevantní.
     */
    @Getter
    private final Admin admin;

    private final Set<GrantedAuthority> authorities;

    /**
     * Konstruktor pro {@link UserDetails} s objektem zákazníka.
     *
     * @param customer    Zákazník spojený s tímto uživatelským detailem
     * @param authorities Seznam oprávnění spojených s tímto uživatelským detailem
     */
    public UserDetails(Customer customer, Collection<? extends GrantedAuthority> authorities) {
        super(customer.getUsername(), customer.getPassword(), authorities);
        this.customer = customer;
        this.admin = null;
        this.authorities = new HashSet<>();
        this.authorities.addAll(authorities);
    }

    /**
     * Konstruktor pro {@link UserDetails} s objektem administrátora.
     *
     * @param admin       Administrátor spojený s tímto uživatelským detailem
     * @param authorities Seznam oprávnění spojených s tímto uživatelským detailem
     */
    public UserDetails(Admin admin, Collection<? extends GrantedAuthority> authorities) {
        super(admin.getUsername(), admin.getPassword(), authorities);
        this.customer = null;
        this.admin = admin;
        this.authorities = new HashSet<>();
        this.authorities.addAll(authorities);
    }

    /**
     * Vrací kolekci oprávnění, která jsou spojená s tímto uživatelským detailem.
     *
     * @return Kolekce oprávnění
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableCollection(authorities);
    }

    /**
     * Vrací heslo uživatele.
     *
     * @return Heslo uživatele
     */
    @Override
    public String getPassword() {
        if (customer == null)
            return admin.getPassword();
        return customer.getPassword();
    }

    /**
     * Vrací uživatelské jméno.
     *
     * @return Uživatelské jméno
     */
    @Override
    public String getUsername() {
        if (customer == null)
            return admin.getUsername();
        return customer.getUsername();
    }

    /**
     * Indikuje, zda účet uživatele není expirovaný.
     *
     * @return {@code true}, pokud účet není expirovaný
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indikuje, zda účet uživatele není uzamčený.
     *
     * @return {@code true}, pokud účet není uzamčený
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indikuje, zda jsou přihlašovací údaje uživatele stále platné.
     *
     * @return {@code true}, pokud jsou přihlašovací údaje stále platné
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indikuje, zda je účet uživatele aktivní.
     *
     * @return {@code true}, pokud je účet aktivní
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
