package com.restaurant.app.management.dao;

import com.restaurant.app.management.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface EmployeeDao extends CrudRepository<Employee, Integer> {

    Employee findByUid(int uid);
    
    List<Employee> findAll();
    
    // TODO - add method signatures as needed
    Employee findByUsername(String username);
}
