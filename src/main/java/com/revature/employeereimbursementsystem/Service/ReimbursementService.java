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

    public ReimbursementModel getTicketID(double ticketID){
        for(int i = 0; i < reimbursementModelList.size(); i++){
            ReimbursementModel t = reimbursementModelList.get(i);
            if(t.getTicketID().equals(ticketID)){
                return reimbursementModelList.get(i);
            }
        }
        return null;
    }

    public void removeReimbursementModel(double ticketID){
        for (int i = 0; i < reimbursementModelList.size(); i++){
            ReimbursementModel t = reimbursementModelList.get(i);
            if(t.getTicketID().equals(ticketID)){
                reimbursementModelList.remove(i);
            }
        }
    }

    public List<ReimbursementModel> getAllReimbursementModels() {
        return reimbursementModelList;
    }

}
