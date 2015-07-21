/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadivulga.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Thiago
 */
public class Database {
    
    public Connection conn;
    public Statement statment = null;
    public PreparedStatement preparedStatement = null;
    public ResultSet resultSet = null;
    
    /**Realiza a conexão no banco de dados.*/
    public void connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //carrega o driver do mysql
            String url = "jdbc:mysql://192.168.2.251:3306/painel?autoReconnect=true"; //acessa a tablea mysql "unimed_biom_teste" no localhost
            String usuario = "Thiago";
            String senha = "root";
            conn = DriverManager.getConnection(url, usuario, senha); //conecta no banco de dados MySql
        }catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e){
            JOptionPane.showMessageDialog(null, "CONNECT - " + e.getMessage());
        }
    }
    
    public int atualizaCert(int id, int catual){
        try{
            PreparedStatement prepared;
            prepared = conn.prepareStatement("UPDATE diario SET catual=? where id= ?");
            prepared.setInt(1, catual);
            prepared.setInt(2, id);
            prepared.executeUpdate();
            return 1;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "atualizaCert - "+ e.getMessage());
        }
        return 0;
    }
    
    public int getId(){
        try {
            
            PreparedStatement prepared;
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
            prepared = conn.prepareStatement("SELECT id FROM diario WHERE data=?");
            prepared.setString(1, date.toString());
            resultSet = prepared.executeQuery();
            int n;
            while(resultSet.next()){
                n = resultSet.getInt(1);
                return n;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "getId - "+ e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }
}
