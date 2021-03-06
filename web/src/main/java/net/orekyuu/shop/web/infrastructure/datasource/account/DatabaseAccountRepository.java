package net.orekyuu.shop.web.infrastructure.datasource.account;

import net.orekyuu.shop.identity.domain.model.account.Account;
import net.orekyuu.shop.identity.domain.model.account.AccountId;
import net.orekyuu.shop.identity.domain.model.account.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Optional;

@Repository
public class DatabaseAccountRepository implements AccountRepository {

    final AccountDao dao;

    public DatabaseAccountRepository(AccountDao dao) {
        this.dao = dao;
    }

    @Override
    public Optional<Account> findById(AccountId id) {
        return dao.findByIds(Collections.singleton(id)).stream()
                .findFirst()
                .map(AccountTable::toAccount);
    }

    @Override
    public void insert(Account account) {
        dao.insert(new AccountTable(account));
    }
}
