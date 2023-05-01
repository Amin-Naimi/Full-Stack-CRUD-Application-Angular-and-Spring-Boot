import { Component, HostListener, OnInit } from '@angular/core';
import { Employees } from './interface/employees';
import { EmployeesService } from './services/employees.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'my-first-project';

  public employees!: Employees[];
  employeeName: any;
  employee!: Employees | null;
  id!: number;
  constructor(private employesService: EmployeesService) { }

  ngOnInit(): void {
    this.getEmployees();
  }

  public getEmployees(): void {
    this.employesService.getEmployees().subscribe(
      (response: Employees[]) => {
        console.log(response);
        this.employees = response;
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
        alert(error.message);

      })
  }

  public onOpenModal(employees: Employees |null, mode: string): void {
    const container  = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-bs-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-bs-target', '#addEmployeeModal');
    }
    else if (mode === 'update') {
      this.employee = employees;
      button.setAttribute('data-bs-target', "#editEmployeeModal");

    }
    else if (mode === 'delete') {
      button.setAttribute('data-bs-target', "#deleteEmployeeModal");
      let getEmployeeName = employees?.name;
      this.employeeName = getEmployeeName;
      this.employee = employees;
    }
    container?.appendChild(button);
    button.click();
  }

  public onAddEmployee(addForm : NgForm):void{
    this.employesService.addEmployee(addForm.value).subscribe(
      (response: Employees)=>{
        console.log(response);
        this.getEmployees();
        addForm.reset();
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
        addForm.reset()
      }
    )
  }

  public onUpdateEmployee(idEmployee : number, employee :Employees):void{
    console.log("id employee:" +idEmployee);
    this.employesService.updateEmployes(idEmployee, employee).subscribe(
      (response: Employees)=>{
        console.log(response);
        this.getEmployees();
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    )
  }

  public onDeleteEmployee(idEmployee : number):void{
    console.log("id employee:" +idEmployee);
    this.employesService.deleteEmployee(idEmployee).subscribe(
      (response:void)=>{
        console.log("Deleted employee");
        this.getEmployees();
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    )
  }

  public searchEmployee(key : string):void{
    const results : Employees[] = [];
    for(const employee of this.employees){
      if(employee.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.email.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.phoneNumber.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.jobTitle.toLowerCase().indexOf(key.toLowerCase()) !== -1){
        results.push(employee);
      }
    }
    this.employees = results;
    if(results.length ===0 || !key){
      this.getEmployees();
    }

  }



}
