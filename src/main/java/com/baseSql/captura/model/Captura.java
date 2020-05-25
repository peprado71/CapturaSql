package com.baseSql.captura.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "capturas")

public class Captura {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private long id;

	@Column(name = "marca")
	  private String marca;
	@Column(name = "nombre_fantasia")
	  private String nombre_fantasia;
	@Column(name = "denominacion")
	  private String denominacion;
	@Column(name = "rnpa")
	  private String rnpa;
	
	public Captura() {
		
	}
	
	
	public Captura(String marca, String nombre_fantasia, String denominacion, String rnpa) {
		this.marca = marca;
		this.nombre_fantasia = nombre_fantasia;
		this.denominacion = denominacion;
		this.rnpa = rnpa;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getNombre_fantasia() {
		return nombre_fantasia;
	}
	public void setNombre_fantasia(String nombre_fantasia) {
		this.nombre_fantasia = nombre_fantasia;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public String getRnpa() {
		return rnpa;
	}
	public void setRnpa(String rnpa) {
		this.rnpa = rnpa;
	}


	@Override
	public String toString() {
		return "Capturas [id=" + id + ", marca=" + marca + ", nombre_fantasia=" + nombre_fantasia + ", denominacion="
				+ denominacion + ", rnpa=" + rnpa + "]";
	}
	
	

}

