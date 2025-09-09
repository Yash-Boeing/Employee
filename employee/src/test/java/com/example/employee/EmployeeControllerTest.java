package com.example.employee;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

import java.util.Arrays;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void testGetAllEmployee() throws Exception {
        when(employeeService.getEmp())
                .thenReturn(Arrays.asList(new Employee(4875456L, "Yash", "Transformation", "dev")));

        mockMvc.perform(get("/getEmployee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Yash"));
    }

    @Test
    void testGetEmpById() throws Exception {
        Employee emp = new Employee(5555575L, "Yash", "Transformation", "Developer");
        when(employeeService.getEmpById(5555575L)).thenReturn(Optional.of(emp));

        mockMvc.perform(get("/getEmployee/{id}", 5555575))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5555575L));
    }

    @Test
    void testupdateEmployee() throws Exception {
        Employee past = new Employee(5555574L, "Yash", "Tranformation", "Dev");
        Employee present = new Employee(5555574L, "Raj", "Tranformation", "Dev");
        when(employeeService.getEmpById(5555574L)).thenReturn(Optional.of(present));
        when(employeeService.upEmp(any(Employee.class)))
                .thenReturn("Id : 5555574\nUpdated Successfully");
        mockMvc.perform(put("/updateEmployee/{id}", 5555574L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(past)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteEmployee() throws Exception {
        Employee delEmp = new Employee(5555575L, "Raj", "Tranformation", "Dev");
        when(employeeService.getEmpById(5555575L)).thenReturn(Optional.of(delEmp));
        when(employeeService.delEmp(5555575L)).thenReturn("Id : 5555575 Deleted Successfully");
        mockMvc.perform(delete("/delEmployee/{id}", 5555575L))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Id : 5555575 Deleted Successfully")));
    }

}