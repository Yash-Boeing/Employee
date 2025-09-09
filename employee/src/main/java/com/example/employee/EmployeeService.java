package com.example.employee;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private  final EmployeeRepository repository;
    public EmployeeService(EmployeeRepository repository){
        this.repository=repository;
    }

    public String createEmployee(Employee employee){

        if(repository.existsById(employee.getId()) ){
            return "Id already exists.";
        } else if (!(employee.getId().toString().length()==7))
            return "Id should be of 7 digit";
        else {
            repository.save(employee);
            return "Id : "+employee.getId()+"\nCreated successfully";
        }
    }

    public List getEmp(){
        return repository.findAll();
    }

    public Optional<Employee> getEmpById(Long Id){
        return repository.findById(Id);
    }

    public String delEmp(Long Id){
        Employee emp=repository.findById(Id).get();
            repository.delete(emp);
            return "Id : "+Id+" Deleted Successfully";
    }

    public String upEmp(Employee employee){
        Employee emp=repository.findById(employee.getId()).get();
        emp.setName(employee.getName());
        emp.setProject(employee.getProject());
        emp.setRole(employee.getRole());
        repository.save(emp);
        return "Id : "+emp.getId()+"\nUpdated Successfully";
    }
}
