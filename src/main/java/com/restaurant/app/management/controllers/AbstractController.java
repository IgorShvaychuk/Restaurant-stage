package com.restaurant.app.management.controllers;

import com.restaurant.app.management.model.Employee;
import com.restaurant.app.management.dao.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public abstract class AbstractController {

    @Autowired
    protected EmployeeDao employeeDao;

    @Autowired
    protected CustomerDao customerDao;

    @Autowired
    protected MenuItemDao menuItemDao;

    @Autowired
    protected CartItemDao cartItemDao;

    @Autowired
    protected OrderHistoryDao orderHistoryDao;


    public static final String userSessionKey = "user_id";

    protected Employee getUserFromSession(HttpSession session) {

        Integer userId = (Integer) session.getAttribute(userSessionKey);
        return userId == null ? null : employeeDao.findByUid(userId);
    }

    protected void setUserInSession(HttpSession session, Employee user) {
        session.setAttribute(userSessionKey, user.getUid());
    }

}
