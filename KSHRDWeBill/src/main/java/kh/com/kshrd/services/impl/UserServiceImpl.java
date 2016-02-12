package kh.com.kshrd.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.com.kshrd.entities.Pagination;
import kh.com.kshrd.entities.User;
import kh.com.kshrd.filters.UserFilter;
import kh.com.kshrd.repositories.UserRepository;
import kh.com.kshrd.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findUserByEmail(String email) {
		try{
			return userRepository.findUserByEmail(email);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
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

	@Override
	public List<User> findAllUsers(UserFilter filter, Pagination pagination) {
		try{
			return userRepository.findAllUsers(filter, pagination);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
		
	}
	
}
