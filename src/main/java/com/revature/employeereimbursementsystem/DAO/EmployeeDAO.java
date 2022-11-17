package com.revature.employeereimbursementsystem.DAO;
import com.revature.employeereimbursementsystem.Model.Employee;
import com.revature.employeereimbursementsystem.Util.ConnectionFactory;
import com.revature.employeereimbursementsystem.Util.Interface.Crudable;

import java.sql.Connection;
import java.util.List;


/*

    This is the DAO that speaks to the database.

 */
public class EmployeeDAO implements Crudable<Employee> {
    @Override
    public Employee create(Employee newObject) {

        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection())
        {
            String sql = "insert into employee ()";

        } catch (Exception e){

        }
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Employee findByID(int id) {
        return null;
    }

    @Override
    public boolean update(Employee updatedObject) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    public Employee loginCheck(int employeeID, String password){
        return null;
    }
}
