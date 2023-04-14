/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.servlet;

import edu.mac.issuetracker.staff.manager.StaffManager;
import jakarta.inject.Inject;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author tohyu
 */
@WebFilter(filterName="AuthFilter", urlPatterns={"/*"})
//Authorisation Filter
public class AuthFilter implements Filter {

    @Inject
    private StaffManager staffManager;
    
    public AuthFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        boolean isLoggedIn = staffManager.isAuthenticated();
        
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        boolean isLoginPage = uri.equals(
                contextPath + "/login.xhtml");
  
        boolean isResources = uri.contains(contextPath + "/jakarta.faces.resource/");
        //boolean isResource = uri
        
        // Check if the user is accessing login page
        if (isLoginPage) {
            
            if (isLoggedIn) {
                //Check the role (if manager, xuser or admin)

                // Redirect to landing or home page
                HttpServletResponse res = (HttpServletResponse) response;
                res.sendRedirect(req.getContextPath()
                        + "/page/user/dashboard.xhtml");
            } else {
                // Otherwise, nothing to do if he has not logged in
                // pass the request along the filter chain
                chain.doFilter(request, response);
            }
        }else if(isResources){
            chain.doFilter(request, response);
        } else {
            // For all other pages,
            if (isLoggedIn) {
                // Nothing to do
                
                //check permission
                
                //remove the following command
                chain.doFilter(request, response);
            } else {
                // Redirect to login page if he has not logged in
                HttpServletResponse res = (HttpServletResponse) response;
                res.sendRedirect(req.getContextPath() + "/login.xhtml");
            }
        }
        
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }
    
}
