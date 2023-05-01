import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Employees } from '../interface/employees';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeesService {

  constructor(private http: HttpClient) { }

  APP_ROOT_URL = 'http://localhost:8081/employee';

  getEmployees(): Observable<Employees[]> {
    return this.http.get<Employees[]>(`${this.APP_ROOT_URL}/all`);
  }

  addEmployee(employee: Employees): Observable<Employees> {
    return this.http.post<Employees>(`${this.APP_ROOT_URL}/add`, employee);
  }

  updateEmployes(idEmployees : number, employee: Employees): Observable<Employees> {
    return this.http.put<Employees>(`${this.APP_ROOT_URL}/update/${idEmployees}`, employee);
  }



  deleteEmployee(idEmployees : number): Observable<void> {
    return this.http.delete<void>(`${this.APP_ROOT_URL}/delete/${idEmployees}`);
  }
}
