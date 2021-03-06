package com.Application.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class EmployeeService {
    @Autowired
    private EmployeeDaoService service;
    @GetMapping("/employees")
    public List<EmployeeBean> retrieveAllEmployee(){
        return service.getEmployee();
    }
    @GetMapping("/employee/{id}")
    public EmployeeBean retrieveEmployeeById(@PathVariable int id)
    {
        EmployeeBean emp= service.getEmployeeById(id);
        if(emp==null)
            throw new EmployeeNotFoundException("id-"+id);
        return emp;
    }
    @PostMapping("/employees")
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody EmployeeBean emp){
        EmployeeBean empSaved=service.saveEmployee(emp);
        URI location=ServletUriComponentsBuilder
                .fromCurrentRequest()//return current request
                .path("/{id}").//appending id to employees
                buildAndExpand(empSaved.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/employee/{id}")
    public void deleteEmployeeId(@PathVariable int id)
    {
       EmployeeBean emp= service.deleteEmployeeById(id);
       if(emp==null)
           throw new EmployeeNotFoundException("id-"+id);
    }
    @PutMapping("/employees")
    public ResponseEntity<Object> updateEmployeeById(@Valid @RequestBody EmployeeBean emp)
    {
        EmployeeBean empUpdated= service.updateEmployeeById(emp);
        if(empUpdated==null)
            throw new EmployeeNotFoundException("Id not found");
        URI location=ServletUriComponentsBuilder
                .fromCurrentRequest()//return current request
                .path("/{id}").//appending id to employees
                buildAndExpand(empUpdated.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
