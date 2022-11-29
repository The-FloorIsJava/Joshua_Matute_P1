package com.revature.employeereimbursementsystem.DAO;

import com.revature.employeereimbursementsystem.Model.Employee;
import com.revature.employeereimbursementsystem.Model.Ticket;
import com.revature.employeereimbursementsystem.Util.ConnectionFactory;
import com.revature.employeereimbursementsystem.Util.Interface.Crudable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TicketDAO implements Crudable<Ticket> {


    /* Created a new Ticket request by using my connection factory, calling my sql tables columns and values and using
        preparedStatements to set the value to the newly created tickets by getting the values from the Ticket model.
        I then run a check to ensure that the submitted ticket is a valid submission. a runtime exception is added to
        catch an error that may happen when an invalid input is entered. a sql exception is used to catch any errors
        that may happen during the use of Javalin.
     */
    @Override
    public Ticket create(Ticket newTicket) {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "insert into tickets (amount, ticket_type, status, requester) values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, newTicket.getAmount());
            preparedStatement.setString(2, newTicket.getTicketType());
            preparedStatement.setString(3, newTicket.getStatus());
            preparedStatement.setString(4, newTicket.getRequester());


            int checkInsert = preparedStatement.executeUpdate();

            if (checkInsert == 0) {
                throw new RuntimeException("Your ticket was not added to the database.");
            } else {
                return newTicket;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

        /*
           a List is populated with employees submitted reimbursement tickets and then instantiated into an array list.
        then the connection factory, calling my sql tables columns and values and using
        preparedStatements to set the value to the newly created tickets by getting the values from the Ticket model.
        I then run a check to ensure that the submitted ticket is a valid submission.
         */
    public List<Ticket> returnEmployeeTickets(Employee employee) {
        List<Ticket> employeeTickets = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "select * from tickets where requester = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getEmployeeUsername());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employeeTickets.add(convertSqlInfoToTicket(resultSet));
            }
            return employeeTickets;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    public List<Ticket> approvedEmployeeTickets(Employee employee) {
        List<Ticket> employeeTickets = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "select * from tickets where requester = ? and status = 'Approved'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getEmployeeUsername());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employeeTickets.add(convertSqlInfoToTicket(resultSet));
            }
            return employeeTickets;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Ticket> deniedEmployeeTickets(Employee employee) {
        List<Ticket> employeeTickets = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "select * from tickets where requester = ? and status = 'Denied'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getEmployeeUsername());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employeeTickets.add(convertSqlInfoToTicket(resultSet));
            }
            return employeeTickets;
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Ticket> returnPendingTickets() {
        List<Ticket> pendingTickets = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "select * from tickets where status = 'Pending'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                pendingTickets.add(convertSqlInfoToTicket(resultSet));
            }
            return pendingTickets;
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public void updateTicket(double ticket_id, String status){
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "update tickets set status = ? where ticket_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setDouble(2, ticket_id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Ticket convertSqlInfoToTicket(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();

        ticket.setAmount(resultSet.getDouble("amount"));
        ticket.setTicketID(resultSet.getDouble("ticket_id"));
        ticket.setTicketType(resultSet.getString("ticket_type"));
        ticket.setStatus(resultSet.getString("status"));
        ticket.setRequester(resultSet.getString("requester"));

        return ticket;
    }

        @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public Employee findById(int employee_id) {
        return null;
    }

    @Override
    public Ticket findByID(int id) {
        return null;
    }

    @Override
    public boolean update(Ticket updatedObject) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }


}



