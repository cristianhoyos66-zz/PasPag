/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.paspag.servlets;

import co.com.ces4.paspagcontrollers.PersonaNaturalJpaController;
import co.com.ces4.paspagcontrollers.TipoCuentaJpaController;
import co.com.ces4.paspagentities.PersonaNatural;
import co.com.ces4.paspagentities.TipoDocumento;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cristian
 */
@WebServlet(name = "PersonaNaturalServlet", urlPatterns = {"/PersonaNaturalServlet"})
public class PersonaNaturalServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    DateFormat df = new SimpleDateFormat("yyyy/dd/mm");
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
        }
    }
    
    protected void limpiarRedireccionar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            PersonaNaturalJpaController controller = (PersonaNaturalJpaController)getServletContext().getAttribute("personaNaturaljpa");
            List listaPersonas = controller.findPersonaNaturalEntities();
            List<TipoDocumento> tipodoc = Arrays.asList(TipoDocumento.values());
            request.setAttribute("listaPersonas", listaPersonas);
            request.setAttribute("tipodoc", tipodoc);
            limpiar(request, response);
            request.getRequestDispatcher("Personas.jsp").forward(request, response);
        }
    }
    
    protected void limpiar(HttpServletRequest request, HttpServletResponse response) {        
        request.setAttribute("documento_persona", "");
        request.setAttribute("nombre_persona", "");
        request.setAttribute("contacto_persona", "");
        request.setAttribute("correo_persona", "");
        request.setAttribute("direccion_persona", "");
        request.setAttribute("usuario", "");
        request.setAttribute("contrasena", "");
        request.setAttribute("pais_nacimiento", "");
        request.setAttribute("ciudad_nacimiento", "");
        request.setAttribute("fecha_nacimiento", "");
    }
    
    protected void metGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String accion = request.getParameter("accion");
            limpiar(request, response);
            if(accion.equals("listPersonasNaturales")){
                limpiarRedireccionar(request, response);
            }
            if (accion.equals("set_data")) {
               PersonaNaturalJpaController controller = (PersonaNaturalJpaController)getServletContext().getAttribute("personaNaturaljpa");
                
               String Documento = request.getParameter("documento");
               String tipo_seleccionado = request.getParameter("tipo_documento");
               request.setAttribute("documento_persona", Documento);
               request.setAttribute("tipo_seleccionado", tipo_seleccionado);
               List listaPersonas = controller.findPersonaNaturalEntities();
               List<TipoDocumento> tipodoc = Arrays.asList(TipoDocumento.values());
               request.setAttribute("listaPersonas", listaPersonas);
               request.setAttribute("tipodoc", tipodoc);
               request.getRequestDispatcher("Personas.jsp").forward(request, response);
            }
        }
    }
    
    protected void metPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            PersonaNaturalJpaController controller = (PersonaNaturalJpaController)getServletContext().getAttribute("personaNaturaljpa");
            
            String Documento = request.getParameter("documento_persona");
            TipoDocumento tipo_seleccionado = TipoDocumento.valueOf(request.getParameter("tipo_seleccionado"));
            String nombre_persona = request.getParameter("nombre_persona");
            String contacto_persona = request.getParameter("contacto_persona");
            String correo_persona = request.getParameter("correo_persona");
            String direccion_persona = request.getParameter("direccion_persona");
            String usuario = request.getParameter("usuario");
            String contrasena = request.getParameter("contrasena");
            String pais_nacimiento = request.getParameter("pais_nacimiento");
            String ciudad_nacimiento = request.getParameter("ciudad_nacimiento");
            Date fecha_nacimiento = df.parse(request.getParameter("fecha_nacimiento"));
            
            PersonaNatural personaNatural = new PersonaNatural(ciudad_nacimiento, pais_nacimiento, fecha_nacimiento, Documento, tipo_seleccionado, nombre_persona, contacto_persona, correo_persona, direccion_persona, usuario, contrasena);
            
            controller.create(personaNatural);
            
           
            limpiarRedireccionar(request, response);
        }
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
        metGet(request, response);
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
        try {
            metPost(request, response);
        } catch (Exception ex) {
            Logger.getLogger(PersonaNaturalServlet.class.getName()).log(Level.SEVERE, null, ex);
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
