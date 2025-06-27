package mk.ukim.finki.emc.bookeshop.dto;

import mk.ukim.finki.emc.bookeshop.model.domain.JwtLog;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public record DisplayJwtLogDto(
        Long id,
        String token,
        String username,
        Instant issuedAt,
        Instant expiredAt) {

    public JwtLog toJwtLogs() {
        return new JwtLog(token, username, issuedAt, expiredAt);
    }

    public static DisplayJwtLogDto from(JwtLog jwtLog) {
        return new DisplayJwtLogDto(jwtLog.getId(), jwtLog.getToken(), jwtLog.getUsername(), jwtLog.getIssuedAt(), jwtLog.getExpiresAt());
    }

    public static List<DisplayJwtLogDto> from(List<JwtLog> jwtLogs) {
        return jwtLogs.stream()
                .map(l -> new DisplayJwtLogDto(l.getId(), l.getToken(), l.getUsername(), l.getIssuedAt(), l.getExpiresAt()))
                .toList();
    }

}
