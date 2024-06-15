package com.luv2code.jobportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.jobportal.entity.Users;

public interface UsersRepository extends JpaRepository<Users,Integer>{

	Optional<Users> findByEmail(String email);

}
