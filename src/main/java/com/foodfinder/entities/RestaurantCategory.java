package com.foodfinder.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jerson Peña
 * @version 1.0
 * 
 *          Clase que contiene el método y variables de restaurantCategory
 *          
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categoria_restaurante")
@EntityListeners(AuditingEntityListener.class)
public class RestaurantCategory implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_categoria")
	private Integer id;
	
	@Basic(optional = false)
	@Column(name = "nombre")
	private String name;
	
	@Basic(optional = false)
	@Column(name = "descripcion")
	private String description;
	
}
