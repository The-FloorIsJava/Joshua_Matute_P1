package com.revature.employeereimbursementsystem.Service;
import com.revature.employeereimbursementsystem.DAO.EmployeeDAO;
import com.revature.employeereimbursementsystem.Model.Employee;

import java.util.List;


public class EmployeeService {

    private Employee sessionEmployee = null;
    private final EmployeeDAO employeeDAO;

    /*
        EmployeeService public method is a constructor of the
        private final EmployeeDAO method.
    */
    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public Employee addEmployee(Employee employee){
        return employeeDAO.create(employee);
    }

    public Employee getEmployee(int employeeID){
        return null;
    }

    public void removeEmployee(int employeeID){

    }

    public List<Employee> getAllEmployees(){
        return null;
    }

    // code for reimbursement ticket requests starts below
}
