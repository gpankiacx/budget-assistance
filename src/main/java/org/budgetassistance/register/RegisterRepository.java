package org.budgetassistance.register;

import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<Register, String> {

  Set<Register> findByAccountId(String accountId);

  Optional<Register> findByAccountIdAndCategory(String accountId, String category);

}
