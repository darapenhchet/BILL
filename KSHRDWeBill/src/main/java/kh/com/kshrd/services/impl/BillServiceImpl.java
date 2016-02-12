package kh.com.kshrd.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.com.kshrd.entities.Bill;
import kh.com.kshrd.repositories.BillRepository;
import kh.com.kshrd.services.BillService;

@Service
public class BillServiceImpl implements BillService{

	@Autowired
	private BillRepository billRepository;
	
	@Override
	public List<Bill> findAllBillsByAccountId(Long accountId) {
		return billRepository.findAllBillsByAccountId(accountId);
	}

}
