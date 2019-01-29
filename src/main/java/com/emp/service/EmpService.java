package com.emp.service;

import com.emp.dao.EmpDao;
import com.emp.model.Emp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpService {

    @Autowired
    EmpDao empDao;

    /* To save an Employee*/
    public Emp save(Emp emp) {
        return empDao.save(emp);
    }

    /* To Search all Employee*/
    public List<Emp> findAll() {
        return empDao.findAll();
    }

    /* To Search an Employee by empid*/
    public Emp getOne(Long empid) {
        return empDao.getOne(empid);

    }

    /* To delete an Employee*/

    public void delete(Long id) {
        empDao.delete(empDao.getOne(id));
    }
}
