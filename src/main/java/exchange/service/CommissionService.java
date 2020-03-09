package exchange.service;

import exchange.db.entity.Commission;
import exchange.db.entity.ExchangeRate;
import exchange.db.repository.CommissionRepository;
import exchange.dto.CommissionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommissionService {

    private final ExchangeRateService exchangeRateService;
    private final CommissionRepository commissionRepository;

    public List<CommissionDTO> getCommissions() {
        List<Commission> commissions = commissionRepository.findAll();
        return commissions.stream()
                .map(CommissionDTO::fromCommission)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommissionDTO setCommission(CommissionDTO commissionDTO) {
        ExchangeRate rate = exchangeRateService.findByCurrencyPair(commissionDTO.getFrom(), commissionDTO.getTo());

        Commission commission = commissionRepository.findFirstByRate(rate).orElse(
                Commission.builder()
                .rate(rate)
                .percent(commissionDTO.getCommissionPt())
                .build());
        commission.setPercent(commissionDTO.getCommissionPt());
        return CommissionDTO.fromCommission(commissionRepository.save(commission));
    }
}

