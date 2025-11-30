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
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
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
    
    
    public List<Pembeli> selectList(String nama, String tipe, String ukuran) {
        List<Pembeli> list = new ArrayList<>();

        String sql = "SELECT * FROM pembeli WHERE 1=1";

        if (!nama.isEmpty()) sql += " AND nama LIKE ?";
        if (!tipe.equals("Semua")) sql += " AND tipe = ?";
        if (!ukuran.equals("Semua")) sql += " AND ukuran = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            int idx = 1;
            if (!nama.isEmpty()) ps.setString(idx++, "%" + nama + "%");
            if (!tipe.equals("Semua")) ps.setString(idx++, tipe);
            if (!ukuran.equals("Semua")) ps.setString(idx++, ukuran);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pembeli p = new Pembeli();
                p.setId(rs.getInt("id"));
                p.setTipe(rs.getString("tipe"));
                p.setUkuran(rs.getString("ukuran"));
                p.setNama(rs.getString("nama"));
                p.setAlamat(rs.getString("alamat"));
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<Pembeli> deleteById(int id, String nama, String tipe, String ukuran) {
        String sql = "DELETE FROM pembeli WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Gagal hapus: " + e.getMessage());
        }

        // kembalikan daftar terbaru setelah delete
        return selectList(nama, tipe, ukuran);
    }



}


