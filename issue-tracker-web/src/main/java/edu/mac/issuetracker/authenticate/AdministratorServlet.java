//
//import jakarta.inject.Inject;
//import jakarta.security.enterprise.SecurityContext;
//import jakarta.servlet.annotation.HttpConstraint;
//import jakarta.servlet.annotation.ServletSecurity;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//
/////*
//// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//// */
////package edu.mac.issuetracker.authenticate;
////
////import jakarta.servlet.annotation.HttpConstraint;
////import jakarta.servlet.annotation.ServletSecurity;
////import jakarta.servlet.annotation.WebServlet;
////import jakarta.servlet.http.HttpServlet;
////
/////**
//// *
//// * @author Jaibun Thana
//// */
//@WebServlet("/protectedServlet")
//@ServletSecurity(@HttpConstraint(rolesAllowed = "admin"))
//public class AdministratorServlet extends HttpServlet {
//    
//    @Inject
//    private SecurityContext securityContext;
//    
//    private static final long serialVersionUID = 1L;
//
//    @Override
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String webName = null;
//        if (securityContext.hasAccessToWebResource(webName, arg1)!= null) {
//            webName = request.getUserPrincipal().getName();
//        }
//        
//        response.getWriter().write(
//                "<html><body> This is a servlet <br><br>\n" +
//        
//                    "web username: " + webName + "<br><br>\n" +
//                            
//                    "web user has role \"foo\": " + request.isUserInRole("foo") + "<br>\n" +
//                    "web user has role \"bar\": " + request.isUserInRole("bar") + "<br>\n" +
//                    "web user has role \"kaz\": " + request.isUserInRole("kaz") + "<br><br>\n" + 
//
//                        
//                    "<form method=\"POST\">" +
//                        "<input type=\"hidden\" name=\"logout\" value=\"true\"  >" +
//                        "<input type=\"submit\" value=\"Logout\">" +
//                    "</form>" +
//                "</body></html>");
//    }
//    
//    @Override
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if ("true".equals(request.getParameter("logout"))) {
//            request.logout();
//            request.getSession().invalidate();
//        }
//        
//        doGet(request, response);
//    }
//    
//    
//    
//}
