package com.revature.employeereimbursementsystem.Service;
import com.revature.employeereimbursementsystem.DAO.EmployeeDAO;
import com.revature.employeereimbursementsystem.Model.Employee;

import java.util.List;

public class EmployeeService {


    private final EmployeeDAO employeeDAO;
    private Employee sessionEmployee = null;

    public EmployeeService(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    public Employee login(int employee_id, String password) {
        sessionEmployee = employeeDAO.loginCheck(employee_id, password);
        return this.sessionEmployee;
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
        return employeeDAO.findAll();
    }
    public Employee getSessionEmployee(){
        return  sessionEmployee;
    }


}



