package mk.ukim.finki.emc.bookeshop.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_LIBRARIAN;

    @Override
    public String getAuthority() {
        return name();
    }
}
