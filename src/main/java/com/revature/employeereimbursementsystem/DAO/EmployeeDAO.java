package com.revature.employeereimbursementsystem.DAO;
import com.revature.employeereimbursementsystem.Model.Employee;
import com.revature.employeereimbursementsystem.Util.ConnectionFactory;
import com.revature.employeereimbursementsystem.Util.Exceptions.InvalidEmployeeInputException;
import com.revature.employeereimbursementsystem.Util.Interface.Crudable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/*

    This is the DAO that speaks to the database.

 */
public class EmployeeDAO implements Crudable<Employee> {
    @Override
    public Employee create(Employee newEmployee) {

        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "INSERT INTO employee (employee_id, employee_email, employee_isManager, employee_pwd) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, newEmployee.getEmployee_id());
            preparedStatement.setString(2, newEmployee.getEmployeeEmail());
            preparedStatement.setBoolean(3, newEmployee.getisManager());
            preparedStatement.setString(4, newEmployee.getPassword());

            int checkInsert = preparedStatement.executeUpdate();

            if (checkInsert == 0) {
                throw new RuntimeException("Employee was not added to database");
            }
            return newEmployee;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Employee loginCheck(int employee_id, String password) throws InvalidEmployeeInputException {

        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "SELECT * FROM employee WHERE employee_id = ? AND employee_pwd = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, employee_id);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()) {
                throw new InvalidEmployeeInputException("Entered information for " + employee_id + "was incorrect. Try again.");
            }
                return convertSqlInfoToEmployee(resultSet);

        } catch (SQLException e) {
                throw new RuntimeException(e);
        }

    }

    @Override
    public List<Employee> findAll() {

        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            List<Employee> employees = new ArrayList<>();

            String sql = "select * from employee";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                employees.add(convertSqlInfoToEmployee(resultSet));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private Employee convertSqlInfoToEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();

        employee.setEmployee_id(resultSet.getInt("employee_id"));
        employee.setEmployeeEmail(resultSet.getString("employee_email"));
        employee.setPassword(resultSet.getString("employee_pwd"));
        employee.setIsManager(resultSet.getBoolean("default"));
        return employee;
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
}

