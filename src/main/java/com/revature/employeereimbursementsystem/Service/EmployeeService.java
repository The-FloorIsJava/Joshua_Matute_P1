package com.revature.employeereimbursementsystem.Service;

import com.revature.employeereimbursementsystem.DAO.EmployeeDAO;
import com.revature.employeereimbursementsystem.DAO.TicketDAO;
import com.revature.employeereimbursementsystem.Model.Employee;
import com.revature.employeereimbursementsystem.Model.Ticket;
import com.revature.employeereimbursementsystem.Util.Exceptions.InvalidEmployeeInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

// business logic is performed here.
// holds session information.
// checks incoming information and validates based on the business needs.

public class EmployeeService {
    private Employee sessionEmployee = null;
    private final EmployeeDAO employeeDAO;
    private final TicketDAO ticketDAO;

    private Logger logger = LogManager.getLogger();

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

    public List<Employee> getAllEmployees(){
        return employeeDAO.findAll();
    }

    public Employee registerEmployee(Employee employee) {
        return employeeDAO.create(employee);
    }


    public Employee login(String employeeUsername, String password) {
        return employeeDAO.loginCheck(employeeUsername, password);
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



