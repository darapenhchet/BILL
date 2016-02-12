package kh.com.kshrd.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.com.kshrd.entities.Account;
import kh.com.kshrd.entities.Bill;
import kh.com.kshrd.repositories.BillRepository;

@Repository
public class BillRepositoryImpl implements BillRepository{

	@Autowired
	protected JdbcTemplate jdbc;
	
	@Override
	public List<Bill> findAllBillsByAccountId(Long accountId) {
		return jdbc.query(
					"SELECT  "
				  + "	  bills.bill_id "
				  + "   , bills.billing_id "
				  + "   , bills.amount "
				  + "   , bills.created_date "
				  + "   , bills.currency "
				  + "   , bills.expiry_date "
				  + "   , accounts.account_id "
				  + "   , accounts.account_no "
				  + "   , accounts.customer_id "
				+ "FROM bills "
				+ "INNER JOIN billings ON bills.bill_id = billings.bill_id "
				+ "INNER JOIN accounts ON billings.account_id = accounts.account_id "
				+ "WHERE accounts.account_id = ?", billMapper, accountId); 
	}
	
	private static final RowMapper<Bill> billMapper = new RowMapper<Bill>() {
		public Bill mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bill bill = new Bill();
			bill.setBillId(rs.getLong("bill_id"));
			bill.setBiilingId(rs.getLong("billing_id"));
			bill.setCreatedDate(rs.getDate("created_date"));
			bill.setCurrency(rs.getString("currency"));
			bill.setExpiryDate(rs.getDate("expiry_date"));
			bill.setAmount(rs.getDouble("amount"));
			
			/*Account account = new Account();
			account.setAccountId(rs.getLong("account_id"));
			account.setAccountNo(rs.getString("account_no"));
			account.setCustomerId(rs.getLong("customer_id"));
			bill.getAccounts().add(account);*/
			
			return bill;
		}
	};
	
}
