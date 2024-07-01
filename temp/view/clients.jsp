<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Client" %>
<!DOCTYPE html>
<html>
<head>
    <title>Clients</title>
</head>
<body>
    <h2>Liste des Clients</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Email</th>
                <th>Téléphone</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Client> clients = (List<Client>) request.getAttribute("clients");
                if (clients != null) {
                    for (Client client : clients) {
            %>
            <tr>
                <td><%= client.getIdClient() %></td>
                <td><%= client.getNom() %></td>
                <td><%= client.getPrenom() %></td>
                <td><%= client.getEmail() %></td>
                <td><%= client.getTelephone() %></td>
                <td>
                    <form action="#" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="idClient" value="<%= client.getIdClient() %>">
                        <button type="submit">Modifier</button>
                    </form>
                    <form action="#" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="idClient" value="<%= client.getIdClient() %>">
                        <button type="submit">Supprimer</button>
                    </form>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr style="color:red;">
                <td colspan="6">Aucun client trouvé.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
    <div>
        <span style="color:red;">
            <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>
        </span>
    </div>
</body>
</html>
