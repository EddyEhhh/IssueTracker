package edu.mac.issuetracker.servlet;

import java.io.IOException;

import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@DeclareRoles({"admin","basic"})
@ServletSecurity(@HttpConstraint(rolesAllowed="admin"))
@WebServlet("/secure")

public class SecureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	private SecurityContext securityContext;
	
    public SecureServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("User: ").append(securityContext.getCallerPrincipal().getName());
	}

}
