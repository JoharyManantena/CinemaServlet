package controller;

import model.Client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClientServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Client> clients = Client.getAllClients();
        request.setAttribute("clients", clients);
        request.getRequestDispatcher("view/clients.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            int idClient = Integer.parseInt(request.getParameter("idClient"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String telephone = request.getParameter("telephone");
            String mdp = request.getParameter("mdp");

            Client client = new Client(nom, prenom, email, telephone, mdp);
            client.setIdClient(idClient);

            if (client.update()) {
                response.sendRedirect("ClientServlet");
            } else {
                request.setAttribute("errorMessage", "Erreur lors de la mise à jour");
                request.getRequestDispatcher("view/clients.jsp").forward(request, response);
            }
        }
         else if ("delete".equals(action)) {
            int idClient = Integer.parseInt(request.getParameter("idClient"));

            Client client = new Client();
            client.setIdClient(idClient);

            if (client.delete()) {
                response.sendRedirect("ClientServlet");
            } else {
                request.setAttribute("errorMessage", "Erreur lors de la suppression");
                request.getRequestDispatcher("view/clients.jsp").forward(request, response);
            }
        }
    }
}
