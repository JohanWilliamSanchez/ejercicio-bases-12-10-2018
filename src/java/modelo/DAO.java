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
public interface DAO {
    public boolean crear(Persona persona);
    public ArrayList<Persona> listar();
    public boolean borrar(Persona persona);
    public boolean actualizar(int id,Persona persona);
    public Persona buscar(int id);
}
