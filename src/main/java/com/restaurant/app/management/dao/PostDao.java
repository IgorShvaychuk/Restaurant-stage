package com.restaurant.app.management.dao;

import com.restaurant.app.management.model.Employee;
import com.restaurant.app.management.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface PostDao extends CrudRepository<Post, Integer> {
    
    List<Post> findByAuthor(Employee u);
    
    // TODO - add method signatures as needed
	List<Post> findAll();
	Post findByUid(int Uid);
}
