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
        CurrencyCode from, to;
        double amount;
        if(exchangeRequestDTO.getOperationType().equals(OperationType.GIVE)) {
            from = exchangeRequestDTO.getCurrencyFrom();
            to = exchangeRequestDTO.getCurrencyTo();
            amount = exchangeRequestDTO.getAmountFrom();
        } else {
            from = exchangeRequestDTO.getCurrencyTo();
            to = exchangeRequestDTO.getCurrencyFrom();
            amount = exchangeRequestDTO.getAmountTo();
        }
        ExchangeRate rate = exchangeRateService.findByCurrencyPair(from, to);
        Commission commission = commissionRepository.findFirstByRate(rate).orElse(
                Commission.builder().
                        percent(0.0).
                        build());

        amount = CalculationUtils.calculateExchangeAmount(amount, rate.getRate(), commission.getPercent());
        exchangeRequestDTO.setCommissionAmount(commission.getPercent());
        if(exchangeRequestDTO.getOperationType().equals(OperationType.GIVE)) {
            exchangeRequestDTO.setAmountTo(amount);
        } else {
            exchangeRequestDTO.setAmountFrom(amount);
        }

        return exchangeRequestDTO;
    }
}
