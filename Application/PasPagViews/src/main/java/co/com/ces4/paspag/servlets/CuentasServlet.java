/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.paspag.servlets;

import co.com.ces4.paspagcontrollers.CuentaJpaController;
import co.com.ces4.paspagcontrollers.EntidadFinancieraJpaController;
import co.com.ces4.paspagcontrollers.PersonaJuridicaJpaController;
import co.com.ces4.paspagcontrollers.PersonaNaturalJpaController;
import co.com.ces4.paspagcontrollers.TipoCuentaJpaController;
import co.com.ces4.paspagcontrollers.exceptions.NonexistentEntityException;
import co.com.ces4.paspagentities.Cuenta;
import co.com.ces4.paspagentities.TipoDocumento;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "CuentasServlet", urlPatterns = {"/CuentasServlet"})
public class CuentasServlet extends HttpServlet {

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
            EntidadFinancieraJpaController controllerEntidadFinanciera = (EntidadFinancieraJpaController) getServletContext().getAttribute("entidadFinancierJpa");
            TipoCuentaJpaController controllerTipoCuenta = (TipoCuentaJpaController) getServletContext().getAttribute("tipoCuentajpa");
            PersonaJuridicaJpaController controllerPersonaJuridica = (PersonaJuridicaJpaController) getServletContext().getAttribute("personaJuridicajpa");
            PersonaNaturalJpaController controllerPersonaNatural = (PersonaNaturalJpaController) getServletContext().getAttribute("personaNaturaljpa");
            
            List personas = controllerPersonaNatural.findPersonaNaturalEntities();
            List empresas = controllerPersonaJuridica.findPersonaJuridicaEntities();
            List tipo_cuentas = controllerTipoCuenta.findTipoCuentaEntities();
            List entidades_bancarias = controllerEntidadFinanciera.findEntidadFinancieraEntities();
            
            request.setAttribute("personas", personas);
            request.setAttribute("empresas", empresas);
            request.setAttribute("tipo_cuentas", tipo_cuentas);
            request.setAttribute("entidades_bancarias", entidades_bancarias);
            limpiar(request, response);
            request.getRequestDispatcher("Cuentas.jsp").forward(request, response);
        }
    }
    
    protected void limpiar(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("numero_cuenta", "");
        request.setAttribute("valido_desde", "");
        request.setAttribute("valido_hasta", "");
        request.setAttribute("tipo_cuenta_seleccionada", "");
        request.setAttribute("empresa_seleccionada", "");
        request.setAttribute("persona_seleccionada", "");
        request.setAttribute("entidad_seleccionada", "");
        request.setAttribute("saldo", "");
    }
    
    protected void metGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NonexistentEntityException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String accion = request.getParameter("accion");
            limpiar(request, response);
            CuentaJpaController controller = (CuentaJpaController) getServletContext().getAttribute("cuentajpa");
            if (accion.equals("listCuentas") || accion.equals("limpiarRedireccionar")) {
                limpiarRedireccionar(request, response);
            }
            if (accion.equals("set_data")) {
                
            }
            if (accion.equals("eliminar")) {
                
            }
        }
    }    
    
    
    protected void metPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
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
        } catch (ParseException ex) {
            Logger.getLogger(CuentasServlet.class.getName()).log(Level.SEVERE, null, ex);
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
