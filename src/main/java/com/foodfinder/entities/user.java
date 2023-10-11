package com.foodfinder.entities;

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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario", schema = "seguridad")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "activo = true")
public class user {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_usuario")
	private Integer id;
	
	@Basic(optional = false)
	@Column(name = "usuario")
	private String user;
	
	@Basic(optional = false)
	@Column(name = "contrasenia")
	private String password;
	
	@Basic(optional = false)
	@Column(name = "nombre")
	private String name;
	
	@Basic(optional = false)
	@Column(name = "tipo_identificacion")
	private String identificationType;
	
	@Basic(optional = false)
	@Column(name = "identificacion")
	private Integer identification;
	
	@Basic(optional = false)
	@Column(name = "correo")
	private String mail;
	
	@Basic(optional = false)
	@Column(name = "celular")
	private Integer cellPhone;
	
	@Basic(optional = false)
	@Column(name = "estado")
	private Integer status;
}
