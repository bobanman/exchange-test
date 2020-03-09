package exchange.web.rest;

import exchange.dto.RateDTO;
import exchange.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ExchangeRatesController.URI)
@RequiredArgsConstructor
public class ExchangeRatesController {

    public static final String URI = "/api/exchange-rates";

    private final ExchangeRateService exchangeRateService;

    @GetMapping
    public List<RateDTO> getExchangeRates() {
        return exchangeRateService.getExchangeRates();
    }

    @PostMapping
    public RateDTO setExchangeRate(@RequestBody @Valid RateDTO exchangeRate) {
        exchangeRateService.setExchangeRate(exchangeRate);
        return exchangeRate;
    }

}
