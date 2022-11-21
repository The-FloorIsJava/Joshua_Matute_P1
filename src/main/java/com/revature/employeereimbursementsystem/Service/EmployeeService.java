package com.revature.employeereimbursementsystem.Service;
import com.revature.employeereimbursementsystem.DAO.EmployeeDAO;
import com.revature.employeereimbursementsystem.Model.Employee;

import java.util.List;

public class EmployeeService {

    private Employee sessionEmployee = null;
    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    public void login(int employee_id, String password) {
        sessionEmployee = employeeDAO.loginCheck(employee_id, password);
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



