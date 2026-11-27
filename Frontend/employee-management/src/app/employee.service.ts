
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Employee {
  id: number;
  name: string;
  dept: string;
  age: number;
  salary: number;
}

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  // Change this URL to your backend URL
  private apiUrl = 'http://localhost:8090/employee';

  constructor(private http: HttpClient) {}

  // GET all employees
  getAllEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.apiUrl);
  }

  // GET employee by ID
  getEmployeeById(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.apiUrl}/${id}`);
  }

  // PUT update employee
  updateEmployee(id: number, employee: Employee): Observable<any> {
    return this.http.put(
      `${this.apiUrl}/${id}`,
      employee
    );
  }

  // DELETE employee
  deleteEmployee(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  // POST: Add new employee
addEmployee(employee: Employee): Observable<any> {
  return this.http.post(this.apiUrl, employee);
}
}
