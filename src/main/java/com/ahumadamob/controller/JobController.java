package com.ahumadamob.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ahumadamob.model.Job;
import com.ahumadamob.service.ICategoryService;
import com.ahumadamob.service.IJobService;
import com.ahumadamob.util.Utils;

@Controller
@RequestMapping("/jobs/")
public class JobController {
	
	@Autowired
	private IJobService serviceJob;
	
	@Autowired
	private ICategoryService serviceCategory;
	
	@Value("${jobs.path.images}")
	private String imagePath;
	
	@GetMapping("/")
	public String index(Model model) {
		List<Job> jobs = serviceJob.fetchAll();
		model.addAttribute("jobs", jobs);
		return "jobs/listJobs";
	}
	
	@GetMapping("/pages")
	public String indexPaginator(Model model, Pageable page) {
		Page<Job> jobs = serviceJob.findAll(page);
		model.addAttribute("jobs", jobs);
		return "jobs/listJobs";
	}
	
	@GetMapping("/details/{id}")
	public String details(@PathVariable("id") int idJob, Model model) {
		
		Job job = serviceJob.fetchById(idJob);
		model.addAttribute("job", job);
		
		System.out.println("job: " + job);
		
		//TODO: Buscar los detalles de la vacante en la base de datos
		return "details";
	}
	
	@GetMapping("/create")
	public String create(Job job, Model model) {
		return "jobs/formJob";
	}
	
	@PostMapping("/save")
	public String save(Job job, BindingResult result, RedirectAttributes redirectAttributes, 
			@RequestParam("imageFile") MultipartFile multipart) {
		
		if(result.hasErrors()) {
			for(ObjectError error: result.getAllErrors()) {
				System.out.println("Detalle del error: " + error.getDefaultMessage());
			}
			return "jobs/formJob";
		}
		
		if(!multipart.isEmpty()) {
			String imageName = Utils.saveImage(multipart, imagePath);
			if(imageName != null) {
				job.setPicture(imageName);
			}
		}
		
		System.out.println("Objeto vacant: " + job);
		serviceJob.save(job);
		redirectAttributes.addFlashAttribute("msg", "La vacante ha sido añadida correctamente");
		return "redirect:/vacancies/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int idJob, RedirectAttributes attributes) {
		serviceJob.delete(idJob);
		attributes.addFlashAttribute("msg", "La oferta fue eliminada");
		return "redirect:/jobs/listJobs";
		
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int idJob, Model model) {
		Job job = serviceJob.fetchById(idJob);		
		model.addAttribute("job", job);		
		return "/jobs/formJob";		
	}
	
	@ModelAttribute
	public void setGeneric(Model model) {
		model.addAttribute("categories", serviceCategory.fetchAll());
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
