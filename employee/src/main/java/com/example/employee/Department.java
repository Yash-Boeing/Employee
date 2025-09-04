//package com.example.employee;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//
//import java.util.List;
//
//@Entity
//public class Department {
//    @Id
//    private Long id;
//    private String name;
//    @OneToMany(mappedBy = "department")
//    private List<Employee> employees;
//
//    public Department(){
//
//    }
//    public Department(Long id,String name){
//        this.id=id;
//        this.name=name;
//    }
//    public Long getId(){
//        return id;
//    }
//    public void setId(Long id){
//        this.id=id;
//    }
//
//    public String getName(){
//        return name;
//    }
//    public void setName(String name){
//        this.name=name;
//    }
//    public String getDetails(){
//        return "Name : "+this.name+"BEMSID : "+this.id;
//    }
//
//    public List<Employee> getEmployees(){
//        return employees;
//    }
//    public void setEmployees(List<Employee> employees){
//        this.employees=employees;
//    }
//}
