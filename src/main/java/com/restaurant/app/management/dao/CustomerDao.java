package com.restaurant.app.management.dao;

import com.restaurant.app.management.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CustomerDao extends CrudRepository<Customer, Integer> {

    Customer findByUid(int uid);
    
    List<Customer> findAll();

    Customer findByPhoneNumber(String phoneNumber);
   //  List<OrderHistory> findAllByOrderHistory();
}
