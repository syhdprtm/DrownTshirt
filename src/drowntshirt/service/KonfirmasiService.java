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
    public Pembeli insertObj(Pembeli p){
        
        if (p.getNama() == null || p.getNama().isEmpty()) {
            System.out.println("Nama tidak boleh kosong!");
            return null;
        }

        Pembeli result = super.insert(p); 

        if (result !=null) {
            System.out.println("Insert berhasil!");
        } else {
            System.out.println("Insert gagal!");
        }

        return result;
    }
    
    public Pembeli updateObj(Pembeli p){
    if (p.getId() == 0) {
            System.out.println("Id tidak boleh kosong!");
            return null;
        }

        Pembeli result = super.update(p); 

        if (result!=null) {
            System.out.println("Update berhasil!");
        } else {
            System.out.println("Update gagal!");
        }

        return result;
}

    
    

}
