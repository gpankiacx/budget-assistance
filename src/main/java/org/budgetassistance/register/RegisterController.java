package org.budgetassistance.register;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/v1/register"}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public final class RegisterController {

    private final RegisterService service;

    @Autowired
    public RegisterController(RegisterService service) {
        this.service = service;
    }

    @PostMapping(path ="/recharge/{accountId}/{category}/{amount}")
    public Register recharge(@PathVariable String accountId, @PathVariable String category, @PathVariable BigDecimal amount) {
        return service.recharge(accountId, category, amount);
    }

    @PostMapping(path = "/transfer/{accountId}/{source}/{target}/{amount}")
    public Map<String, Register> transfer(@PathVariable String accountId, @PathVariable String source,
      @PathVariable String target, @PathVariable BigDecimal amount) {
        return service.transfer(accountId, source, target, amount);
    }

    @GetMapping(path = "/balance/{accountId}")
    public Set<Register> getBalance(@PathVariable String accountId) {
        return service.getBalance(accountId);
    }

}
