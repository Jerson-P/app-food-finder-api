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

import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nicope
 * @version 1.0
 * 
 *          Clase Entity de tipo (user) que representa un registro de
 *          la BD. Cada instancia de esta entidad representa un registro de la
 *          BD. Cada atributo representa una columna de la BD. Los métodos de
 *          esta clase se usan para manipular los datos. (Anotación @Data)
 * 
 *          Implementa la interfaz (Serializable) la cual permite convertir un
 *          objeto (instancia) en ceros y uno, para de esta manera pueda ser
 *          transportado, almacenado y reconstruido en otra plataforma o
 *          sistema.
 * 
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "estado = 1")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_usuario")
	private Integer id;
	
	@Basic(optional = true)
	@Column(name = "usuario")
	private String user;
	
	@Basic(optional = true)
	@Column(name = "contrasenia")
	private String password;
	
	@Basic(optional = true)
	@Column(name = "nombre")
	private String name;
	
	@Basic(optional = true)
	@Column(name = "tipo_identificacion")
	private String identificationType;
	
	@Basic(optional = true)
	@Column(name = "identificacion")
	private String identification;
	
	@Basic(optional = true)
	@Column(name = "correo")
	private String mail;
	
	@Basic(optional = true)
	@Column(name = "celular")
	private String cellPhone;
	
	@Basic(optional = true)
	@Column(name = "estado")
	private Integer status;
}
