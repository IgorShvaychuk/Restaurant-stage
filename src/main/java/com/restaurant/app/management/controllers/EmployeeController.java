package com.restaurant.app.management.controllers;

import com.restaurant.app.management.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class EmployeeController extends AbstractController {

    @RequestMapping(value = "/")
    public String index(Model model){

        List<Employee> employees =employeeDao.findAll();
        model.addAttribute("employees", employees);
        return "index";
    }
}
