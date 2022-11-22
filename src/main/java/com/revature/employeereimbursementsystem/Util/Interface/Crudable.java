package com.revature.employeereimbursementsystem.Util.Interface;
import com.revature.employeereimbursementsystem.Model.Employee;

import java.util.List;

/*
    This ensures our DAO layer is consistent
*/

public interface Crudable<T> {

    //create T: specifies
    T create(T newObject);

    //read
    List<T> findAll();

    Employee findById(int employee_id);

    T findByID(int id);

    //update
    boolean update(T updatedObject);

    //delete
    boolean  delete(int id);

}
