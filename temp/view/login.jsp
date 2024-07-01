<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="log" method="post">
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="jean.dupont@example.com" required>
        </div>
        <div>
            <label for="mdp">Mot de passe:</label>
            <input type="password" id="mdp" name="mdp" value="password123">
        </div>
        <div>
            <button type="submit">Login</button>
        </div>
        <div>
            <span style="color:red;">
                <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>
            </span>
        </div>
    </form>
    <div>
        <p>Pas encore inscrit? <a href="inscription">S'inscrire ici</a></p>
    </div>
</body>
</html>
