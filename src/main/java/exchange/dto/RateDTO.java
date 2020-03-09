package exchange.dto;

import exchange.db.entity.ExchangeRate;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class RateDTO {

    @NotNull
    private CurrencyCode from;

    @DecimalMin(value = "0.0", inclusive = false)
    private double rate;

    @NotNull
    private CurrencyCode to;

    public static RateDTO fromExchangeRate(ExchangeRate exchangeRate) {
        return RateDTO.builder()
                .from(exchangeRate.getFrom().getCode())
                .rate(exchangeRate.getRate())
                .to(exchangeRate.getTo().getCode())
                .build();
    }
}
