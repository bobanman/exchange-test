package exchange.db.repository;

import exchange.db.entity.Currency;
import exchange.db.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    ExchangeRate findByFromEqualsAndToEquals(Currency from, Currency to);

    @Modifying
    @Query("update ExchangeRate r set r.rate = :rate  where r.from = :from and r.to = :to")
    int setExchangeRate(@Param("from") Currency from, @Param("to") Currency to, @Param("rate") double rate);

}
