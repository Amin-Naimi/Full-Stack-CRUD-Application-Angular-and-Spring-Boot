package atlier.controlleur;

import atlier.Entity.Employee;
import atlier.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
@RequiredArgsConstructor
@CrossOrigin
public class EmployeeControlleur {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/all")
    //return http responce
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    //return http responce
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id){
        Employee employees = employeeService.getEmployee(id);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee employee1 = employeeService.addEmployee(employee);
        return new ResponseEntity<>(employee1,HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(employee, id);
        if (updatedEmployee != null) {
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> addEmployee(@PathVariable ("id") Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
