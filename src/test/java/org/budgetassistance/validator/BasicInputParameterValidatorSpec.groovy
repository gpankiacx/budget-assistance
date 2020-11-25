package org.budgetassistance.validator

import org.budgetassistance.account.Account
import org.budgetassistance.account.AccountRepository
import spock.lang.Specification
import spock.lang.Unroll

class BasicInputParameterValidatorSpec extends Specification {

    @Unroll
    def "validate amount should throw exception for #amount"() {
        setup:
        def accountRepository = Mock(AccountRepository)
        def validator = new BasicInputParameterValidator(accountRepository)

        when:
        validator.validateAmount(amount)

        then:
        def error = thrown(expectedException)
        error.message.contains(expectedMessage)

        where:
        amount || expectedException || expectedMessage
        0      || RuntimeException  || "Invalid amount"
        -1     || RuntimeException  || "Invalid amount"
    }

    @Unroll
    def "amount validation should pass for #amount"() {
        setup:
        def accountRepository = Mock(AccountRepository)
        def validator = new BasicInputParameterValidator(accountRepository)

        when:
        validator.validateAmount(amount)

        then:
        noExceptionThrown()

        where:
        amount << [1, 200]
    }

    def "account validation should throw exception for not existing one"() {
        given:
        def accountRepository = Mock(AccountRepository)
        accountRepository.findById(_) >> Optional.empty()
        def validator = new BasicInputParameterValidator(accountRepository)

        when:
        validator.validateAccount("1")

        then:
        def error = thrown(RuntimeException)
        error.message.contains("Account does not exists")
    }

    def "account validation should pass"() {
        given:
        def accountRepository = Mock(AccountRepository)
        accountRepository.findById(_) >> Optional.of(new Account())
        def validator = new BasicInputParameterValidator(accountRepository)

        when:
        validator.validateAccount("1")

        then:
        noExceptionThrown()
    }
}
