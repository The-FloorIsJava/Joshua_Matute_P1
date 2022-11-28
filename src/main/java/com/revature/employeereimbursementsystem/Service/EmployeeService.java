package com.revature.employeereimbursementsystem.Service;
import com.revature.employeereimbursementsystem.DAO.EmployeeDAO;
import com.revature.employeereimbursementsystem.DAO.TicketDAO;
import com.revature.employeereimbursementsystem.Model.Employee;
import com.revature.employeereimbursementsystem.Model.Ticket;
import com.revature.employeereimbursementsystem.Util.ConnectionFactory;
import com.revature.employeereimbursementsystem.Util.DTO.TicketDTO;
import com.revature.employeereimbursementsystem.Util.Exceptions.InvalidEmployeeInputException;
import org.eclipse.jetty.util.DateCache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private Employee sessionEmployee = null;
    private final EmployeeDAO employeeDAO;
    private final TicketDAO ticketDAO;

    public EmployeeService(EmployeeDAO employeeDAO, TicketDAO ticketDAO) {
        this.employeeDAO = employeeDAO;
        this.ticketDAO = ticketDAO;
    }

    public Employee getSessionEmployee() {
        return sessionEmployee;
    }

    public void setSessionEmployee(Employee employee) {
        this.sessionEmployee = employee;
    }

    public Employee registerEmployee(Employee employee) {
        return employeeDAO.create(employee);
    }


    public int login(Employee employee) {
        int temp = 0;
        try {
            String username = employee.getEmployeeUsername();
            String password = employee.getPassword();
            Employee authenticated = employeeDAO.loginCheck(username, password);

            if (authenticated != null) {
                this.setSessionEmployee(authenticated);
                temp = 1;
            }

        } catch (InvalidEmployeeInputException e) {
            e.printStackTrace();
            temp = 2;
        }
        return temp;
    }
    public List<Employee> getAllEmployees() {
        return employeeDAO.findAll();
    }


    // TICKET REQUEST FEATURES

    public double submitTicket(Ticket ticket) {

        try {
            Employee requester = this.getSessionEmployee();
            int temp = 0;
            if (requester == null) {
                temp = 1;
            } else if (ticket.getAmount() != 0 && ticket.getTicketType() != null) {
                ticket.setStatus("Pending");
                ticket.setRequester(requester.getEmployeeUsername());
                ticketDAO.create(ticket);
                temp = 2;
            } else {
                temp = 3;
            }
            return temp;

        } catch (RuntimeException r) {
            r.printStackTrace();
            System.out.println("This ticket does not exist in the ERS.");
        }
        return 0;
    }

    public List<Ticket> employeeTickets(Employee employee) {
        return ticketDAO.returnEmployeeTickets(employee);
    }


    //Manager features.

    public List<Ticket> viewPendingEmployeeTickets(Employee employee) {
            return ticketDAO.returnPendingTickets();

    }

    public List<Ticket> approvedEmployeeTickets(Employee employee) {
            return ticketDAO.approvedEmployeeTickets(employee);
}

    public List<Ticket> deniedEmployeeTickets(Employee employee) {
        return ticketDAO.deniedEmployeeTickets(employee);
    }

    public void updateTicket(double ticket_id, String status) {
        ticketDAO.updateTicket(ticket_id, status);
    }

    // LOGOUT
    public void logout() {
        sessionEmployee = null;
    }
}



