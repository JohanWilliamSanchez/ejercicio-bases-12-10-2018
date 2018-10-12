/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.DAOPersona;
import modelo.Persona;

/**
 *
 * @author Labing I5
 */
public class RegistroServlet extends HttpServlet {
    private DAOPersona dao;

    @Override
    public void init() throws ServletException {
         this.dao = new DAOPersona();
    }
    
  
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
         if(request.getParameter("borrar")!= null){
            String id = request.getParameter("borrar");
            Persona persona = this.dao.buscar(Integer.parseInt(id));
            this.dao.borrar(persona);
            
            ArrayList<Persona> personas = this.dao.listar();
            RequestDispatcher rq = request.getRequestDispatcher("index.jsp");
            request.setAttribute("lista", personas);
            rq.forward(request, response);
            
         }
         
         if(request.getParameter("editar")!=null){
             String id = request.getParameter("editar");
             Persona persona = this.dao.buscar(Integer.parseInt(id));
             RequestDispatcher rq = request.getRequestDispatcher("index.jsp");
             request.setAttribute("persona", persona);
             rq.forward(request, response);
         }
        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String cedula = request.getParameter("cedula");
       String nombre = request.getParameter("nombre");
       String apellido = request.getParameter("apellido");
       //Validaciones - SQL Inyection - Luego
       if(nombre != null && apellido != null && nombre.length()>0){
           Persona persona = 
                   new Persona(Integer.parseInt(cedula), nombre, apellido);
           if(!this.dao.crear(persona)){
              response.sendRedirect("index.jsp?error=ErrorDatos");
           }
           ArrayList<Persona> personas = this.dao.listar();
           RequestDispatcher rq = request.getRequestDispatcher("index.jsp");
           request.setAttribute("lista", personas);
           rq.forward(request, response);
           
       }else{
           response.sendRedirect("index.jsp?error=IngreseDatos");
       }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
