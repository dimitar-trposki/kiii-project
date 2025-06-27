package mk.ukim.finki.emc.bookeshop.repository;

import mk.ukim.finki.emc.bookeshop.model.domain.JwtLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtLogRepository extends JpaRepository<JwtLog, Long> {
}
