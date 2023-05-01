package atlier.Service;

import atlier.Entity.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee addEmployee(Employee employee);

    public List<Employee> findAllEmployees();

    public Employee updateEmployee(Employee employee, Long id);

    public Employee getEmployee(Long id);

    public void deleteEmployee(Long id);
}
