package com.ahumadamob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahumadamob.model.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {
	
	public List<Job> findByStatus(String status);
	public List<Job> findByHighlightAndStatusOrderByIdDesc(int highlight, String status);

}
