package com.foodfinder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.foodfinder.entities.MenuImages;

/**
 * @author Luis Montoya
 * @version 1.0
 * 
 *          Clase que contiene el método y variables de menuImagesRepository
 *          
 */

@Repository
public interface MenuImagesRepository extends JpaRepository<MenuImages, Integer> {
	
	long countMenuImagesById(Integer id);
	
	@Query(value = "SELECT * FROM imagenes_menu ORDER BY id_menu DESC LIMIT 1", nativeQuery = true)
	MenuImages findLastInsertedMenuImage();

}
