/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.Angyusers;
import entities.Pointreg;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AngyusersJpaController;
import services.PointregJpaController;

/**
 *
 * @author Angie_
 */
@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {

    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAweb");
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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletLogin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletLogin at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
        //processRequest(request, response);

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAwebPU");
//        AngyusersJpaController userJpaContr = new AngyusersJpaController(emf);
//        List<Angyusers> usergame = userJpaContr.findAngyusersEntities();
//        PointregJpaController regJpaContr = new PointregJpaController(emf);
//        List<Pointreg> listRegPer = regJpaContr.findPointregEntities();
//        Angyusers jpauser = new Angyusers();
//        String comprCookie;
//        int iduser = 0;
//        boolean nickCompr = false, passwCompr = false;
        /**
         * Comprobamos que si tenemos cookies de login guardadas, en el caso de
         * que tengamos las usaremos para conectar a la pagina principal del
         * usuario si no nos mantiene en la página de Login.
         */
//        Cookie ck[] = request.getCookies();
//        for (Cookie cookie : ck) {
//            comprCookie = cookie.getValue();
//            for (Angyusers angyusers : usergame) {
//                if (comprCookie.contentEquals(angyusers.getUsuario())) {
//                    nickCompr = true;
//                    iduser = angyusers.getIdUser();
//                    String nick = comprCookie.toString();
//                    request.setAttribute("usuario", nick);
//                }
//                if (comprCookie.contentEquals(angyusers.getContras())) {
//                    passwCompr = true;
//                }
//            }
//        }
//        if (nickCompr == true && passwCompr == true) {
        /**
         * Comandos para enviar listas de consultas al JSP.
         */
//            RequestDispatcher a = request.getRequestDispatcher("lander/index.html");
//            a.forward(request, response);
//        } else {
//            RequestDispatcher a = request.getRequestDispatcher("index2.jsp");
//            a.forward(request, response);
//        }
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
        //processRequest(request, response);

        //Variables String recogida con los parametros del documento index.jsp 
        String usuario = request.getParameter("usuario");
        String contras = request.getParameter("contras");

        Angyusers user = new Angyusers();
        user.setIdUser(0);
        user.setUsuario(usuario);
        user.setContras(contras);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAwebPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.flush();
        em.getTransaction().commit();

        response.sendRedirect("lander/index.html");

//        PrintWriter out = response.getWriter();
//
//        response.setContentType("text/html;charset=UTF-8");//Trata los caracteres con acentos.   
//
//        AngyusersJpaController jpaContr = new AngyusersJpaController(emf);
//        jpaContr.create(user);
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
