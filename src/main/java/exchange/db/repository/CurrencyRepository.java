package exchange.db.repository;

import exchange.db.entity.Currency;
import exchange.dto.CurrencyCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    Currency findByCode(CurrencyCode code);
}
