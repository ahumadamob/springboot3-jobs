package com.ahumadamob.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ahumadamob.model.Category;

@Service
public class CategoryServiceImpl implements ICategoryService {
	
	private List<Category> categories = null;
	
	public CategoryServiceImpl() {
		
		categories = new LinkedList<Category>();
		
		try {
			Category category1 = new Category();
			category1.setId(1);
			category1.setName("Ventas");
			category1.setDescription("Categoría de ofertas laborales relacionadas a Ventas");
			categories.add(category1);
			
			Category category2 = new Category();
			category2.setId(2);
			category2.setName("Contabilidad");
			category2.setDescription("Categoría de ofertas laborales relacionadas a Contabilidad");
			categories.add(category2);		
			
			Category category3 = new Category();
			category3.setId(3);
			category3.setName("Transporte");
			category3.setDescription("Categoría de ofertas laborales relacionadas a Transporte");
			categories.add(category1);
			
			Category category4 = new Category();
			category4.setId(4);
			category4.setName("Informática");
			category4.setDescription("Categoría de ofertas laborales relacionadas a Informática");
			categories.add(category4);		
			
			Category category5 = new Category();
			category5.setId(5);
			category5.setName("Construcción");
			category5.setDescription("Categoría de ofertas laborales relacionadas a Construcción");
			categories.add(category5);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error:" + e.getMessage());
		}		
		
	}

	@Override
	public List<Category> fetchAll() {		
		return categories;
	}

	@Override
	public Category findById(Integer idCategory) {
		for(Category category : categories) {
			if(category.getId() == idCategory) {
				return category;
			}
		}
		return null;
	}

	@Override
	public void save(Category category) {
		categories.add(category);		
	}

	@Override
	public Page<Category> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
