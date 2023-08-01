package com.restaurant.app.management.dao;

import com.restaurant.app.management.model.CartItem;
import com.restaurant.app.management.model.Customer;
import com.restaurant.app.management.model.MenuItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CartItemDao extends CrudRepository<CartItem, Integer> {

	CartItem findByUid(int uid);

	List<CartItem> findByCustomer(Customer customer);

	List<CartItem> findAll();

	CartItem findByCustomerAndMenuItem(Customer customer, MenuItem menuItem);

}
