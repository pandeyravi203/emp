package com.emp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author ravi
 */
@Controller
public class IndexController {

    @GetMapping("/adminPage")
    public String adminPage() {
        return "index";
    }
    @GetMapping("/homePage")
    public String homePage() {
        return "login";
    }
}
