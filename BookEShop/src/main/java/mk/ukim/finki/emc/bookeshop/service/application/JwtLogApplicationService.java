package mk.ukim.finki.emc.bookeshop.service.application;

import mk.ukim.finki.emc.bookeshop.dto.DisplayJwtLogDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JwtLogApplicationService {

    List<DisplayJwtLogDto> getAllLogs();

    Optional<DisplayJwtLogDto> logToken(String username, String token, Instant issuedAt, Instant expiresAt);

}
