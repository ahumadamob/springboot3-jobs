package com.ahumadamob.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ahumadamob.model.User;
import com.ahumadamob.service.IUserService;

@Controller
@RequestMapping("/users/")
public class UserController {
	
	@Autowired
	private IUserService serviceUser;
	
	@GetMapping("/")
	public String index(Model model) {
		List<User> users = serviceUser.findAll();
		model.addAttribute("users", users);
		return "users/listUsers";
	}
	
	@PostMapping("/save")
	public String save(User user, BindingResult result) {
		
		if(result.hasErrors()) {
			for(ObjectError error: result.getAllErrors()) {
				System.out.println("Detalle del error: " + error.getDefaultMessage());
			}
			return "/formSignup";			
		}
		user.setCreateDate(new Date());
		user.setStatus(1);
		serviceUser.save(user);
		return "redirect:/";
	}
	
	@GetMapping("/activate/{id}")
	private String activateUser(@PathVariable("id") int idUser, RedirectAttributes attributes) {
		User user = serviceUser.findById(idUser);
		user.setStatus(1);
		serviceUser.save(user);
		attributes.addFlashAttribute("msg", "El usuario ha sido activado.");
		return "redirect:/users/";
	}
	
	@GetMapping("/deactivate/{id}")
	private String deactivateUser(@PathVariable("id") int idUser, RedirectAttributes attributes) {
		User user = serviceUser.findById(idUser);
		user.setStatus(0);
		serviceUser.save(user);
		attributes.addFlashAttribute("msg", "El usuario ha sido desactivado.");
		return "redirect:/users/";
	}
	
	@GetMapping("/delete/{id}")
	private String delete(@PathVariable("id") int idUser, RedirectAttributes attributes) {
		serviceUser.delete(idUser);
		attributes.addFlashAttribute("msg", "El usuario ha sido eliminado.");
		return "redirect:/users/";
	}	
	
}
