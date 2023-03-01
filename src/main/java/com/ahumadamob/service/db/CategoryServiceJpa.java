package com.ahumadamob.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ahumadamob.model.Category;
import com.ahumadamob.repository.CategoryRepository;
import com.ahumadamob.service.ICategoryService;

@Service
@Primary
public class CategoryServiceJpa implements ICategoryService {
	
	@Autowired
	private CategoryRepository categoryR;

	@Override
	public List<Category> fetchAll() {
		return categoryR.findAll();
	}

	@Override
	public Category findById(Integer idCategory) {
		Optional<Category> optional = categoryR.findById(idCategory);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}

	}

	@Override
	public void save(Category category) {
		categoryR.save(category);
	}

	@Override
	public Page<Category> findAll(Pageable page) {
		return categoryR.findAll(page);
	}
	
	

}
