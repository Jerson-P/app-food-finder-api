package com.foodfinder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodfinder.entities.restaurantImages;

/**
 * @author Jerson Peña
 * @version 1.0
 * 
 *          Clase que contiene el método y variables de restaurantImagesRepository
 *          
 */

@Repository
public interface restaurantImagesRepository extends JpaRepository<restaurantImages, Integer>{

}
