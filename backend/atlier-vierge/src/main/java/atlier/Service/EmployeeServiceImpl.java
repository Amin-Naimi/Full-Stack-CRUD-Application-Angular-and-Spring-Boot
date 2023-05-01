package atlier.Service;

import atlier.Entity.Employee;
import atlier.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            log.info("Employee found with id {}", id);
            Employee employeeToUpdate = employeeOptional.get();
            employeeToUpdate.setName(employee.getName());
            employeeToUpdate.setEmail(employee.getEmail());
            employeeToUpdate.setJobTitle(employee.getJobTitle());
            employeeToUpdate.setPhoneNumber(employee.getPhoneNumber());
            employeeToUpdate.setImageUrl(employee.getImageUrl());
            employeeRepository.save(employeeToUpdate);
            return employeeToUpdate;
        } else {
            log.info("Employee with id {} not found", id);
            return null;
        }
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
