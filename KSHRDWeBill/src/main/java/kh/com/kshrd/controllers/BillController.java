package kh.com.kshrd.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kh.com.kshrd.entities.Bill;
import kh.com.kshrd.entities.Pagination;
import kh.com.kshrd.services.BillService;

@Controller
public class BillController {

	
	@Autowired
	private BillService billService;
	
	@RequestMapping(value="/account/{accountId}/bills", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findAllBillByDonorId(@PathVariable("accountId") Long accountId, Pagination pagination){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Bill> bills = billService.findAllBillsByAccountId(accountId);
		map.put("RESP_DATA", bills);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
}
