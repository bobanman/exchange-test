package exchange.web.rest;

import exchange.dto.ExchangeRequestDTO;
import exchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ExchangeController.URI)
@RequiredArgsConstructor
public class ExchangeController {

    public static final String URI = "/api/exchange";

    private final ExchangeService exchangeService;

    @PostMapping
    public ExchangeRequestDTO exchange(@RequestBody @Valid ExchangeRequestDTO ExchangeRequestDTO) {
        return exchangeService.exchange(ExchangeRequestDTO);
    }

}

