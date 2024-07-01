<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inscription</title>
</head>
<body>
    <h2>Inscription</h2>
    <form action="inscription" method="post">
        <div>
            <label for="nom">Nom:</label>
            <input type="text" id="nom" name="nom" required>
        </div>
        <div>
            <label for="prenom">Prénom:</label>
            <input type="text" id="prenom" name="prenom" required>
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div>
            <label for="telephone">Téléphone:</label>
            <input type="text" id="telephone" name="telephone" required>
        </div>
        <div>
            <label for="mdp">Mot de passe:</label>
            <input type="password" id="mdp" name="mdp" required>
        </div>
        <div>
            <button type="submit">S'inscrire</button>
        </div>
        <div>
            <span style="color:red;">
                <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>
            </span>
        </div>
    </form>
</body>
</html>
