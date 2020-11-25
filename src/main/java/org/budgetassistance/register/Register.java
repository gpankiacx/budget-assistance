package org.budgetassistance.register;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.budgetassistance.account.Account;

@Entity
@Table(name="REGISTER")
public class Register {

    @Id
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;
    private BigDecimal balance;
    private String category;

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCategory() {
        return category;
    }

    public void add(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void subtract(BigDecimal amount) {
        balance = balance.subtract(amount);
    }
}
