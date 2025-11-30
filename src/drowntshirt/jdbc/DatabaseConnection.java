/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drowntshirt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author afif
 */



public class DatabaseConnection {

    // ====== SETTING KONEKSI ======
    private static final String SERVER = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE = "drowntshirt";   // Ubah sesuai nama DB kamu
    private static final String USER = "root";
    private static final String PASSWORD = "root";              // Isi password MySQL kamu

    // URL JDBC
    private static final String URL =
            "jdbc:mysql://" + SERVER + ":" + PORT + "/" + DATABASE + "?useSSL=false";

    // ====== METHOD KONEKSI ======
    public static Connection getConnection() {
        Connection conn = null;

        try {
            // Load driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Mulai koneksi
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Koneksi ke MySQL berhasil!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySQL tidak ditemukan!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Gagal konek ke database!");
            e.printStackTrace();
        }

        return conn;
    }
    
}

