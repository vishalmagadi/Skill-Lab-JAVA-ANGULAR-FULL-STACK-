package com.bms.service;

import java.util.List;

import com.bms.entity.Employee;
import com.bms.exception.IdNotFoundException;
import com.bms.exception.InValidAgeException;

public interface EmployeeService {

	Employee addEmployee(Employee employee) throws InValidAgeException;
	List<Employee> getAllEmployee();
	Employee getEmployeeById(int id) throws IdNotFoundException;
	String deleteEmployeeById(int id) throws IdNotFoundException;
	Employee updateEmployeeById(int id, Employee employee) throws IdNotFoundException;
	
}
