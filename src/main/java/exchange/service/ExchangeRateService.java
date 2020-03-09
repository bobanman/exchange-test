package exchange.service;

import exchange.db.entity.Currency;
import exchange.db.entity.ExchangeRate;
import exchange.db.repository.CurrencyRepository;
import exchange.db.repository.ExchangeRateRepository;
import exchange.dto.CurrencyCode;
import exchange.dto.RateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import exchange.util.CalculationUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final CurrencyRepository currencyRepository;

    public List<RateDTO> getExchangeRates() {
        List<ExchangeRate> rates = exchangeRateRepository.findAll();
        return rates.stream()
                .map(RateDTO::fromExchangeRate)
                .collect(Collectors.toList());
    }

    @Transactional
    public void setExchangeRate(RateDTO exchangeRate) {
        Currency currencyFrom = currencyRepository.findByCode(exchangeRate.getFrom());
        Currency currencyTo = currencyRepository.findByCode(exchangeRate.getTo());
        double reverseRate = CalculationUtils.reverseRate(exchangeRate.getRate());

        exchangeRateRepository.setExchangeRate(currencyFrom, currencyTo, exchangeRate.getRate());
        exchangeRateRepository.setExchangeRate(currencyTo, currencyFrom, reverseRate);
    }

    public ExchangeRate findByCurrencyPair(CurrencyCode from, CurrencyCode to) {
        return exchangeRateRepository.findByFromEqualsAndToEquals(
                currencyRepository.findByCode(from),
                currencyRepository.findByCode(to));
    }
}
