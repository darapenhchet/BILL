package kh.com.kshrd.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.com.kshrd.entities.Account;
import kh.com.kshrd.entities.Pagination;
import kh.com.kshrd.entities.User;
import kh.com.kshrd.filters.UserFilter;
import kh.com.kshrd.repositories.RoleRepository;
import kh.com.kshrd.repositories.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	protected JdbcTemplate jdbc;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User findUserByEmail(String email) {
		User user = jdbc.queryForObject(
				  "SELECT users.user_id "
				  + "	, users.username "
				  + "   , users.email "
				  + "   , users.password "
				  + "   , users.phone "
				  + "   , users.name "
				  + "   , users.status "
				  + "   , accounts.account_id "
				  + "   , accounts.account_no "
				  + "   , accounts.customer_id "
				  + "FROM users "
				  + "INNER JOIN accounts ON users.user_id = accounts.user_id "
				  + "WHERE users.email = ?", userMapper, email);
		user.setRoles(roleRepository.getAllRolesByUserId(user.getId()));
		return user;
	}
	
	@Override
	public List<User> findAllUsers(UserFilter filter, Pagination pagination) {
		return jdbc.query(
				  "SELECT users.user_id "
				  + "	, users.username "
				  + "   , users.email "
				  + "   , users.password "
				  + "   , users.phone "
				  + "   , users.name "
				  + "   , users.status "
				  + "   , accounts.account_id "
				  + "   , accounts.account_no "
				  + "   , accounts.customer_id "
				+ "FROM users "
				+ "INNER JOIN user_roles ON users.user_id = user_roles.user_id "
				+ "INNER JOIN accounts ON users.user_id = accounts.user_id "
				+ "WHERE user_roles.role_id = ?", userMapper, filter.getRoleId());
	}

	@Override
	public Boolean deleteUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean insertUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static final RowMapper<User> userMapper = new RowMapper<User>() {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("user_id"));
			user.setEmail(rs.getString("email"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setStatus(rs.getBoolean("status"));
			user.setPhone(rs.getString("phone"));
			
			Account account = new Account();
			account.setAccountId(rs.getLong("account_id"));
			account.setAccountNo(rs.getString("account_no"));
			account.setCustomerId(rs.getLong("customer_id"));
			user.setAccount(account);
			return user;
		}
	};
}
