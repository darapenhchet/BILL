package kh.com.kshrd.repositories;

import java.util.List;

import kh.com.kshrd.entities.Role;

public interface RoleRepository {

	public List<Role> getAllRolesByUserId(Long id);
}
