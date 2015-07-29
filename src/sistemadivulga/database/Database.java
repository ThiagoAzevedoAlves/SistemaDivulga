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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
            String url = "jdbc:mysql://192.168.0.100:3306/painel?autoReconnect=true";
            String usuario = "Thiago";
            String senha = "root";
            conn = DriverManager.getConnection(url, usuario, senha); //conecta no banco de dados MySql
        }catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e){
            JOptionPane.showMessageDialog(null, "CONNECT - " + e.getMessage());
        }
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
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "getId - "+ e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }
        
    public int getIdMedia(){
        try {
            PreparedStatement prepared;
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
            prepared = conn.prepareStatement("SELECT id FROM media WHERE data=?");
            prepared.setString(1, date.toString());
            resultSet = prepared.executeQuery();
            int n;
            while(resultSet.next()){
                n = resultSet.getInt(1);
                return n;
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "getId - "+ e.getMessage());
            e.printStackTrace();
        }
        return -1;
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
    
    public int atualizaPref(int id, int patual){
        try{
            PreparedStatement prepared;
            prepared = conn.prepareStatement("UPDATE diario SET patual=? where id= ?");
            prepared.setInt(1, patual);
            prepared.setInt(2, id);
            prepared.executeUpdate();
            return 1;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "atualizaCert - "+ e.getMessage());
        }
        return 0;
    }
    
    public int atualizaReg(int id, int ratual){
        try{
            PreparedStatement prepared;
            prepared = conn.prepareStatement("UPDATE diario SET ratual=? where id= ?");
            prepared.setInt(1, ratual);
            prepared.setInt(2, id);
            prepared.executeUpdate();
            return 1;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "atualizaCert - "+ e.getMessage());
        }
        return 0;
    }
    
    public int addCert(int id, int certidoes){
        try{
            PreparedStatement prepared;
            prepared = conn.prepareStatement("UPDATE diario SET certidoes=? where id= ?");
            prepared.setInt(1, certidoes);
            prepared.setInt(2, id);
            prepared.executeUpdate();
            return 1;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "atualizaCert - "+ e.getMessage());
        }
        return 0;
    }
    
    public int addPref(int id, int preferencial){
        try{
            PreparedStatement prepared;
            prepared = conn.prepareStatement("UPDATE diario SET preferencial=? where id= ?");
            prepared.setInt(1, preferencial);
            prepared.setInt(2, id);
            prepared.executeUpdate();
            return 1;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "atualizaCert - "+ e.getMessage());
        }
        return 0;
    }
    
    public int addReg(int id, int registros){
        try{
            PreparedStatement prepared;
            prepared = conn.prepareStatement("UPDATE diario SET registros=? where id= ?");
            prepared.setInt(1, registros);
            prepared.setInt(2, id);
            prepared.executeUpdate();
            return 1;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "atualizaCert - "+ e.getMessage());
        }
        return 0;
    }
        
    public int getCertAtual(){
        try {
            
            PreparedStatement prepared;
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
            prepared = conn.prepareStatement("SELECT catual FROM diario WHERE data=?");
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
    
    public int getPrefAtual(){
        try {
            
            PreparedStatement prepared;
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
            prepared = conn.prepareStatement("SELECT patual FROM diario WHERE data=?");
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
    
    public int getRegAtual(){
        try {
            
            PreparedStatement prepared;
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
            prepared = conn.prepareStatement("SELECT ratual FROM diario WHERE data=?");
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
    
    public int getCertFinal(){
        try {
            
            PreparedStatement prepared;
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
            prepared = conn.prepareStatement("SELECT certidoes FROM diario WHERE data=?");
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
    
    public int getPrefFinal(){
        try {
            
            PreparedStatement prepared;
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
            prepared = conn.prepareStatement("SELECT preferencial FROM diario WHERE data=?");
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
    
    public int getRegFinal(){
        try {
            
            PreparedStatement prepared;
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
            prepared = conn.prepareStatement("SELECT registros FROM diario WHERE data=?");
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
    
    public void Inicia(){
        boolean existe = false;
        try{            
            PreparedStatement prepared;
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
            prepared = conn.prepareStatement("SELECT id FROM diario WHERE data=?");
            prepared.setString(1, date.toString());
            resultSet = prepared.executeQuery();
            while(resultSet.next()){
                existe = true;
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "getId - "+ e.getMessage());
            e.printStackTrace();
        }
        if(existe==false){
            JOptionPane.showMessageDialog(null, "Bom dia!");
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
            try{
                String sql;
                sql = "insert into diario values(default, ?, ?, ?, ?, ?, ?, ?)";
                preparedStatement = conn.prepareStatement(sql); //prepara os argumentos;
                preparedStatement.setString(1, date.toString());
                preparedStatement.setInt(2, 100);
                preparedStatement.setInt(3, 0);
                preparedStatement.setInt(4, 200);
                preparedStatement.setInt(5, 100);
                preparedStatement.setInt(6, 0);
                preparedStatement.setInt(7, 200);
                preparedStatement.executeUpdate(); //executa o update na tabela
                existe = true;
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Inicia" + "\n" + e.getMessage());            
            }
        }
    }
    
    public void salvaMedia(int mcert, int mpref, int mreg){
        boolean existe = false;
        try{            
            PreparedStatement prepared;
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
            prepared = conn.prepareStatement("SELECT id FROM media WHERE data=?");
            prepared.setString(1, date.toString());
            resultSet = prepared.executeQuery();
            while(resultSet.next()){
                existe = true;
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "getId - "+ e.getMessage());
            e.printStackTrace();
        }
        if(existe==false){
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
            try{
                String sql;
                sql = "insert into media values(default, ?, ?, ?, ?)";
                preparedStatement = conn.prepareStatement(sql); //prepara os argumentos;
                preparedStatement.setString(1, date.toString());
                preparedStatement.setInt(2, mcert);
                preparedStatement.setInt(3, mpref);
                preparedStatement.setInt(4, mreg);
                preparedStatement.executeUpdate(); //executa o update na tabela
                existe = true;
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "salvaMedia" + "\n" + e.getMessage());            
            }
        }
    }
    
    public void geraAtendimento(int tipo){
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Date date = new java.sql.Date(currentDate.getTime());
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String horas = dateFormat.format(date).split(":", 2)[0];
        String minutos = dateFormat.format(date).split(":", 2)[1];
        String total = dateFormat.format(date);
        try{
            String sql;
            sql = "insert into horariochegada values(default, ?, ?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(sql); //prepara os argumentos;
            preparedStatement.setString(1, date.toString());
            preparedStatement.setString(2, total);
            preparedStatement.setInt(3, Integer.valueOf(horas));
            preparedStatement.setInt(4, Integer.valueOf(minutos));
            if(tipo == 0){
                preparedStatement.setString(5, "p");
            }else if(tipo == 1){
                preparedStatement.setString(5, "c");
            }else if(tipo == 2){
                preparedStatement.setString(5, "r");
            }            
            preparedStatement.executeUpdate(); //executa o update na tabela
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "geraAtendimento" + "\n" + e.getMessage());            
        }
    }
    
    public void geraChamado(int tipo){
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Date date = new java.sql.Date(currentDate.getTime());
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String horas = dateFormat.format(date).split(":", 2)[0];
        String minutos = dateFormat.format(date).split(":", 2)[1];
        String total = dateFormat.format(date);
        try{
            String sql;
            sql = "insert into horariochamada values(default, ?, ?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(sql); //prepara os argumentos;
            preparedStatement.setString(1, date.toString());
            preparedStatement.setString(2, total);
            preparedStatement.setInt(3, Integer.valueOf(horas));
            preparedStatement.setInt(4, Integer.valueOf(minutos));
            if(tipo == 0){
                preparedStatement.setString(5, "p");
            }else if(tipo == 1){
                preparedStatement.setString(5, "c");
            }else if(tipo == 2){
                preparedStatement.setString(5, "r");
            }            
            preparedStatement.executeUpdate(); //executa o update na tabela
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "geraAtendimento" + "\n" + e.getMessage());            
        }
    }
    
}
