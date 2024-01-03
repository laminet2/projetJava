package com.database.services.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.database.services.Database;

public class MySql implements Database{

    private final String driver="com.mysql.cj.jdbc.Driver";
    private  String url;
    private  String user;
    private  String password;

    private  Connection conn=null;
    private PreparedStatement ps=null;

    public  MySql(String url,String user,String password){
        this.url=url;
        this.user=user;
        this.password=password;
    }

    @Override
    public PreparedStatement getPs() {
        return ps;
    }

    @Override
    public void openConnexion() {
         try {
            Class.forName(driver);
           conn = DriverManager.getConnection(url,user, password);
        } catch (ClassNotFoundException e) {
              System.out.println("Erreur de chargement du Driver");
        } catch (SQLException e) {
            System.out.println("Erreur d'ouverture de la connexion");
        }
                 
    }

    @Override
    public void closeConnexion() {
        if (conn!=null) {
              try {
                conn.close();

            } catch (SQLException e) {
                System.out.println("Erreur de fermeture de BD ");
            }
        } else {
            
        }
    }

    @Override
    public ResultSet executeSelect() {
        ResultSet rs=null;
          try {   
            rs=ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    //insert ou update ou delete
    //update ou delete => nbreLigne affecte par la requete
    //insert  le last insert id
    /*
     *  insert into ..... 
     *  select Max(id)
     */
    
    @Override
    public int executeUpdate() {
        int nbreLigne=0;
       try {
            nbreLigne=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nbreLigne;
    }

    @Override
    public void initPreparedStatement(String sql) {
        try {
            if(sql.toLowerCase().startsWith("insert")){
               ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }else{
                ps=conn.prepareStatement(sql);
            }
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}