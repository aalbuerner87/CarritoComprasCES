/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.naming.CommunicationException;

/**
 *
 * @author mfarias
 */
class Cliente {
    private String nombre;

    Cliente(String n) {
        setNombre(n);
    }
    
    /**
     * @return the nombre
     */
    public String getNombre() throws CommunicationException {
        // Este código no es bueno modificarlo
        // en este caso es trivial pero puede ser complejo
        // conviene modificar o crear código de testing y 
        // no tocar el código de producción
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }




}
