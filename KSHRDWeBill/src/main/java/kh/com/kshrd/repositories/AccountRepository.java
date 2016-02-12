package kh.com.kshrd.repositories;

import java.util.List;

import kh.com.kshrd.entities.Account;

public interface AccountRepository {

	public List<Account> findAllAccountByBillId();
}
