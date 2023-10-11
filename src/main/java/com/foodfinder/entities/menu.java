package com.foodfinder.entities;

import java.io.Serializable;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Luis Montoya
 * @version 1.0
 * 
 *          Clase que contiene el método y variables de menu
 *          
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menu")
public class Menu implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_menu")
	private Integer id;
	
	@Basic(optional = false)
	@Column(name = "nombre")
	private String name;
	
	@Basic(optional = false)
	@Column(name = "descripcion")
	private String description;
	
	@Basic(optional = false)
	@Column(name = "precio")
	private float price;
	
	@Basic(optional = false)
	@Column(name = "disponibilidad")
	private Integer availability;
	
	@JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
	@OneToOne
	private MenuCategory categoria;
	
	@JoinColumn(name = "id_restaurante", referencedColumnName = "id_restaurante")
	@OneToOne
	private Restaurant restaurant; 
}
