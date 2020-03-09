package exchange.web.rest;

import exchange.dto.CommissionDTO;
import exchange.service.CommissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(CommissionController.URI)
@RequiredArgsConstructor
public class CommissionController {

    public static final String URI = "/api/commissions";

    private final CommissionService commissionService;

    @GetMapping
    public List<CommissionDTO> getCommissions() {
        return commissionService.getCommissions();
    }


    @PostMapping
    public CommissionDTO setCommission(@RequestBody @Valid CommissionDTO commission) {
        return commissionService.setCommission(commission);
    }

}
