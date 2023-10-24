package com.foodfinder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.foodfinder.entities.RestaurantCategory;

/**
 * @author Jerson Peña
 * @version 1.0
 * 
 *          Clase que contiene el método y variables de restaurantCategoryRepository
 *          
 */

@Repository
public interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategory, Integer>{

	long countRestaurantCategoryById(Integer id);
	
	@Query(value = "SELECT * FROM categoria_restaurante ORDER BY id_categoria DESC LIMIT 1", nativeQuery = true)
	RestaurantCategory findLastInsertedRestaurantCategory();
}
