package controller;

import model.Client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormClientServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String mdp = request.getParameter("mdp");

        Client client = new Client(nom, prenom, email, telephone, mdp);

        if (client.insert()) {
            response.sendRedirect("view/login.jsp");
            System.out.println("Meetttyyy");
        } else {
            request.setAttribute("errorMessage", "Erreur lors de l'inscription");
            request.getRequestDispatcher("view/inscription.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("view/inscription.jsp").forward(request, response);
    }
}
