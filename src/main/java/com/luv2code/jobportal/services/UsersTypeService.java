package com.luv2code.jobportal.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.luv2code.jobportal.entity.UsersType;
import com.luv2code.jobportal.repository.UsersTypeRepository;

@Service
public class UsersTypeService {

	private final UsersTypeRepository usersTypeRepository;
	
	//Constructor Injection
	public UsersTypeService(UsersTypeRepository usersTypeRepository) {
		this.usersTypeRepository = usersTypeRepository;
	}
	
	//Method to Get All UserTypes
	public List<UsersType> getAll(){
		return usersTypeRepository.findAll();
	}
}
