
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EmployeeService, Employee } from '../employee.service';

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './employee.component.html',
  styleUrl: './employee.component.css'
})
export class EmployeeComponent implements OnInit {

  employees: Employee[] = [];

  searchId: number | null = null;

  employee: Employee = {
    id: 0,
    name: '',
    dept: '',
    age: 0,
    salary: 0
  };

  editMode = false;
  message = '';
  errorMessage = '';

  constructor(private employeeService: EmployeeService) {}

  ngOnInit(): void {
    this.getAllEmployees();
  }

  // GET all employees
  getAllEmployees(): void {
    this.employeeService.getAllEmployees().subscribe({
      next: (data) => {
        this.employees = data;
        this.errorMessage = '';
      },
      error: (error) => {
        this.errorMessage = 'Unable to fetch employees';
        console.error(error);
      }
    });
  }

  // GET employee by ID
  searchEmployee(): void {

    if (this.searchId === null || this.searchId <= 0) {
      this.errorMessage = 'Please enter a valid employee ID';
      return;
    }

    this.employeeService.getEmployeeById(this.searchId).subscribe({
      next: (data) => {
        this.employees = [data];
        this.errorMessage = '';
        this.message = 'Employee found successfully';
      },
      error: (error) => {
        this.employees = [];
        this.errorMessage = 'Employee not found';
        this.message = '';
        console.error(error);
      }
    });
  }

  // Select employee for update
  editEmployee(emp: Employee): void {
    this.employee = { ...emp };
    this.editMode = true;
    this.message = '';
    this.errorMessage = '';
  }

  // PUT update employee
  updateEmployee(): void {

    this.employeeService.updateEmployee(
      this.employee.id,
      this.employee
    ).subscribe({
      next: () => {
        this.message = 'Employee updated successfully!';
        this.errorMessage = '';
        this.resetForm();
        this.getAllEmployees();
      },
      error: (error) => {
        this.errorMessage = 'Unable to update employee';
        console.error(error);
      }
    });
  }

  // DELETE employee
  deleteEmployee(id: number): void {

    if (confirm('Are you sure you want to delete this employee?')) {

      this.employeeService.deleteEmployee(id).subscribe({
        next: () => {
          this.message = 'Employee deleted successfully!';
          this.errorMessage = '';
          this.getAllEmployees();
        },
        error: (error) => {
          this.errorMessage = 'Unable to delete employee';
          console.error(error);
        }
      });

    }
  }

  // POST: Add employee
addEmployee(): void {
  this.employeeService.addEmployee(this.employee).subscribe({
    next: () => {
      this.message = 'Employee added successfully!';
      this.errorMessage = '';
      this.resetForm();
      this.getAllEmployees();
    },
    error: (error) => {
      this.errorMessage = 'Unable to add employee';
      console.error(error);
    }
  });
}

  resetForm(): void {
  this.employee = {
    id: 0,
    name: '',
    dept: '',
    age: 0,
    salary: 0
  };

  this.editMode = false;
}
}
