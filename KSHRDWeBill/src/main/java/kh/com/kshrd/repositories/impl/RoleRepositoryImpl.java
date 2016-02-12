package kh.com.kshrd.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.com.kshrd.entities.Role;
import kh.com.kshrd.repositories.RoleRepository;

@Repository
public class RoleRepositoryImpl implements RoleRepository{
	
	@Autowired
	protected JdbcTemplate jdbc;
	
	@Override
	public List<Role> getAllRolesByUserId(Long id) {
		return jdbc.query("SELECT roles.id, roles.name, roles.status "
						+ "FROM roles "
						+ "INNER JOIN user_roles ON roles.id = user_roles.role_id "
						+ "WHERE user_roles.user_id = ?", roleMapper, id);
	}
	
	private static final RowMapper<Role> roleMapper = new RowMapper<Role>() {
		public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
			Role role = new Role();
			role.setId(rs.getInt("id"));
			role.setName(rs.getString("name"));
			role.setStatus(rs.getBoolean("status"));
			return role;
		}
	};
	
	

}
