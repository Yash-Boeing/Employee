package com.example.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
public class EmployeeController {
    @Autowired
    private EmployeeRepository repository;
    @Autowired
//    private DepartmentRepository deptRepo;


    @GetMapping("/")
    public String hello() {
        return ("HelloWorld");
    }

    @GetMapping("/ping")
    public String PP() {
        return ("Pong");
    }

    @GetMapping("/getEmployee")
    public ResponseEntity<?> getAllEmploy(){
        if(repository.findAll().isEmpty()){
            return ResponseEntity.status(404).body("No Employee exists");
        }
        else{
            List<Employee> employees=repository.findAll();
            return ResponseEntity.ok(employees);
        }
    }

    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<?> getEmpById(@PathVariable Long id){
        if(repository.existsById(id)){
            return ResponseEntity.ok(repository.findById(id).get());
        }
        else{
            return ResponseEntity.status(404).body("Employee doesn't Exist");
        }
    }

    @PostMapping("/createEmployee")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee){
//        System.out.println(employee);
        if(repository.existsById(employee.getId())){
            return ResponseEntity.status(404).body("BEMSID already exist");
        }
        else {
            repository.save(employee);
            return ResponseEntity.ok("Id: "+employee.getId()+"\n Created Successfully");
        }
    }

//    @PostMapping("/department")
//    public String addDept(@RequestBody Department department){
//        deptRepo.save(department);
//        return "ResponseEntity.ok(department)";
//    }


    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<?> updateEmp(@PathVariable Long id,@RequestBody Employee newEmployee){
        if(repository.existsById(id)) {
            Employee employee = repository.findById(id).get();
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            employee.setProject(newEmployee.getProject());
            repository.save(employee);
            return ResponseEntity.ok("Id: "+employee.getId()+"\n Updated Successfully");
        }
        else{
            return ResponseEntity.status(404).body("Employee doesn't Exist");
        }
    }

    @DeleteMapping("/delEmployee/{id}")
    public ResponseEntity<?>  delEmp(@PathVariable Long id){
        if(repository.existsById(id)){
            Employee employee=repository.findById(id).get();
            repository.delete(employee);
            return ResponseEntity.ok("Id: "+employee.getId()+"\n Deleted Successfully");
        }
        else{
            return ResponseEntity.status(404).body("Employee doesn't Exist");
        }

    }




}
//@RestController("/dept")
//@CrossOrigin
//class DepartmentController{
//
//    @Autowired
//    private DepartmentRepository deptRepo;
//
//    @GetMapping
//    public ResponseEntity<?> getDept(){
//        return (ResponseEntity<?>) ResponseEntity.ok("Department is fetched.");
//    }
//
//    @PostMapping("/{id}")
//    public ResponseEntity<?> setDept(@PathVariable Long id){
//        if(!deptRepo.existsById(id))
//            return ResponseEntity.status(404).body("No information");
//        else{
//            return ResponseEntity.ok(deptRepo.findById(id));
//        }
//    }
//}
