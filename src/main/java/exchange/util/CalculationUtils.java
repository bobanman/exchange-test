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
                        4,
                        RoundingMode.HALF_UP);
        return reverseRate.doubleValue();
    }

    public static double calculateAmountTo(double amount, double rate, double commission) {
        double result = amount * rate;
        result = result - result * (commission / 100d);
        return round(result);
    }

    public static double calculateAmountFrom(double amount, double rate, double commission) {
        double result = 100d * amount / (100d - commission);
        result = result / rate;
        return round(result);
    }

    private static double round(double value) {
        return round(value, RoundingMode.HALF_UP);
    }

    private static double round(double value, RoundingMode rm) {
        return BigDecimal.valueOf(value).setScale(SCALE, rm).doubleValue();
    }

}
