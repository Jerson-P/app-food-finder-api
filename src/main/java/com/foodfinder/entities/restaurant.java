package com.foodfinder.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jerson Peña
 * @version 1.0
 * 
 *          Clase que contiene el método y variables de restaurant
 *          
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurantes")
@EntityListeners(AuditingEntityListener.class)
//@Where(clause = "activo = true")
public class restaurant implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_restaurante")
	private Integer id;
	
	@Basic(optional = false)
	@Column(name = "nombre")
	private String name;
	
	@Basic(optional = false)
	@Column(name = "correo")
	private String email;
	
	@Basic(optional = false)
	@Column(name = "direccion")
	private String adress;
	
	@Basic(optional = false)
	@Column(name = "telefono")
	private String phone;
	
	@Basic(optional = false)
	@Column(name = "nit")
	private Integer nit;
	
	@Basic(optional = false)
	@Column(name = "estado")
	private Integer status;
	
	@JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
	@OneToOne
	private restaurantCategory category;
	
	@JoinColumn(name = "id_detalle", referencedColumnName = "id_detalle")
	@OneToOne
	private restaurantDetail detail;
	
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
	@OneToOne
	private user user;
	
}
