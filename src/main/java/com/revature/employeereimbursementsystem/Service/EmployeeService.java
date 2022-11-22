package com.revature.employeereimbursementsystem.Service;
import com.revature.employeereimbursementsystem.DAO.EmployeeDAO;
import com.revature.employeereimbursementsystem.Model.Employee;
import com.revature.employeereimbursementsystem.Model.Ticket;
import com.revature.employeereimbursementsystem.Util.Exceptions.InvalidEmployeeInputException;

import java.util.List;

public class EmployeeService {
    private Employee sessionEmployee = null;
    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    public Employee registerEmployee(Employee employee) {
        return employeeDAO.create(employee);
    }

    public Employee getEmployee(String employee_email) {
        return null;
    }

    public void removeEmployee(String employee_email) {

    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.findAll();
    }

    public Ticket submitTicket(Ticket ticket) {
        return null;
    }


    public void login(String employeeEmail, String password) {
        sessionEmployee = employeeDAO.loginCheck(employeeEmail, password);
    }


    public void logout() {
         sessionEmployee = null;
    }

    public Employee getSessionEmployee(){
        return  sessionEmployee;
    }


}



