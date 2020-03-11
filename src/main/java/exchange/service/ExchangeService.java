package exchange.service;

import exchange.db.entity.Commission;
import exchange.db.entity.ExchangeRate;
import exchange.db.repository.CommissionRepository;
import exchange.dto.CurrencyCode;
import exchange.dto.ExchangeRequestDTO;
import exchange.dto.OperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import exchange.util.CalculationUtils;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRateService exchangeRateService;
    private final CommissionRepository commissionRepository;

    public ExchangeRequestDTO exchange(ExchangeRequestDTO exchangeRequestDTO) {
        CurrencyCode from = exchangeRequestDTO.getCurrencyFrom();
        CurrencyCode to = exchangeRequestDTO.getCurrencyTo();
        double amount;
        ExchangeRate rate = exchangeRateService.findByCurrencyPair(from, to);
        Commission commission = commissionRepository.findFirstByRate(rate).orElse(
                Commission.builder().
                        percent(0.0).
                        build());

        if(exchangeRequestDTO.getOperationType().equals(OperationType.GIVE)) {
            amount = CalculationUtils.calculateAmountTo(exchangeRequestDTO.getAmountFrom(), rate.getRate(), commission.getPercent());
            exchangeRequestDTO.setAmountTo(amount);
        } else {
            amount = CalculationUtils.calculateAmountFrom(exchangeRequestDTO.getAmountTo(), rate.getRate(), commission.getPercent());
            exchangeRequestDTO.setAmountFrom(amount);
        }

        exchangeRequestDTO.setCommissionAmount(commission.getPercent());
        return exchangeRequestDTO;
    }
}
