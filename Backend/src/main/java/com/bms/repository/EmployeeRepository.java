package com.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bms.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	

}
