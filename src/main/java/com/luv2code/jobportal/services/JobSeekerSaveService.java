package com.luv2code.jobportal.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.luv2code.jobportal.entity.JobPostActivity;
import com.luv2code.jobportal.entity.JobSeekerProfile;
import com.luv2code.jobportal.entity.JobSeekerSave;
import com.luv2code.jobportal.repository.JobSeekerSaveRepository;

@Service
public class JobSeekerSaveService {

	private final JobSeekerSaveRepository jobSeekerSaveRepository;
	
	public JobSeekerSaveService(JobSeekerSaveRepository jobSeekerSaveRepository) {
		this.jobSeekerSaveRepository = jobSeekerSaveRepository;
	}

	public List<JobSeekerSave> getCandidatesJob(JobSeekerProfile userAccountId){
		return jobSeekerSaveRepository.findByUserId(userAccountId);
	}
	
	public List<JobSeekerSave> getJobCandidates(JobPostActivity job){
		return jobSeekerSaveRepository.findByJob(job);
	}

	public void addNew(JobSeekerSave jobSeekerSave) {
		jobSeekerSaveRepository.save(jobSeekerSave);
	}
	
}
