package com.revature.employeereimbursementsystem.Service;

import com.revature.employeereimbursementsystem.DAO.TicketDAO;
import com.revature.employeereimbursementsystem.Model.Employee;
import org.eclipse.jetty.util.DateCache;

public class TicketService {

    private final TicketDAO ticketDAO;

    public TicketService(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }



}
