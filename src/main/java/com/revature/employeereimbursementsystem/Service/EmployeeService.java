package com.revature.employeereimbursementsystem.Service;
import com.revature.employeereimbursementsystem.DAO.EmployeeDAO;
import com.revature.employeereimbursementsystem.Model.Employee;

import java.util.List;
import java.util.ArrayList;

public class EmployeeService {

    private Employee sessionEmployee = null;
    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    public void login() {
        sessionEmployee = null;
    }

    public void logout() {
        sessionEmployee = null;
    }

    public Employee registerNewEmployee(Employee employee){
        return employeeDAO.create(employee);
    }


    public Employee getSpecificEmployee (int employeeID){
        return null;
    }

    public List<Employee> getAllEmployees(){
        return null;
    }
    public Employee getSessionEmployee(){
        return  sessionEmployee;
    }

    }



