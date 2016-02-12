package kh.com.kshrd.repositories;

import java.util.List;

import kh.com.kshrd.entities.Bill;

public interface BillRepository{

	public List<Bill> findAllBillsByAccountId(Long accountId);
}
