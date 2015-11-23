/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.paspag.servlets;

import co.com.ces4.paspagcontrollers.PersonaJuridicaJpaController;
import co.com.ces4.paspagcontrollers.PersonaNaturalJpaController;
import co.com.ces4.paspagcontrollers.exceptions.NonexistentEntityException;
import co.com.ces4.paspagentities.PersonaJuridica;
import co.com.ces4.paspagentities.PersonaNatural;
import co.com.ces4.paspagentities.PersonaPK;
import co.com.ces4.paspagentities.TipoDocumento;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cristian
 */
@WebServlet(name = "PersonaJuridicaServlet", urlPatterns = {"/PersonaJuridicaServlet"})
public class PersonaJuridicaServlet extends HttpServlet {

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
            PersonaJuridicaJpaController controller = (PersonaJuridicaJpaController) getServletContext().getAttribute("personaJuridicajpa");
            TipoDocumento tipodoc = TipoDocumento.values()[TipoDocumento.values().length - 1];
            List listaPersonas = controller.findPersonaJuridicaEntities();
            request.setAttribute("listaPersonas", listaPersonas);
            request.setAttribute("tipo_seleccionado", tipodoc.toString());
            limpiar(request, response);
            request.getRequestDispatcher("PersonasJuridicas.jsp").forward(request, response);
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
        request.setAttribute("conditional", "");
    }

    protected void metGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NonexistentEntityException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String accion = request.getParameter("accion");
            limpiar(request, response);
            PersonaJuridicaJpaController controller = (PersonaJuridicaJpaController) getServletContext().getAttribute("personaJuridicajpa");
            if (accion.equals("listPersonasJuridicas") || accion.equals("limpiarRedireccionar")) {
                limpiarRedireccionar(request, response);
            }
            if (accion.equals("set_data")) {
                TipoDocumento tipodoc = TipoDocumento.values()[TipoDocumento.values().length - 1];
                List listaPersonas = controller.findPersonaJuridicaEntities();

                String Documento = request.getParameter("documento");
                String tipo_seleccionado = request.getParameter("tipo_documento");
                String conditional = request.getParameter("conditional");
                TipoDocumento tipoSeleccionado = TipoDocumento.valueOf(tipo_seleccionado);
                PersonaPK primaryKey = new PersonaPK(Documento, tipoSeleccionado);

                PersonaJuridica persona = controller.findPersonaJuridica(primaryKey);

                request.setAttribute("conditional", conditional);
                request.setAttribute("documento_persona", Documento);
                request.setAttribute("tipo_seleccionado", tipo_seleccionado);
                request.setAttribute("nombre_persona", persona.getNombre());
                request.setAttribute("contacto_persona", persona.getContacto());
                request.setAttribute("correo_persona", persona.getCorreo());
                request.setAttribute("direccion_persona", persona.getDireccion());
                request.setAttribute("usuario", persona.getUsuario());
                request.setAttribute("contrasena", persona.getContrasena());
                request.setAttribute("pais_nacimiento", persona.getPaisConstitucion());
                request.setAttribute("ciudad_nacimiento", persona.getCiudadConstitucion());
                request.setAttribute("fecha_nacimiento", df.format(persona.getFechaConstitucion()));

                request.setAttribute("listaPersonas", listaPersonas);
                request.setAttribute("tipodoc", tipodoc);

                request.getRequestDispatcher("PersonasJuridicas.jsp").forward(request, response);
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
            PersonaJuridicaJpaController controller = (PersonaJuridicaJpaController)getServletContext().getAttribute("personaJuridicajpa");
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
            PersonaJuridica personaJuridica = new PersonaJuridica(ciudad_nacimiento, pais_nacimiento, fecha_nacimiento, Documento, tipo_seleccionado, nombre_persona, contacto_persona, correo_persona, direccion_persona, usuario, contrasena);
            if (request.getParameter("conditional").equals("1")){
                controller.edit(personaJuridica);
                limpiarRedireccionar(request, response);
            }else {                
                controller.create(personaJuridica);
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
