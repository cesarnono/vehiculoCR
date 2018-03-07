/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certicamara.vehiculoControlRemoto.service;

import com.certicamara.vehiculoControlRemoto.model.Consola;
import com.certicamara.vehiculoControlRemoto.model.Practica;
import com.certicamara.vehiculoControlRemoto.model.Superficie;
import com.certicamara.vehiculoControlRemoto.model.Vehiculo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author César Aguirre Vega
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PracticaServiceTest {
    
    public PracticaServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of iniciarPractica method, of class PracticaService.
     */
   /* @Test
    public void testIniciarPractica() {
        System.out.println("iniciarPractica");
        Superficie superficie = null;
        PracticaService instance = new PracticaService();
        Practica expResult = null;
        Practica result = instance.iniciarPractica(superficie);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of gestionarComando method, of class PracticaService.
     */
    @Test
    public void testGestionarComando() {
        System.out.println("gestionarComando");
        Practica practica = new Practica();
        practica.setVehiculo(new Vehiculo(0, 0));
        practica.setSuperficie(new Superficie(5, 5));
        practica.setConsola(new Consola());
        String comando = "4,N";
        PracticaService instance = new PracticaService();
        instance.gestionarComando(practica, comando);        
        assertTrue(practica.getVehiculo().getPosicionX() == 4 && practica.getVehiculo().getPosicionY()==0);        
        
    }
    
    @Test
    public void testGestionarComandoLista() {
        System.out.println("gestionarComando");
        Practica practica = new Practica();
        practica.setVehiculo(new Vehiculo(0, 0));
        practica.setSuperficie(new Superficie(5, 5));
        practica.setConsola(new Consola());
        String comando = "4,N;3,E;2,S";
        PracticaService instance = new PracticaService();
        instance.gestionarComando(practica, comando);        
        assertTrue(practica.getVehiculo().getPosicionX() == 2 && practica.getVehiculo().getPosicionY()==3); 
        
    }
    
    @Test
    public void testGestionarComandoErroneo() {
        System.out.println("gestionarComando");
        Practica practica = new Practica();
        practica.setVehiculo(new Vehiculo(0, 0));
        practica.setSuperficie(new Superficie(5, 5));
        practica.setConsola(new Consola());
        String comando = "4,N;3,E;2;S";
        PracticaService instance = new PracticaService();
        instance.gestionarComando(practica, comando);        
        assertEquals(practica.getNovedad(), "Formato de comando erroneo");        
    }
    
    @Test
    public void testGestionarComandoPorFueraSuperfice() {
        System.out.println("gestionarComando");
        Practica practica = new Practica();
        practica.setVehiculo(new Vehiculo(0, 0));
        practica.setSuperficie(new Superficie(5, 5));
        practica.setConsola(new Consola());
        String comando = "4,N;3,E;6,S";
        PracticaService instance = new PracticaService();
        instance.gestionarComando(practica, comando);        
        assertTrue(practica.getVehiculo().getPosicionX() == 0 && practica.getVehiculo().getPosicionY()==3); 
        assertEquals(practica.getNovedad(), "Se detiene el movimiento por que se sale de los limites de la superficie");
        
    }
    
}
