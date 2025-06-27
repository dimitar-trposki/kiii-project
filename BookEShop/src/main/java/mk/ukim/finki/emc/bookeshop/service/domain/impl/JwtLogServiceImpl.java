package mk.ukim.finki.emc.bookeshop.service.domain.impl;

import mk.ukim.finki.emc.bookeshop.model.domain.JwtLog;
import mk.ukim.finki.emc.bookeshop.repository.JwtLogRepository;
import mk.ukim.finki.emc.bookeshop.service.domain.JwtLogService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JwtLogServiceImpl implements JwtLogService {

    private final JwtLogRepository jwtLogsRepository;

    public JwtLogServiceImpl(JwtLogRepository jwtLogsRepository) {
        this.jwtLogsRepository = jwtLogsRepository;
    }

    @Override
    public List<JwtLog> getAllLogs() {
        return jwtLogsRepository.findAll();
    }

    @Override
    public Optional<JwtLog> logToken(String username, String token, Instant issuedAt, Instant expiresAt) {
        if (token != null && username != null) {
            return Optional.of(jwtLogsRepository.save(new JwtLog(username, token, issuedAt, expiresAt)));
        }
        return Optional.empty();
    }
}
