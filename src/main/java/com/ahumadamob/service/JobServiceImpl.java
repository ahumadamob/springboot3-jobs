package com.ahumadamob.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ahumadamob.model.Job;

@Service
public class JobServiceImpl implements IJobService {
	
	private List<Job> jobs = null;	
	
	public JobServiceImpl() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		jobs = new LinkedList<Job>();
		
		try {
			// Creamos la oferta de trabajo 1
			Job job1 = new Job();
			job1.setId(1);
			job1.setTitle("Ingeniero Civil");
			job1.setDescription("Se solicita Ing. Civil para diseñar puente peatonal");
			job1.setPostDate(sdf.parse("15-12-2020"));
			job1.setSalary(8400.0);
			job1.setHighlight(1);
			
			
			// Creamos la oferta de trabajo 2
			Job job2 = new Job();
			job2.setId(2);
			job2.setTitle("Arquero");
			job2.setDescription("Solicitamos arquero que no se coma goles como Armani");
			job2.setPostDate(sdf.parse("03-01-2021"));
			job2.setSalary(7500000.0);	
			job2.setHighlight(0);
			
			// Creamos la oferta de trabajo 3
			Job job3 = new Job();
			job3.setId(3);
			job3.setTitle("Lateral Izquierdo");
			job3.setDescription("Solicitamos marcador de punta izquierdo que juegue además como win y recorte para adentro");
			job3.setPostDate(sdf.parse("05-01-2021"));
			job3.setSalary(17500000.0);
			job3.setHighlight(0);
			job3.setPicture("river.png");
			
			// Creamos la oferta de trabajo 4
			Job job4 = new Job();
			job4.setId(4);
			job4.setTitle("Delantero nivel Dios");
			job4.setDescription("Solicitamos delantero que le haga tres goles o más al Palmeiras");
			job4.setPostDate(sdf.parse("08-01-2021"));
			job4.setSalary(57500000.0);
			job4.setHighlight(1);
			job4.setPicture("river.png");
			
			jobs.add(job1);
			jobs.add(job2);
			jobs.add(job3);
			jobs.add(job4);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error:" + e.getMessage());
		}	
		
	}
	
	@Override
	public List<Job> fetchAll() {		
		return jobs;
	}

	@Override
	public Job fetchById(Integer idJob) {
		for(Job job : jobs) {
			if(job.getId() == idJob) {
				return job;
			}
		}
		return null;
	}

	@Override
	public void save(Job job) {
		jobs.add(job);
		
	}

	@Override
	public List<Job> findHighlight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int idJob) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Job> searchByExample(Example<Job> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Job> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
