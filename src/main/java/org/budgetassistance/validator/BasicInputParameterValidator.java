package org.budgetassistance.validator;

import java.math.BigDecimal;
import org.budgetassistance.account.AccountRepository;
import org.budgetassistance.exception.InvalidParameterException;
import org.budgetassistance.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class BasicInputParameterValidator implements InputParameterValidator {

    private final AccountRepository accountRepository;

    @Autowired
    BasicInputParameterValidator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidParameterException("Invalid amount - " + amount);
        }
    }

    @Override
    public void validateAccount(String accountId) {
        accountRepository.findById(accountId)
          .orElseThrow(() -> new ResourceNotFoundException("Account does not exists - id: " + accountId));
    }
}
