package com.ahumadamob.service;

import java.util.List;

import com.ahumadamob.model.User;

public interface IUserService {
	
	List<User> findAll();
	User findById(int idUser);
	void save(User user);
	void delete(int idUser);	

}
