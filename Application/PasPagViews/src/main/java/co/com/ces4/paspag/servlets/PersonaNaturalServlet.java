/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.paspag.servlets;

import co.com.ces4.paspagcontrollers.PersonaNaturalJpaController;
import co.com.ces4.paspagcontrollers.TipoCuentaJpaController;
import co.com.ces4.paspagcontrollers.exceptions.NonexistentEntityException;
import co.com.ces4.paspagentities.PersonaNatural;
import co.com.ces4.paspagentities.TipoDocumento;
import co.com.ces4.paspagentities.PersonaPK;
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
import java.util.ArrayList;
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
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
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
            List<TipoDocumento> tipodoc = new ArrayList<>();
            for (int i = 0; i < TipoDocumento.values().length; i++) {
                tipodoc.add(TipoDocumento.values()[i]);
            }
            List listaPersonas = controller.findPersonaNaturalEntities();
            request.setAttribute("listaPersonas", listaPersonas);
            request.setAttribute("tipodoc", tipodoc);
            limpiar(request, response);
            request.getRequestDispatcher("Personas.jsp").forward(request, response);
        }
    }
    
    
    protected void limpiar(HttpServletRequest request, HttpServletResponse response) {        
        List<TipoDocumento> tipodoc = new ArrayList<>();
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
        request.setAttribute("conditional", "");
    }
    
    protected void metGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NonexistentEntityException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String accion = request.getParameter("accion");
            limpiar(request, response);
            PersonaNaturalJpaController controller = (PersonaNaturalJpaController)getServletContext().getAttribute("personaNaturaljpa");

            if(accion.equals("listPersonasNaturales") || accion.equals("limpiarRedireccionar")){
                limpiarRedireccionar(request, response);
            }
            if (accion.equals("set_data")) {
               List<TipoDocumento> tipodoc = Arrays.asList(TipoDocumento.values());
               List listaPersonas = controller.findPersonaNaturalEntities();

                              
               String Documento = request.getParameter("documento");
               String tipo_seleccionado = request.getParameter("tipo_documento");
               TipoDocumento tipoSeleccionado = TipoDocumento.valueOf(tipo_seleccionado);
               PersonaPK primaryKey = new PersonaPK(Documento, tipoSeleccionado);
               
               PersonaNatural persona = controller.findPersonaNatural(primaryKey);
               
               request.setAttribute("documento_persona", Documento);
               request.setAttribute("tipo_seleccionado", tipoSeleccionado);
               request.setAttribute("nombre_persona", persona.getNombre());
               request.setAttribute("contacto_persona", persona.getContacto());
               request.setAttribute("correo_persona", persona.getCorreo());
               request.setAttribute("direccion_persona", persona.getDireccion());
               request.setAttribute("usuario", persona.getUsuario());
               request.setAttribute("contrasena", persona.getContrasena());
               request.setAttribute("pais_nacimiento", persona.getPaisNacimiento());
               request.setAttribute("ciudad_nacimiento", persona.getCiudadNacimiento());
               request.setAttribute("fecha_nacimiento", df.format(persona.getFechaNacimiento()));
               
               request.setAttribute("listaPersonas", listaPersonas);
               request.setAttribute("tipodoc", tipodoc);
               
               request.getRequestDispatcher("Personas.jsp").forward(request, response);
            }
            if (accion.equals("eliminar")) {
                String Documento = request.getParameter("documento");
                String tipo_seleccionado = request.getParameter("tipo_documento");
                TipoDocumento tipoSeleccionado = TipoDocumento.valueOf(tipo_seleccionado);
                PersonaPK primaryKey = new PersonaPK(Documento, tipoSeleccionado);
                controller.destroy(primaryKey);
                limpiarRedireccionar(request, response);
            }
        }
    }
    
    protected void metPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            System.out.println("conditional " + request.getParameter("conditional"));
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
            System.out.println("entra" + request.getParameter("conditional"));
            if (request.getParameter("conditional").equals("1")){
                controller.edit(personaNatural);
                limpiarRedireccionar(request, response);
            }else {                
                controller.create(personaNatural);
                limpiarRedireccionar(request, response);
            }
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
        try {
            metGet(request, response);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersonaNaturalServlet.class.getName()).log(Level.SEVERE, null, ex);
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
