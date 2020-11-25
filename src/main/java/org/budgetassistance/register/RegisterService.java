package org.budgetassistance.register;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.budgetassistance.account.AccountRepository;
import org.budgetassistance.validator.BasicInputParameterValidator;
import org.budgetassistance.validator.InputParameterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private static final String SOURCE_KEY = "source";
    private static final String TARGET_KEY = "target";

    private final RegisterRepository registerRepository;
    private final AccountRepository accountRepository;
    private final InputParameterValidator parameterValidator;

    @Autowired
    public RegisterService(RegisterRepository registerRepository, AccountRepository accountRepository, BasicInputParameterValidator parameterValidator) {
        this.registerRepository = registerRepository;
        this.accountRepository = accountRepository;
        this.parameterValidator = parameterValidator;
    }

    public Register recharge(String accountId, String category, BigDecimal amount) {
        parameterValidator.validateAccount(accountId);
        parameterValidator.validateAmount(amount);

        return registerRepository.findByAccountIdAndCategory(accountId, category).map(register -> {
            register.add(amount);
            return registerRepository.save(register);
        }).orElseThrow(() -> new RuntimeException("Register not found - " + category));
    }

    public Map<String, Register> transfer(String accountId, String source, String target, BigDecimal amount) {
        parameterValidator.validateAccount(accountId);
        parameterValidator.validateAmount(amount);

        Register sourceRegister = registerRepository.findByAccountIdAndCategory(accountId, source)
          .filter(register -> register.getBalance().compareTo(amount) >= 0).map(register -> {
              register.subtract(amount);
              return register;
          }).orElseThrow(() -> new RuntimeException("Could not subtract from source register - " + source));
        Register targetRegister = registerRepository.findByAccountIdAndCategory(accountId, target).map(register -> {
            register.add(amount);
            return register;
        }).orElseThrow(() -> new RuntimeException("Could not add to target register - " + target));
        registerRepository.saveAll(List.of(sourceRegister, targetRegister));
        return Map.of(SOURCE_KEY, sourceRegister, TARGET_KEY, targetRegister);
    }

    public Set<Register> getBalance(String accountId) {
        parameterValidator.validateAccount(accountId);

        return registerRepository.findByAccountId(accountId);
    }
}
