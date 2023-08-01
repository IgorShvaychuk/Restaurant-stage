package com.restaurant.app.management.dao;

import com.restaurant.app.management.model.Customer;
import com.restaurant.app.management.model.MenuItem;
import com.restaurant.app.management.model.OrderHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface OrderHistoryDao extends CrudRepository<OrderHistory, Integer> {
    
    List<OrderHistory> findByCustomer(Customer c);
    List<OrderHistory> findByMenuItem(MenuItem mi);
    List<OrderHistory> findByCustomerOrderByOrderDateDesc(Customer c);
    List<OrderHistory> findByCustomerOrderByQuantityDesc(Customer c);
    OrderHistory findByCustomerAndMenuItem(Customer c, MenuItem mi);
    
    // TODO -
	List<OrderHistory> findAll();
	OrderHistory findByUid(int Uid);
}

