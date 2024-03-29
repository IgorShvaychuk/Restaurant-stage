package com.restaurant.app.management.security;

import com.restaurant.app.management.controllers.AbstractController;
import com.restaurant.app.management.model.Employee;
import com.restaurant.app.management.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
    	
    	//restricted URLs
        List<String> authPages = Arrays.asList("/customerlookup", "/createcustomer", "/menu", "/addmenuitem", "/customerinformation", "/updatemenuitem");

        // Require sign-in for auth pages
        if ( authPages.contains(request.getRequestURI()) ) {

        	boolean isLoggedIn = false;
        	Employee user;
            Integer userId = (Integer) request.getSession().getAttribute(AbstractController.userSessionKey);

            if (userId != null) {
            	user = employeeDao.findByUid(userId);
            	
            	if (user != null) {
            		isLoggedIn = true;
            	}
            }

            // If user not logged in, redirect to login page
            if (!isLoggedIn) {
                response.sendRedirect("/login");
                return false;
            }
        }

        return true;
    }

}