package kh.com.kshrd.services;

import java.util.List;

import kh.com.kshrd.entities.Bill;

public interface BillService {

	public List<Bill> findAllBillsByAccountId(Long accountId);
}
