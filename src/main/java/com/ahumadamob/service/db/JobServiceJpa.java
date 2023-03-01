package com.ahumadamob.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ahumadamob.model.Job;
import com.ahumadamob.repository.JobRepository;
import com.ahumadamob.service.IJobService;

@Service
@Primary
public class JobServiceJpa implements IJobService {
	
	@Autowired
	private JobRepository jobRepository;

	@Override
	public List<Job> fetchAll() {
		return jobRepository.findAll();
	}

	@Override
	public Job fetchById(Integer idJob) {
		Optional<Job> optional = jobRepository.findById(idJob);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}

	@Override
	public void save(Job job) {
		jobRepository.save(job);

	}

	@Override
	public List<Job> findHighlight() {
		List<Job> jobs = jobRepository.findByHighlightAndStatusOrderByIdDesc(1, "Aprobada");
		System.out.println("Jobs encontrados: " + jobs.size());
		for(Job job: jobs) {
			System.out.println(job.toString());
		}
		return jobs;
	}

	@Override
	public void delete(int idJob) {
		jobRepository.deleteById(idJob);
	}

	@Override
	public List<Job> searchByExample(Example<Job> example) {
		return jobRepository.findAll(example);
	}

	@Override
	public Page<Job> findAll(Pageable page) {
		return jobRepository.findAll(page);

	}
	
	
	
	

}
