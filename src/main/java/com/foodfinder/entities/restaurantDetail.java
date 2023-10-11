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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detalle_restaurante")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "activo = true")

public class restaurantDetail implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_detalle")
	private Integer id;
	
	@Basic(optional = false)
	@Column(name = "nombre")
	private String name;
	
	@Basic(optional = false)
	@Column(name = "descripcion")
	private String description;
	
	@Basic(optional = false)
	@Column(name = "horario_atencion")
	private String openinHours;
	
	@Basic(optional = false)
	@Column(name = "sitio_web")
	private String webSite;
	
	@Basic(optional = false)
	@Column(name = "ubicacion_mapa")
	private String locationMap;
}