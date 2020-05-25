package com.baseSql.captura.endpoint;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baseSql.captura.model.Captura;
import com.baseSql.captura.repository.CapturaRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CapturaController {

	@Autowired
	CapturaRepository capturaRepository;
	
	@GetMapping("/capturas")
	public ResponseEntity<List<Captura>> getAllCapturas(@RequestParam(required = false) String marca) {
	    try {
	      List<Captura> capturas = new ArrayList<Captura>();

	      if (marca == null)
	        capturaRepository.findAll().forEach(capturas::add);
	      else
	        capturaRepository.findByMarcaContaining(marca).forEach(capturas::add);

	      if (capturas.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(capturas, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @GetMapping("/capturas/{id}")
	  public ResponseEntity<Captura> getCapturaById(@PathVariable("id") long id) {
	    Optional<Captura> capturaData = capturaRepository.findById(id);

	    if (capturaData.isPresent()) {
	      return new ResponseEntity<>(capturaData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @PostMapping("/capturas")
	  public ResponseEntity<Captura> createCaptura(@RequestBody Captura captura) {
	    try {
	      Captura _captura = capturaRepository.save(new Captura(captura.getMarca(), captura.getNombre_fantasia(), captura.getDenominacion(), captura.getRnpa()));
	      return new ResponseEntity<>(_captura, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	  
	  @PutMapping("/capturas/{id}")
	  public ResponseEntity<Captura> updateCaptura(@PathVariable("id") long id, @RequestBody Captura captura) {
	    Optional<Captura> capturaData = capturaRepository.findById(id);

	    if (capturaData.isPresent()) {
	      Captura _captura = capturaData.get();
	      _captura.setMarca(captura.getMarca());
	      _captura.setNombre_fantasia(captura.getNombre_fantasia());
	      _captura.setDenominacion(captura.getDenominacion());
	      _captura.setRnpa(captura.getRnpa());
	      return new ResponseEntity<>(capturaRepository.save(_captura), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @DeleteMapping("/capturas/{id}")
	  public ResponseEntity<HttpStatus> deleteCaptura(@PathVariable("id") long id) {
	    try {
	      capturaRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	  }

	  @DeleteMapping("/capturas")
	  public ResponseEntity<HttpStatus> deleteAllCapturas() {
	    try {
	      capturaRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	  
	  @PostMapping("/capturas/archivo")
	  public ResponseEntity<List<Captura>> saveArchivo() {
	    try {
	    	List<Captura> listaCapturas = new ArrayList<Captura>();
	    	File archivo = new File("c:/sts/workspace/alimentos.json");
	    	ObjectMapper objectMapper = new ObjectMapper();
	    	objectMapper.configure (DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    	
	    	Captura[] capturas = objectMapper.readValue(archivo, Captura[].class);
	    	for (int i=0; i< capturas.length;i++) {
	    		
	    		Captura _captura = capturaRepository.save(capturas[i]);		    			
	    		
				listaCapturas.add(_captura);
			}		
	    	
	    	 return new ResponseEntity<>(listaCapturas, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    }
	    
	  }
}

