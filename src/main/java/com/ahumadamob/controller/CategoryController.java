package com.ahumadamob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ahumadamob.model.Category;
import com.ahumadamob.service.ICategoryService;

@Controller
@RequestMapping(value="categories")
public class CategoryController {
	
	@Autowired
	private ICategoryService serviceCategory;
	
	@GetMapping("/")
	public String index(Model model) {
		List<Category> categories = serviceCategory.fetchAll();
		model.addAttribute("categories", categories);		
		return "categories/listCategories";
	}
	
	@GetMapping("/pages")
	public String indexPaginator(Model model, Pageable page) {
		Page<Category> categories = serviceCategory.findAll(page);
		model.addAttribute("categories", categories);
		return "categories/listCategories";
	}	
	
	@GetMapping("create")
	public String create() {
		return "categories/formCategory";
	}
	
	@PostMapping("save")
	public String save(Category category, BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			return "categories/formCategory";
		}
		serviceCategory.save(category);
		redirectAttributes.addFlashAttribute("msg", "La vacante ha sido añadida correctamente");
		return "redirect:/categories/";
	}

}
