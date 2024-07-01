package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private int idClient;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String mdp;

    public Client() {}

    public Client(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }

    public Client(String nom, String prenom, String email, String telephone, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.mdp = mdp;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public boolean login() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Connect.getConnection();
            String query = "SELECT * FROM clients WHERE email = ? AND mdp = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, this.email);
            ps.setString(2, this.mdp);
            rs = ps.executeQuery();

            if (rs.next()) {
                this.idClient = rs.getInt("id_client");
                this.nom = rs.getString("nom");
                this.prenom = rs.getString("prenom");
                this.telephone = rs.getString("telephone");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnection(conn);
        }
        return false;
    }

    public boolean insert() {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Connect.getConnection();
            String query = "INSERT INTO clients (nom, prenom, email, telephone, mdp) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, this.nom);
            ps.setString(2, this.prenom);
            ps.setString(3, this.email);
            ps.setString(4, this.telephone);
            ps.setString(5, this.mdp);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnection(conn);
        }
        return false;
    }

    public boolean update() {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Connect.getConnection();
            String query = "UPDATE clients SET nom = ?, prenom = ?, email = ?, telephone = ?, mdp = ? WHERE id_client = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, this.nom);
            ps.setString(2, this.prenom);
            ps.setString(3, this.email);
            ps.setString(4, this.telephone);
            ps.setString(5, this.mdp);
            ps.setInt(6, this.idClient);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnection(conn);
        }
        return false;
    }

    public boolean delete() {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Connect.getConnection();
            String query = "DELETE FROM clients WHERE id_client = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, this.idClient);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnection(conn);
        }
        return false;
    }

    public static List<Client> getAllClients() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Client> clients = new ArrayList<>();

        try {
            conn = Connect.getConnection();
            String query = "SELECT * FROM clients";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Client client = new Client();
                client.setIdClient(rs.getInt("id_client"));
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setEmail(rs.getString("email"));
                client.setTelephone(rs.getString("telephone"));
                client.setMdp(rs.getString("mdp"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnection(conn);
        }
        return clients;
    }
}
