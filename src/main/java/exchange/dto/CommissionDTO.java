package exchange.dto;

import exchange.db.entity.Commission;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CommissionDTO {
    @DecimalMax("100.00")
    @DecimalMin("0.00")
    private double commissionPt;

    @NotNull
    private CurrencyCode from;

    @NotNull
    private CurrencyCode to;

    public static CommissionDTO fromCommission(Commission commission) {
        return CommissionDTO.builder()
                .from(commission.getRate().getFrom().getCode())
                .commissionPt(commission.getPercent())
                .to(commission.getRate().getTo().getCode())
                .build();
    }
}

