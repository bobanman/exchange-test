package exchange.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculationUtils {

    public static int SCALE = 2;

    private CalculationUtils() {

    }

    public static double reverseRate(double rate) {
        BigDecimal reverseRate = BigDecimal.valueOf(1.0d)
                .divide(
                        BigDecimal.valueOf(rate),
                        SCALE,
                        RoundingMode.HALF_DOWN);
        return reverseRate.doubleValue();
    }

    public static double calculateExchangeAmount(double amount, double rate, double commission) {
        BigDecimal result = BigDecimal.valueOf(amount).multiply(BigDecimal.valueOf(rate));
        result = result.subtract(result.multiply(BigDecimal.valueOf(commission/100.0d)));
        return result.setScale(SCALE, RoundingMode.HALF_DOWN).doubleValue();
    }
}
