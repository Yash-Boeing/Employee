package com.example.employee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@CrossOrigin
@RestController
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service){
        this.service=service;
    }

    @GetMapping("/")
    public String hello() {
        return ("Backend is running.");
    }

    @GetMapping("/getEmployee")
    public ResponseEntity<?> getAllEmploy() {
        if(service.getEmp().isEmpty())
            return ResponseEntity.status(404).body("No employee exists");
        return ResponseEntity.ok(service.getEmp());
    }

    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<?> getEmpById(@PathVariable Long id) {
        Optional<Employee> emp=service.getEmpById(id);
        if(emp.isEmpty())
            return ResponseEntity.status(404).body("Employee doesn't exists.");
        return ResponseEntity.ok(emp.get());
    }

    @PostMapping("/createEmployee")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        String createEmp=service.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createEmp);
    }

//    @PostMapping("/department")
//    public String addDept(@RequestBody Department department){
//        deptRepo.save(department);
//        return "ResponseEntity.ok(department)";
//    }


    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<?> updateEmp(@PathVariable Long id, @RequestBody Employee newEmployee) {
        Optional<Employee> emp=service.getEmpById(id);
        if(emp.isEmpty())
            return ResponseEntity.status(404).body("Employee doesn't exists.");
        return ResponseEntity.ok(service.upEmp(newEmployee));
    }

    @DeleteMapping("/delEmployee/{id}")
    public ResponseEntity<?> delEmp(@PathVariable Long id) {
        Optional<Employee> emp=service.getEmpById(id);
        if(emp.isEmpty())
            return ResponseEntity.status(404).body("Employee doesn't exists.");
        return ResponseEntity.ok(service.delEmp(id));
    }
}





