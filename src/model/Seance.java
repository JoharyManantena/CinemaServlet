package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Seance {
    private int idSeance;
    private int idFilm;
    private String dateSeance;
    private String heureDebut;
    private String heureFin;
    private String salle;

    public Seance() {}

    public Seance(int idFilm, String dateSeance, String heureDebut, String heureFin, String salle) {
        this.idFilm = idFilm;
        this.dateSeance = dateSeance;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.salle = salle;
    }

    public int getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getDateSeance() {
        return dateSeance;
    }

    public void setDateSeance(String dateSeance) {
        this.dateSeance = dateSeance;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public boolean insert() {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Connect.getConnection();
            String query = "INSERT INTO seances (id_film, date_seance, heure_debut, heure_fin, salle) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, this.idFilm);
            ps.setString(2, this.dateSeance);
            ps.setString(3, this.heureDebut);
            ps.setString(4, this.heureFin);
            ps.setString(5, this.salle);
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
            String query = "UPDATE seances SET id_film = ?, date_seance = ?, heure_debut = ?, heure_fin = ?, salle = ? WHERE id_seance = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, this.idFilm);
            ps.setString(2, this.dateSeance);
            ps.setString(3, this.heureDebut);
            ps.setString(4, this.heureFin);
            ps.setString(5, this.salle);
            ps.setInt(6, this.idSeance);
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
            String query = "DELETE FROM seances WHERE id_seance = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, this.idSeance);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnection(conn);
        }
        return false;
    }

    public static List<Seance> getAllSeances() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Seance> seances = new ArrayList<>();

        try {
            conn = Connect.getConnection();
            String query = "SELECT * FROM seances";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Seance seance = new Seance();
                seance.setIdSeance(rs.getInt("id_seance"));
                seance.setIdFilm(rs.getInt("id_film"));
                seance.setDateSeance(rs.getString("date_seance"));
                seance.setHeureDebut(rs.getString("heure_debut"));
                seance.setHeureFin(rs.getString("heure_fin"));
                seance.setSalle(rs.getString("salle"));
                seances.add(seance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnection(conn);
        }
        return seances;
    }
}
