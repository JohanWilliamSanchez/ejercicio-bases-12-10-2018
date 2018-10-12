/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Labing I5
 */
public class DAOPersona implements DAO{
    private ArrayList<Persona> bdPersonas;

    public DAOPersona() {
       this.bdPersonas = new ArrayList<>();
    }
        
    @Override
    public boolean crear(Persona persona) {
          if(this.buscar(persona.getId())==null){
            return this.bdPersonas.add(persona);
          }
          return false;
    }

    @Override
    public ArrayList<Persona> listar() {
        return bdPersonas;
    }

    @Override
    public boolean borrar(Persona persona) {
        return this.bdPersonas.remove(persona);
    }

    @Override
    public boolean actualizar(int id, Persona persona) {
        for (int i = 0; i < this.bdPersonas.size(); i++) {
            Persona aux = this.bdPersonas.get(i);
            if(aux.getId() == id){
               this.bdPersonas.get(i).setNombre(persona.getNombre());
               this.bdPersonas.get(i).setApellido(persona.getApellido());
               return true;
            }
        }
       return false;
    }

    @Override
    public Persona buscar(int id) {
       
         for (Persona persona : bdPersonas) {
            if(persona.getId() == id){
              return persona;
            }    
         }
         return null;
    }
    
}
