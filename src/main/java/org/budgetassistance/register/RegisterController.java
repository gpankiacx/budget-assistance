package org.budgetassistance.register;

import java.math.BigDecimal;
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

    @PostMapping(path ="/recharge/{accountId}/{category}/{amount}")
    public ResponseEntity recharge(@PathVariable String accountId, @PathVariable String category, @PathVariable BigDecimal amount) {
        return null;
    }

    @PostMapping(path = "/transfer/{accountId}/{source}/{target}/{amount}")
    public ResponseEntity transfer(@PathVariable String accountId, @PathVariable String source,
      @PathVariable String target, @PathVariable BigDecimal amount) {
        return null;
    }

    @GetMapping(path = "/balance/{accountId}")
    public ResponseEntity getBallance(@PathVariable String accountId) {
        return null;
    }

}
