package exchange.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class ExchangeRequestDTO {

    private double amountFrom;

    private double amountTo;

    @NotNull
    private CurrencyCode currencyFrom;

    @NotNull
    private CurrencyCode currencyTo;

    @NotNull
    private OperationType operationType;

    private double commissionAmount;
}
