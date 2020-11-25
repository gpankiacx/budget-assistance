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

}
