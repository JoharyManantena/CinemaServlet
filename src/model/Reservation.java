package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private int idReservation;
    private int idSeance;
    private int idClient;
    private int nombreTickets;

    public Reservation() {}

    public Reservation(int idSeance, int idClient, int nombreTickets) {
        this.idSeance = idSeance;
        this.idClient = idClient;
        this.nombreTickets = nombreTickets;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public int getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getNombreTickets() {
        return nombreTickets;
    }

    public void setNombreTickets(int nombreTickets) {
        this.nombreTickets = nombreTickets;
    }

    public boolean insert() {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Connect.getConnection();
            String query = "INSERT INTO reservations (id_seance, id_client, nombre_tickets) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, this.idSeance);
            ps.setInt(2, this.idClient);
            ps.setInt(3, this.nombreTickets);
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
            String query = "UPDATE reservations SET id_seance = ?, id_client = ?, nombre_tickets = ? WHERE id_reservation = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, this.idSeance);
            ps.setInt(2, this.idClient);
            ps.setInt(3, this.nombreTickets);
            ps.setInt(4, this.idReservation);
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
            String query = "DELETE FROM reservations WHERE id_reservation = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, this.idReservation);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnection(conn);
        }
        return false;
    }

    public static List<Reservation> getAllReservations() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Reservation> reservations = new ArrayList<>();

        try {
            conn = Connect.getConnection();
            String query = "SELECT * FROM reservations";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setIdReservation(rs.getInt("id_reservation"));
                reservation.setIdSeance(rs.getInt("id_seance"));
                reservation.setIdClient(rs.getInt("id_client"));
                reservation.setNombreTickets(rs.getInt("nombre_tickets"));
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnection(conn);
        }
        return reservations;
    }
}
