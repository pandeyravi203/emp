package com.emp.controller;

/**
 *
 * @author ravi
 */
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.dao.EmpDao;
import com.emp.model.Emp;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/employee")
public class EmpController {

    @Autowired
    EmpDao empDao;
    
    
    @PostMapping("/")
    public Emp createEmp(@RequestBody Emp emp) {
        return empDao.save(emp);
    }

    @GetMapping("/")
    public List<Emp> allEmp() {
        return empDao.findAll();
    }

    @GetMapping("/{empid}")
    public ResponseEntity<Emp> getEmpById(@PathVariable(value = "empid") Long empid){
       Emp employee = empDao.getOne(empid);
        if (employee == null) {
            
            return ResponseEntity.notFound().build();
            
        }
        return ResponseEntity.ok().body(employee);
    }

    @PutMapping("/{empid}")
    public ResponseEntity<Emp> updateEmpoyee(@PathVariable(value = "empid") Long empid, @RequestBody Emp emp) {
        Emp employee = empDao.getOne(empid);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        employee.setFirstname(emp.getFirstname());
        employee.setLastname(emp.getLastname());
        employee.setAddress(emp.getAddress());
        employee.setDesignation(emp.getDesignation());
        employee.setEmail(emp.getEmail());
        return ResponseEntity.ok().body(empDao.save(employee));
    }

    @DeleteMapping("/{empid}")
    public ResponseEntity<Emp> deleteEmp(@PathVariable(value = "empid") Long empid) {
        Emp employee = empDao.getOne(empid);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        empDao.delete(employee);
        return ResponseEntity.ok().build();
    }

}
