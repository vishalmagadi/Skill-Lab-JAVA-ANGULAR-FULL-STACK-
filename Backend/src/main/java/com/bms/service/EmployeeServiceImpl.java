package com.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.entity.Employee;
import com.bms.exception.IdNotFoundException;
import com.bms.exception.InValidAgeException;
import com.bms.repository.EmployeeRepository;

@Service // It will configure the bean
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee addEmployee(Employee employee) throws InValidAgeException {
		 if(employee.getAge()<22)
			 throw new InValidAgeException("Age is less, Minimun age is 22");
		
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) throws IdNotFoundException {
		return employeeRepository.findById(id).orElseThrow(()-> new IdNotFoundException("No id Present for Reterive"));
	}

	@Override
	public String deleteEmployeeById(int id) throws IdNotFoundException {
		  Employee emp=  employeeRepository.findById(id).orElseThrow(()->new IdNotFoundException("No Id Found to delete"));
		
		    employeeRepository.delete(emp);
		    return "Employee with ID"+ id +"Deleted Successfully" ;
	}

	@Override
	public Employee updateEmployeeById(int id, Employee employee) throws IdNotFoundException {
		  Employee emp=  employeeRepository.findById(id).orElseThrow(()->new IdNotFoundException("No Id Found to Update"));
              emp.setName(employee.getName());
              emp.setDept(employee.getDept());
              emp.setSalary(employee.getSalary());
              emp.setAge(employee.getAge());
		
              return employeeRepository.save(emp);
	}
	
	
	

	
}
