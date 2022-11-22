package com.revature.employeereimbursementsystem.Service;
import com.revature.employeereimbursementsystem.DAO.EmployeeDAO;
import com.revature.employeereimbursementsystem.Model.Employee;
import com.revature.employeereimbursementsystem.Util.Exceptions.InvalidEmployeeInputException;

import java.util.List;

public class EmployeeService {


    private final EmployeeDAO employeeDAO;
    private Employee sessionEmployee = null;

    public EmployeeService(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    public int login (Employee employee) {

        int temp = 0;

        try {
            int employee_id = employee.getEmployee_id();
            String password = employee.getPassword();
            Employee approved = employeeDAO.loginCheck(employee_id, password);

            if (approved != null) {
                sessionEmployee = approved;
                temp = 1;
            }
        } catch (InvalidEmployeeInputException e){
            e.printStackTrace();
            temp = 2;
        }
        return temp;
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



