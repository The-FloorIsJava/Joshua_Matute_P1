package com.revature.employeereimbursementsystem.Service;
import com.revature.employeereimbursementsystem.Model.ReimbursementModel;

import java.util.ArrayList;
import java.util.List;




public class ReimbursementService {

    List<ReimbursementModel> reimbursementModelList;

    public ReimbursementService(){

        reimbursementModelList = new ArrayList<>();

    }

    public void addTicket(double ticketID, double amount){

        ReimbursementModel newReimbursementModel = new ReimbursementModel(ticketID, amount);
        reimbursementModelList.add(newReimbursementModel);
    }

    public void addEmployee(ReimbursementModel employee){
        reimbursementModelList.add(employee);
    }

    // NOT SURE WHY .EQUALS IS GIVING ME TROUBLE?



}
