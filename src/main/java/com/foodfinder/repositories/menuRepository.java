package com.foodfinder.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodfinder.entities.menu;

/**
 * @author Luis Montoya
 * @version 1.0
 * 
 *          Clase que contiene el método y variables de menuRepository
 *          
 */

@Repository
public interface menuRepository extends JpaRepository<menu, Integer>{
	
}
