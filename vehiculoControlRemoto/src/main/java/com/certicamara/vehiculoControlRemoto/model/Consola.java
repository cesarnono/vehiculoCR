/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certicamara.vehiculoControlRemoto.model;

import com.certicamara.vehiculoControlRemoto.exception.PracticaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author César Aguirre Vega
 */
public class Consola {

    private String descripcion;
    private List<String> historialComandos = new ArrayList<String>();
    private String comando;
    private String [] listaComandoValido;

    public Consola() {
    }

    public Consola(String comando) {
        this.comando = comando;
    }
    
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getHistorialComandos() {
        return historialComandos;
    }

    public void setHistorialComandos(List<String> historialComandos) {
        this.historialComandos = historialComandos;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public boolean validarEntradaComando() throws PracticaException {
        boolean respuesta = false;       
        if (this.comando != null) {
            String[] listaComandos = this.comando.split(";");
            for (String comandoTemp : listaComandos) {
                String arrayComando[] = comandoTemp.split(",");
                if (arrayComando.length != 2) {
                    throw new PracticaException("Formato de comando erroneo");
                }
                if(!this.validarDesplazamiento(arrayComando[0])){
                     throw new PracticaException("Formato de comando erroneo el desplazamiento debe ser un numero");
                }
                if(!this.validarDireccion(arrayComando[1].toUpperCase())){
                    throw new PracticaException("Formato de comando erroneo la direccion deber ser N,S,E,O");
                }
            }
            this.listaComandoValido = listaComandos;
            respuesta = true;
        }
        return respuesta;
    }

    

    boolean validarDesplazamiento(String dato) {
        if (dato == null || dato.isEmpty()) {
            return false;
        }
        for (int i = 0; i < dato.length(); i++) {
            if (!Character.isDigit(dato.charAt(i))) {
                return false;
            }
        }
        return true;

    }
    
    boolean validarDireccion(String direccion){
        return "N".equals(direccion) || "S".equals(direccion) || "E".equals(direccion) || "O".equals(direccion);
    }
    
    

    public static void main(String args[]) {
        Consola consola = new Consola();
        consola.setComando("5,P");
        try {
            System.out.println("respuesta validar entrada comando :"+consola.validarEntradaComando());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public String[] getListaComandoValido() {
        return listaComandoValido;
    }

    public void setListaComandoValido(String[] listaComandoValido) {
        this.listaComandoValido = listaComandoValido;
    }
    
    
    

}
