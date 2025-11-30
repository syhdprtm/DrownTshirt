/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drowntshirt.service;
import drowntshirt.jdbc.Crud;
import drowntshirt.model.Pembeli;


/**
 *
 * @author syuhada
 */
public class KonfirmasiService extends Crud{
    public boolean insertObj(Pembeli p){
        
        if (p.getNama() == null || p.getNama().isEmpty()) {
            System.out.println("Nama tidak boleh kosong!");
            return false;
        }

        boolean result = super.insert(p); 

        if (result) {
            System.out.println("Insert berhasil!");
        } else {
            System.out.println("Insert gagal!");
        }

        return result;
    }
    
    public static void main(String[] args) {
        Pembeli p = new Pembeli();
        KonfirmasiService k = new KonfirmasiService();
        
        k.insertObj(p);
        
    }
//    public boolean insert(Pembeli p) {
//        String sql = "INSERT INTO pembeli (tipe, ukuran, nama, alamat) VALUES (?, ?, ?, ?)";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setString(1, p.getTipe());
//            ps.setString(2, String.valueOf(p.getUkuran()));
//            ps.setString(3, p.getNama());
//            ps.setString(4, p.getAlamat());
//
//            ps.executeUpdate();
//            return true;
//
//        } catch (SQLException e) {
//            System.err.println("Gagal insert: " + e.getMessage());
//            return false;
//        }
//    }
}
