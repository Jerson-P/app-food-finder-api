package com.foodfinder.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Luis Montoya
 * @version 1.0
 * 
 *          Clase que contiene el método y variables de menuImages
 *          
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "imagenes_menu")
public class MenuImages implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_imagen")
	private Integer id;
	
	@Basic(optional = false)
	@Column(name = "imagen")
	private String rutaimagen;
	
	@JoinColumn(name = "id_menu", referencedColumnName = "id_menu")
	@ManyToOne
	private Menu menu;
	
}
