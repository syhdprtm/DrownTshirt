/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drowntshirt.jdbc;
import drowntshirt.jdbc.DatabaseConnection;
import drowntshirt.model.Pembeli;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author syuhada
 */
public class Crud {
    
    
    public boolean insert(Pembeli p) {
        String sql = "INSERT INTO pembeli (tipe, ukuran, nama, alamat) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getTipe());
            ps.setString(2, p.getUkuran());
            ps.setString(3, p.getNama());
            ps.setString(4, p.getAlamat());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Gagal insert: " + e.getMessage());
            return false;
        }
    }
}
