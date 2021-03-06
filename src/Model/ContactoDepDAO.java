/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author beto-
 */
public class ContactoDepDAO {
    
    Connection conex= new Connection();
        PreparedStatement est;
        
        public void addContact(ContactoDepVO conDeportista, DeportistasVO deportista){

	try{
                est = conex.getConnection().prepareStatement("Call"
                        + " insert_into_MediosDeContactoDep (?,?,?,?,?,?)");
                est.setString(1, conDeportista.getNumCelularDep());
                est.setString(2, conDeportista.getNumCasaDep());
                est.setString(3, conDeportista.getCorreoDep());
                est.setString(4, deportista.getNombreDep());
                est.setString(5, deportista.getApPaDep());
                est.setString(6, deportista.getApMaDep());
                est.executeUpdate();
		est.close();
                conex.desconectar();
			
	} catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al registrar"
                    + " Contactos: \n"+e);
        }
    }
        
         public ContactoDepVO buscarPersona(int codigo){
        Connection conex= new Connection();
        ContactoDepVO personFound= new ContactoDepVO();
	boolean existe=false;
	try{
            PreparedStatement consulta;
            consulta = conex.getConnection().prepareStatement(
                    "SELECT * FROM MediosDeContactoDep where idDep = ? ");
            consulta.setInt(1, codigo);
            ResultSet res = consulta.executeQuery();
            while(res.next()){
                existe=true;
                personFound.setNumCelularDep(res.getString("CelDep"));
		personFound.setNumCasaDep(res.getString("TelCasaDep"));
		personFound.setCorreoDep(res.getString("CorreoDep"));
		
            }
            consulta.close();
            res.close();
            conex.desconectar();					
	} catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error: \n"+e);	}
        
	if (existe) {
            return personFound;
	} else return null;				
    }
   
        
    
    
}
