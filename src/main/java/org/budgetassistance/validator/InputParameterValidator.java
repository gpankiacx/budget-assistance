package org.budgetassistance.validator;

import java.math.BigDecimal;

public interface InputParameterValidator {

    void validateAmount(BigDecimal amount);

    void validateAccount(String accountId);
}
