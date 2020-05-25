package com.baseSql.captura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baseSql.captura.model.Captura;



public interface CapturaRepository extends JpaRepository<Captura, Long > 
	{
		  List<Captura> findByMarcaContaining(String marca);	    	  
		}