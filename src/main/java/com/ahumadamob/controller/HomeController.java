package com.ahumadamob.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ahumadamob.model.Job;
import com.ahumadamob.service.ICategoryService;
import com.ahumadamob.service.IJobService;

@Controller
public class HomeController {
	
	@Autowired
	private IJobService serviceJob;
	
	@Autowired
	private ICategoryService serviceCategory;
	
	@GetMapping("/jobs")
	public String showJobs (Model model) {
		List<Job> jobs = serviceJob.fetchAll();
		model.addAttribute("jobs", jobs);
		return "jobs";
	}
	
	@GetMapping("/")
	public String showHome(Model model) {
		List<Job> jobs = serviceJob.findHighlight();
		model.addAttribute("jobs", jobs);		
		return "home";
	}
	
	@GetMapping("/details")
	public String showDetails(Model model) {
		Job job = new Job();
		job.setTitle("Ingeniero en comunicaciones");
		job.setDescription("Se solicita ingeniero para dar soporte en intranet");
		job.setPostDate(new Date());
		job.setSalary(150000.0);
		model.addAttribute("job", job);
		return "details";
	}
	
	@GetMapping("/signup")
	public String signup(Model model, Job job) {
		return "formSignup";
	}
	
	@GetMapping("/search")
	public String search(@ModelAttribute("search")  Job job, Model model) {
		System.out.println("Buscando por: " + job);
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains());
		Example<Job> example = Example.of(job, matcher);
		List<Job> jobs = serviceJob.searchByExample(example);
		model.addAttribute("jobs", jobs);
		return "home";
		
	}

	@ModelAttribute
	public void setGenerics(Model model) {
		Job search = new Job();
		model.addAttribute("categories", serviceCategory.fetchAll());
		model.addAttribute("search", search);
		
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));		
	}
}
