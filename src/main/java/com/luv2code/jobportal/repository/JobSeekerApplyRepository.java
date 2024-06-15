package com.luv2code.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luv2code.jobportal.entity.JobPostActivity;
import com.luv2code.jobportal.entity.JobSeekerApply;
import com.luv2code.jobportal.entity.JobSeekerProfile;

@Repository
public interface JobSeekerApplyRepository extends JpaRepository<JobSeekerApply, Integer> {

	List<JobSeekerApply> findByUserId(JobSeekerProfile userId);
	
	List<JobSeekerApply> findByJob(JobPostActivity job);
}
