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
    
    
    public Pembeli insert(Pembeli p) {
        String sql = "INSERT INTO pembeli (tipe, ukuran, nama, alamat) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getTipe());
            ps.setString(2, p.getUkuran());
            ps.setString(3, p.getNama());
            ps.setString(4, p.getAlamat());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                try (var rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idBaru = rs.getInt(1);
                        p.setId(idBaru);   
                    }
                }
            }

            return p;  

        } catch (SQLException e) {
            System.err.println("Gagal insert: " + e.getMessage());
            return null;
        }
    }

    
    public Pembeli update(Pembeli p) {
        String sql = "UPDATE pembeli SET tipe = ?, ukuran = ?, nama = ?, alamat = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getTipe());
            ps.setString(2, p.getUkuran());
            ps.setString(3, p.getNama());
            ps.setString(4, p.getAlamat());
            ps.setInt(5, p.getId());

            int rows = ps.executeUpdate();
            
            if (rows > 0) {
                return p;
            }
            return null;

        } catch (SQLException e) {
            System.err.println("Gagal update: " + e.getMessage());
            return null;
        }
    }

}
