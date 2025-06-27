package mk.ukim.finki.emc.bookeshop.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Entity
public class JwtLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String token;
    private Instant issuedAt;
    private Instant expiresAt;

    public JwtLog() {
    }

    public JwtLog(String username, String token, Instant issuedAt, Instant expiresAt) {
        this.username = username;
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public Instant getIssuedAt() {
//        return issuedAt;
//    }
//
//    public void setIssuedAt(Instant issuedAt) {
//        this.issuedAt = issuedAt;
//    }
//
//    public Instant getExpiresAt() {
//        return expiresAt;
//    }
//
//    public void setExpiresAt(Instant expiresAt) {
//        this.expiresAt = expiresAt;
//    }

}
