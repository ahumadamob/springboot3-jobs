package com.ahumadamob.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ahumadamob.model.Job;

public interface IJobService {
	List<Job> fetchAll();
	Page<Job> findAll(Pageable page);
	List<Job> findHighlight();
	Job fetchById(Integer idJob);
	void save(Job job);
	void delete(int idJob);
	List<Job> searchByExample(Example<Job> example);
}
