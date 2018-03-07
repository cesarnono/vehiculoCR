/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certicamara.vehiculoControlRemoto.controller;

import com.certicamara.vehiculoControlRemoto.model.Consola;
import com.certicamara.vehiculoControlRemoto.model.Practica;
import com.certicamara.vehiculoControlRemoto.model.Superficie;
import com.certicamara.vehiculoControlRemoto.service.PracticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author César Aguirre Vega
 */
@RestController
public class PracticaController {

    @Autowired
    PracticaService practicaService;

    @GetMapping("/")
    public String index() {
        return "Bienvenido a practicas";
    }
    
    

    @CrossOrigin
    @PostMapping("/iniciarpractica")
    public Practica iniciarPractica(@RequestBody Superficie superficie) {
        Practica practica = this.practicaService.iniciarPractica(superficie);        
        return practica;
    }
    
    @CrossOrigin
    @PostMapping("/corrercomando")
    public Practica correrComando(@RequestBody Practica practica) {
        this.practicaService.gestionarComando(practica, practica.getConsola().getComando());
        return practica;
    }

}
