package com.ahumadamob.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ahumadamob.model.Category;

public interface ICategoryService {
	
	List<Category> fetchAll();
	Category findById(Integer idCategory);
	void save(Category category);
	Page<Category> findAll(Pageable page);

}
