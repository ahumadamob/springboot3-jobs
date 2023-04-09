package com.ahumadamob.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahumadamob.model.User;
import com.ahumadamob.repository.UserRepository;
import com.ahumadamob.service.IUserService;

@Service
public class UserServiceJpa implements IUserService {
	
	@Autowired
	private UserRepository userR;

	@Override
	public List<User> findAll() {
		return userR.findAll();
	}

	@Override
	public User findById(int idUser) {
		Optional<User> optional = userR.findById(idUser);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}

	@Override
	public void save(User user) {
		userR.save(user);
	}

	@Override
	public void delete(int idUser) {
		userR.deleteById(idUser);
	}

}
