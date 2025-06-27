package mk.ukim.finki.emc.bookeshop.service.domain;

import mk.ukim.finki.emc.bookeshop.model.domain.JwtLog;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JwtLogService {

    List<JwtLog> getAllLogs();

    Optional<JwtLog> logToken(String username, String token, Instant issuedAt, Instant expiresAt);

}
