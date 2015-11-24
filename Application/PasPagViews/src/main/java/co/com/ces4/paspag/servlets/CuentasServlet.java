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
import co.com.ces4.paspagentities.EntidadFinanciera;
import co.com.ces4.paspagentities.PersonaJuridica;
import co.com.ces4.paspagentities.PersonaNatural;
import co.com.ces4.paspagentities.PersonaPK;
import co.com.ces4.paspagentities.TipoCuenta;
import co.com.ces4.paspagentities.TipoDocumento;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.ConversionException;

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
            CuentaJpaController controllerCuenta = (CuentaJpaController) getServletContext().getAttribute("cuentajpa");
            EntidadFinancieraJpaController controllerEntidadFinanciera = (EntidadFinancieraJpaController) getServletContext().getAttribute("entidadFinancierJpa");
            TipoCuentaJpaController controllerTipoCuenta = (TipoCuentaJpaController) getServletContext().getAttribute("tipoCuentajpa");
            PersonaJuridicaJpaController controllerPersonaJuridica = (PersonaJuridicaJpaController) getServletContext().getAttribute("personaJuridicajpa");
            PersonaNaturalJpaController controllerPersonaNatural = (PersonaNaturalJpaController) getServletContext().getAttribute("personaNaturaljpa");
            
            List personas = controllerPersonaNatural.findPersonaNaturalEntities();
            List empresas = controllerPersonaJuridica.findPersonaJuridicaEntities();
            List tipo_cuentas = controllerTipoCuenta.findTipoCuentaEntities();
            List entidades_bancarias = controllerEntidadFinanciera.findEntidadFinancieraEntities();
            List listaCuentas = controllerCuenta.findCuentaEntities();
            
            request.setAttribute("personas", personas);
            request.setAttribute("empresas", empresas);
            request.setAttribute("tipo_cuentas", tipo_cuentas);
            request.setAttribute("entidades_bancarias", entidades_bancarias);
            request.setAttribute("listaCuentas", listaCuentas);
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
            CuentaJpaController controller = (CuentaJpaController) getServletContext().getAttribute("cuentajpa");TipoCuentaJpaController controllerTipoCuenta = (TipoCuentaJpaController) getServletContext().getAttribute("tipoCuentajpa");
            if (accion.equals("listCuentas") || accion.equals("limpiarRedireccionar")) {
                limpiarRedireccionar(request, response);
            }
            if (accion.equals("set_data")) {
                String numero_cuenta = request.getParameter("numero_cuenta");
                String conditional = request.getParameter("conditional");
                request.setAttribute("conditional", conditional);
                
                Cuenta cuenta = controller.findCuenta(numero_cuenta);
                request.setAttribute("numero_cuenta", numero_cuenta);
                request.setAttribute("saldo", cuenta.getSaldo());
                request.setAttribute("valido_desde", df.format(cuenta.getValido_desde()));
                request.setAttribute("valido_hasta", df.format(cuenta.getValido_hasta()));
                request.setAttribute("tipo_cuenta_seleccionada", cuenta.getTipo_cuenta());
                request.setAttribute("entidad_seleccionada", cuenta.getEntidadFinanciera());
                if (cuenta.getPersonaJuridica() != null){
                    request.setAttribute("empresa_seleccionada", cuenta.getPersonaJuridica());
                }
                if (cuenta.getPersonaNatural() != null) {
                    request.setAttribute("persona_seleccionada", cuenta.getPersonaNatural());                
                }
                
                CuentaJpaController controllerCuenta = (CuentaJpaController) getServletContext().getAttribute("cuentajpa");
                EntidadFinancieraJpaController controllerEntidadFinanciera = (EntidadFinancieraJpaController) getServletContext().getAttribute("entidadFinancierJpa");
                TipoCuentaJpaController controllerTipoCuenta2 = (TipoCuentaJpaController) getServletContext().getAttribute("tipoCuentajpa");
                PersonaJuridicaJpaController controllerPersonaJuridica = (PersonaJuridicaJpaController) getServletContext().getAttribute("personaJuridicajpa");
                PersonaNaturalJpaController controllerPersonaNatural = (PersonaNaturalJpaController) getServletContext().getAttribute("personaNaturaljpa");

                List personas = controllerPersonaNatural.findPersonaNaturalEntities();
                List empresas = controllerPersonaJuridica.findPersonaJuridicaEntities();
                List tipo_cuentas = controllerTipoCuenta2.findTipoCuentaEntities();
                List entidades_bancarias = controllerEntidadFinanciera.findEntidadFinancieraEntities();
                List listaCuentas = controllerCuenta.findCuentaEntities();

                request.setAttribute("personas", personas);
                request.setAttribute("empresas", empresas);
                request.setAttribute("tipo_cuentas", tipo_cuentas);
                request.setAttribute("entidades_bancarias", entidades_bancarias);
                request.setAttribute("listaCuentas", listaCuentas);
                request.getRequestDispatcher("Cuentas.jsp").forward(request, response);
                
            }
            if (accion.equals("eliminar")) {
                String numero_cuenta = request.getParameter("numero_cuenta");
                controller.destroy(numero_cuenta);
                limpiarRedireccionar(request, response);                
            }
        }
    }    
    
    protected String[] getArrayPK (String pkStr) {
        String []array = pkStr.split(",");
        String documento = array[0].substring(20);
        String tipoDocumento = array[1].substring(15, array[1].length()-1);
        array[0] = documento;
        array[1] = tipoDocumento;
        return array;
    }   
    
    protected void metPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            CuentaJpaController controller = (CuentaJpaController) getServletContext().getAttribute("cuentajpa");
            EntidadFinancieraJpaController controllerEntidadFinanciera = (EntidadFinancieraJpaController) getServletContext().getAttribute("entidadFinancierJpa");
            TipoCuentaJpaController controllerTipoCuenta = (TipoCuentaJpaController) getServletContext().getAttribute("tipoCuentajpa");
            PersonaJuridicaJpaController controllerPersonaJuridica = (PersonaJuridicaJpaController) getServletContext().getAttribute("personaJuridicajpa");
            PersonaNaturalJpaController controllerPersonaNatural = (PersonaNaturalJpaController) getServletContext().getAttribute("personaNaturaljpa");
            PersonaJuridica personaJuridica;
            PersonaNatural personaNatural;
                    
            String numero_cuenta = request.getParameter("numero_cuenta");
            BigDecimal saldo = new BigDecimal(request.getParameter("saldo"));
            Date valido_desde = df.parse(request.getParameter("valido_desde"));
            Date valido_hasta = df.parse(request.getParameter("valido_hasta"));
            Integer idTipoCuenta = Integer.parseInt(request.getParameter("tipo_cuenta_seleccionada"));
            TipoCuenta tipoCuenta = controllerTipoCuenta.findTipoCuenta(idTipoCuenta);
            
            String valorPrimaryKeyEmpresa = request.getParameter("empresa_seleccionada");
            if (!valorPrimaryKeyEmpresa.isEmpty()) {
                String []primaryKeyEmpresa = getArrayPK(valorPrimaryKeyEmpresa);
                PersonaPK pkEmpresa = new PersonaPK(primaryKeyEmpresa[0], TipoDocumento.valueOf(primaryKeyEmpresa[1]));
                personaJuridica = controllerPersonaJuridica.findPersonaJuridica(pkEmpresa);
            }else {
                personaJuridica = null;
            }
            
            String valorPrimaryKeyPersona = request.getParameter("persona_seleccionada");
            if (!valorPrimaryKeyPersona.isEmpty()){
                String []primaryKeyPersona = getArrayPK(valorPrimaryKeyPersona);
                PersonaPK pkPersona = new PersonaPK(primaryKeyPersona[0], TipoDocumento.valueOf(primaryKeyPersona[1]));
                personaNatural = controllerPersonaNatural.findPersonaNatural(pkPersona);
            }else {
                personaNatural = null;
            }
            
            String valorPrimaryKeyEntidad = request.getParameter("entidad_seleccionada");
            String []primaryKeyEntidad = getArrayPK(valorPrimaryKeyEntidad);
            PersonaPK pkEntidad = new PersonaPK(primaryKeyEntidad[0], TipoDocumento.valueOf(primaryKeyEntidad[1]));
            EntidadFinanciera entidadFinanciera = controllerEntidadFinanciera.findEntidadFinanciera(pkEntidad);
            
            Cuenta cuenta = new Cuenta(numero_cuenta, valido_desde, valido_hasta, tipoCuenta, personaJuridica, personaNatural, entidadFinanciera, saldo);
                    
            if (request.getParameter("conditional").equals("1")){
                controller.edit(cuenta);
                limpiarRedireccionar(request, response);
            }else {                
                controller.create(cuenta);
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
