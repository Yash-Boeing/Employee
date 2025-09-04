package com.example.employee;
import jakarta.persistence.*;

@Entity
public class Employee {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String name;
    private String project;
    private String role;

//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private Department department;

    public Employee(){
    }

    public Employee(Long id,String name,String project,String role/*, Department department */){
        this.name=name;
        this.role=role;
        this.id=id;
        this.project=project;
//        this.department=department;

    }

    public void Emp(){
        this.project=project;
        this.id=getId();
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public void setRole(String role){
        this.role=role;
    }
    public String getRole(){
        return role;
    }

    public void setProject(String project){
        this.project=project;
    }
    public String getProject(){
        return project;
    }

//    public Department getDepartment(){
//        return department;
//    }
//    public void setDepartment(Department department){
//        this.department=department;
//    }

}


