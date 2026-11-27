package com.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bms.entity.Employee;
import com.bms.exception.IdNotFoundException;
import com.bms.exception.InValidAgeException;
import com.bms.service.EmployeeService;

@CrossOrigin
@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee employee) throws InValidAgeException
	{
		return employeeService.addEmployee(employee);
	}
	
	@GetMapping("/employee")
	public List<Employee> getAllEmployee()
	{
		return employeeService.getAllEmployee();
	}
	
	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable int id) throws IdNotFoundException
	{
		return employeeService.getEmployeeById(id);
	}
	
	
	@DeleteMapping("/employee/{id}")
	public String deleteEmployeeById(@PathVariable int id) throws IdNotFoundException
	{
		return employeeService.deleteEmployeeById(id);
	}
	
	@PutMapping("/employee/{id}")
	public Employee updateEmployeeById(@PathVariable int id,@RequestBody Employee employee) throws IdNotFoundException
	{
		return employeeService.updateEmployeeById(id, employee);
	}
	
	
	
	
}
