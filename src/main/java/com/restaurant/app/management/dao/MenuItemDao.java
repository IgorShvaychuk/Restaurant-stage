package com.restaurant.app.management.dao;

import com.restaurant.app.management.model.MenuItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface MenuItemDao extends CrudRepository<MenuItem, Integer> {

    MenuItem findByUid(int uid);
    List <MenuItem> findByStatus(String status);
    
    List<MenuItem> findAll();

    MenuItem findByName(String name);
}

