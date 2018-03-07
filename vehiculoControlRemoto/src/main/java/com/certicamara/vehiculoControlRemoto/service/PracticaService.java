/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certicamara.vehiculoControlRemoto.service;

import com.certicamara.vehiculoControlRemoto.exception.PracticaException;
import com.certicamara.vehiculoControlRemoto.model.Consola;
import com.certicamara.vehiculoControlRemoto.model.Practica;
import com.certicamara.vehiculoControlRemoto.model.Superficie;
import com.certicamara.vehiculoControlRemoto.model.Vehiculo;
import org.springframework.stereotype.Service;

/**
 *
 * @author César Aguirre
 */
@Service
public class PracticaService {

    private static final String NORTE = "N";
    private static final String SUR = "S";
    private static final String ESTE = "E";
    private static final String OESTE = "O";

    public Practica iniciarPractica(Superficie superficie) {
        Practica practica = new Practica();
        practica.setSuperficie(superficie);
        practica.setVehiculo(new Vehiculo(0, 0));
        practica.setConsola(new Consola());        
        return practica;
    }

    public void gestionarComando(Practica practica, String comando) {
        Consola consola = practica.getConsola();
        if (consola != null) {
            consola.setComando(comando);
            try {
                Vehiculo vehiculo= practica.getVehiculo();
                consola.validarEntradaComando();
                System.out.println("posicion del auto antes de correr el comando: "+vehiculo.getPosicionX() +"-"+vehiculo.getPosicionY());
                this.correrComando(practica);
                System.out.println("posicion del auto despues de correr el comando: "+vehiculo.getPosicionX() +"-"+vehiculo.getPosicionY());

            } catch (PracticaException e) {
                practica.setNovedad(e.getMessage());
                e.printStackTrace();
            }
        } else {
            practica.setNovedad("Ocurrio un error.. no se encontro consola configurada");
        }
    }

    private void correrComando(Practica practica) throws PracticaException {
        Consola consola = practica.getConsola();
        Vehiculo vehiculo = practica.getVehiculo();
        Superficie superficie = practica.getSuperficie();
        for (String comando : consola.getListaComandoValido()) {
            String[] arrComando = comando.split(",");
            int desplazamiento = Integer.parseInt(arrComando[0]);
            String direccion = arrComando[1];
            switch (direccion) {
                case NORTE:
                    this.avanzarEnX(vehiculo, desplazamiento, superficie);
                    break;
                case SUR:
                    this.retrocederEnX(vehiculo, desplazamiento);
                    break;
                case ESTE:
                    this.avanzarEnY(vehiculo, desplazamiento, superficie);
                    break;
                case OESTE:
                    this.retrocederEnY(vehiculo, desplazamiento);
                    break;
            }
        }
    }

    private void avanzarEnX(Vehiculo vehiculo, int desplazamiento, Superficie superficie) throws PracticaException {
        if (vehiculo.getPosicionX() + desplazamiento >= superficie.getDimensionX()) {
            vehiculo.setPosicionX(superficie.getDimensionX() - 1);
            throw new PracticaException("Se detiene el movimiento por que se sale de los limites de la superficie");
        } else {
            vehiculo.setPosicionX(vehiculo.getPosicionX() + desplazamiento);
        }
    }

    private void retrocederEnX(Vehiculo vehiculo, int desplazamiento) throws PracticaException {
        if (vehiculo.getPosicionX() - desplazamiento < 0) {
            vehiculo.setPosicionX(0);
            throw new PracticaException("Se detiene el movimiento por que se sale de los limites de la superficie");
        } else {
            vehiculo.setPosicionX(vehiculo.getPosicionX() - desplazamiento);
        }
    }

    private void avanzarEnY(Vehiculo vehiculo, int desplazamiento, Superficie superficie) throws PracticaException {
        if (vehiculo.getPosicionY() + desplazamiento >= superficie.getDimensionY()) {
            vehiculo.setPosicionY(superficie.getDimensionY() - 1);
            throw new PracticaException("Se detiene el movimiento por que se sale de los limites de la superficie");
        } else {
            vehiculo.setPosicionY(vehiculo.getPosicionY() + desplazamiento);
        }
    }

    private void retrocederEnY(Vehiculo vehiculo, int desplazamiento) throws PracticaException {
        if (vehiculo.getPosicionY() - desplazamiento < 0) {
            vehiculo.setPosicionY(0);
            throw new PracticaException("Se detiene el movimiento por que se sale de los limites de la superficie");
        } else {
            vehiculo.setPosicionY(vehiculo.getPosicionY() - desplazamiento);
        }
    }
}
