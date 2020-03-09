package exchange.db.repository;

import exchange.db.entity.Commission;
import exchange.db.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Long> {

    Optional<Commission> findFirstByRate(ExchangeRate rate);
}
