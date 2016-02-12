package kh.com.kshrd.services;

import java.util.List;

import kh.com.kshrd.entities.Pagination;
import kh.com.kshrd.entities.User;
import kh.com.kshrd.filters.UserFilter;

public interface UserService {

	public User findUserByEmail(String email);
	
	public Boolean deleteUserById(Long id);
	
	public Boolean updateUser(User user);
	
	public Boolean insertUser(User user);
	
	public List<User> findAllUsers(UserFilter filter, Pagination pagination);
}
