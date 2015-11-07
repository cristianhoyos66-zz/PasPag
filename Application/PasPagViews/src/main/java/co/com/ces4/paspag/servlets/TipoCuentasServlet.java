/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ces4.paspag.servlets;

import co.com.ces4.paspagcontrollers.TipoCuentaJpaController;
import co.com.ces4.paspagcontrollers.exceptions.NonexistentEntityException;
import co.com.ces4.paspagentities.TipoCuenta;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "TipoCuentasServlet", urlPatterns = {"/TipoCuentasServlet"})
public class TipoCuentasServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TipoCuentasServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TipoCuentasServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    protected void limpiar(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("descripcion", "");
        request.setAttribute("id", "");
    }
    
    protected void metPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            TipoCuentaJpaController controller = (TipoCuentaJpaController)getServletContext().getAttribute("tipoCuentajpa");
            String descripcion = request.getParameter("descripcion");
            if (request.getParameter("id").equals("")) {
                controller.create(new TipoCuenta(descripcion));
            }else {
                Integer id = Integer.parseInt(request.getParameter("id"));
                TipoCuenta tc = new TipoCuenta(descripcion);
                tc.setId(id);
                controller.edit(tc);
            }
            List listaTipoCuentas = controller.findTipoCuentaEntities();
            request.setAttribute("tipoCuentas", listaTipoCuentas);
            limpiar(request, response);
            request.getRequestDispatcher("TipoCuentas.jsp").forward(request, response);
        }
    }
    
    protected void metGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NonexistentEntityException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String accion = request.getParameter("accion");
            limpiar(request, response);
            if(accion.equals("listTipoCuentas")){
                TipoCuentaJpaController controller = (TipoCuentaJpaController)getServletContext().getAttribute("tipoCuentajpa");
                List listaTipoCuentas = controller.findTipoCuentaEntities();
                request.setAttribute("tipoCuentas", listaTipoCuentas);
                request.getRequestDispatcher("TipoCuentas.jsp").forward(request, response);
            }
            if (accion.equals("eliminar")) {
                TipoCuentaJpaController controller = (TipoCuentaJpaController)getServletContext().getAttribute("tipoCuentajpa");
                Integer id = Integer.parseInt(request.getParameter("id"));
                controller.destroy(id);
                List listaTipoCuentas = controller.findTipoCuentaEntities();
                request.setAttribute("tipoCuentas", listaTipoCuentas);
                request.getRequestDispatcher("TipoCuentas.jsp").forward(request, response);
            }
            if (accion.equals("actualizar")) {
               TipoCuentaJpaController controller = (TipoCuentaJpaController)getServletContext().getAttribute("tipoCuentajpa");
               String id = request.getParameter("id");
               String descripcion = request.getParameter("descripcion");
               request.setAttribute("id", id);
               request.setAttribute("descripcion", descripcion);
               List listaTipoCuentas = controller.findTipoCuentaEntities();
               request.setAttribute("tipoCuentas", listaTipoCuentas);
               request.getRequestDispatcher("TipoCuentas.jsp").forward(request, response);
            }
            if (accion.equals("limpiar")) {
               TipoCuentaJpaController controller = (TipoCuentaJpaController)getServletContext().getAttribute("tipoCuentajpa");
               limpiar(request, response);
               List listaTipoCuentas = controller.findTipoCuentaEntities();
               request.setAttribute("tipoCuentas", listaTipoCuentas);
               request.getRequestDispatcher("TipoCuentas.jsp").forward(request, response);
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
            Logger.getLogger(TipoCuentasServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(TipoCuentasServlet.class.getName()).log(Level.SEVERE, null, ex);
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
