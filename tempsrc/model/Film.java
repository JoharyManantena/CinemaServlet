package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Film {
    private int idFilm;
    private String titre;
    private String realisateur;
    private int anneeSortie;
    private String genre;

    public Film() {}

    public Film(String titre, String realisateur, int anneeSortie, String genre) {
        this.titre = titre;
        this.realisateur = realisateur;
        this.anneeSortie = anneeSortie;
        this.genre = genre;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public int getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(int anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean insert() {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Connect.getConnection();
            String query = "INSERT INTO films (titre, realisateur, annee_sortie, genre) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, this.titre);
            ps.setString(2, this.realisateur);
            ps.setInt(3, this.anneeSortie);
            ps.setString(4, this.genre);
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
            String query = "UPDATE films SET titre = ?, realisateur = ?, annee_sortie = ?, genre = ? WHERE id_film = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, this.titre);
            ps.setString(2, this.realisateur);
            ps.setInt(3, this.anneeSortie);
            ps.setString(4, this.genre);
            ps.setInt(5, this.idFilm);
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
            String query = "DELETE FROM films WHERE id_film = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, this.idFilm);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnection(conn);
        }
        return false;
    }

    public static List<Film> getAllFilms() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Film> films = new ArrayList<>();

        try {
            conn = Connect.getConnection();
            String query = "SELECT * FROM films";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Film film = new Film();
                film.setIdFilm(rs.getInt("id_film"));
                film.setTitre(rs.getString("titre"));
                film.setRealisateur(rs.getString("realisateur"));
                film.setAnneeSortie(rs.getInt("annee_sortie"));
                film.setGenre(rs.getString("genre"));
                films.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnection(conn);
        }
        return films;
    }
}
