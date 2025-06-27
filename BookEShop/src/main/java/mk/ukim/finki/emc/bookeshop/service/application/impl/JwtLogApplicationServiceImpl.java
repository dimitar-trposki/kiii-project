package mk.ukim.finki.emc.bookeshop.service.application.impl;

import mk.ukim.finki.emc.bookeshop.dto.DisplayJwtLogDto;
import mk.ukim.finki.emc.bookeshop.service.application.JwtLogApplicationService;
import mk.ukim.finki.emc.bookeshop.service.domain.JwtLogService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JwtLogApplicationServiceImpl implements JwtLogApplicationService {

    private final JwtLogService jwtLogService;

    public JwtLogApplicationServiceImpl(JwtLogService jwtLogService) {
        this.jwtLogService = jwtLogService;
    }

    @Override
    public List<DisplayJwtLogDto> getAllLogs() {
        return DisplayJwtLogDto.from(jwtLogService.getAllLogs());
    }

    @Override
    public Optional<DisplayJwtLogDto> logToken(String username, String token, Instant issuedAt, Instant expiresAt) {
        return jwtLogService.logToken(username, token, issuedAt, expiresAt).map(DisplayJwtLogDto::from);
    }

}
