package br.edu.ifmg.produto.repository;

import br.edu.ifmg.produto.entities.PasswordRecover;
import ch.qos.logback.core.status.InfoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PasswordRecoveryRepository extends JpaRepository<PasswordRecover, Long> {
    @Query("Select obj FROM PasswordRecover obj " +
            "WHERE (obj.token = :token) " +
            "AND (obj.expiration > :now) "
    )
    public List<PasswordRecover> searchValidToken(String token, Instant now);
}
