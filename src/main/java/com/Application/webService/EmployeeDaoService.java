package com.Application.webService;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Component
public class EmployeeDaoService {
    private static List<EmployeeBean> employee= new ArrayList<EmployeeBean>();
    private static int countId=3;
    static{
        employee.add(new EmployeeBean(1,"Abhisehk Bhardwaj",23));
        employee.add(new EmployeeBean(2,"Justin Devassy",23));
        employee.add(new EmployeeBean(3,"Sourabh Singh",24));
    }
    public List<EmployeeBean> getEmployee(){
        return employee;
    }
    public EmployeeBean getEmployeeById(int id){
        for(EmployeeBean emp:employee)
        {
            if(emp.getId()==id)
                return emp;
        }
        return null;
    }
    public EmployeeBean updateEmployeeById(EmployeeBean emp1){
        for( EmployeeBean emp:employee)
        {
            if(emp1.getId()==emp.getId())
            {
                emp.setName(emp1.getName());
                emp.setAge(emp1.getAge());
                return emp;
            }
        }
        return null;
    }
    public EmployeeBean saveEmployee(EmployeeBean emp)
    {
        if(emp.getId()==null){
            emp.setId(++countId);
        }
        employee.add(emp);
        return emp;
    }
    public EmployeeBean deleteEmployeeById(int id){
        Iterator<EmployeeBean> iterator = employee.iterator();
        while(iterator.hasNext())
        {
            EmployeeBean emp=iterator.next();
            if(emp.getId()==id){
                iterator.remove();
                return emp;
            }
        }
        return null;
    }
}
