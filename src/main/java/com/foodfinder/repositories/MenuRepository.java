package com.foodfinder.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodfinder.entities.Menu;

/**
 * @author Luis Montoya
 * @version 1.0
 * 
 *          Clase que contiene el método y variables de menuRepository
 *          
 */

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer>{
	
	long countMenuById(Integer id);
	
}
