package com.phoebus.pandemiaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phoebus.pandemiaapi.entity.Hospital;


public interface HospitalRepository extends JpaRepository<Hospital, Long>{
	

}
