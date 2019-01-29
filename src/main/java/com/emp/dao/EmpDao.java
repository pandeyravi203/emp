/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emp.dao;

import com.emp.model.Emp;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ravi
 */
public interface EmpDao extends JpaRepository<Emp, Long> {
	
}
