package gestion.classe.repository.implementation;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.database.services.Database;

import gestion.classe.entities.Module;
import gestion.classe.entities.Professeur;
import gestion.classe.entities.Salle;
import gestion.classe.repository.SalleRepository;

public class SalleRepositoryImple implements SalleRepository{
    private Database database;
    private final String SQL_SALLE_DISPO="SELECT * FROM `cours` WHERE date=?  AND `idSalle`=? AND ? BETWEEN `heureDebut` AND `heureFin` ";
    private final String SQL_SELECT_SALLE_BY_ID="SELECT * FROM `salle` where isArchived=? AND id=?";
    private final String SQL_SELECT_ALL="SELECT * FROM `salle` where isArchived=?";
    private final String SQL_INSERT="INSERT INTO `salle` (`libelle`,`isArchived`) VALUES (?,?)";
    private final String SQL_UPDATE="UPDATE `salle` SET `libelle` = ? WHERE id = ? ";
    private final String SQL_ARCHIVER="UPDATE `salle` SET `isArchived`= ? WHERE id = ?";

    public SalleRepositoryImple(Database database){
        this.database=database;
    }
    
    @Override
    public ArrayList<Salle> findAll() {
        ArrayList<Salle> salles=new ArrayList<>();
        try {
            database.openConnexion();
            database.initPreparedStatement(SQL_SELECT_ALL);
            database.getPs().setBoolean(1, false);
            ResultSet resultSet= database.executeSelect();
            while (resultSet.next()) {
                salles.add(new Salle(resultSet.getInt("id"),
                                resultSet.getString("libelle")));
            }
            resultSet.close();
            database.closeConnexion();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return salles;
    }

    @Override
    public Salle findById(int id) {
        try {
            database.openConnexion();
                   database.initPreparedStatement(SQL_SELECT_SALLE_BY_ID);
                    database.getPs().setBoolean(1,false);
                   database.getPs().setInt(2,id);
                   ResultSet resultSet=database.executeSelect();
                   if (resultSet.next()) {
                       
                       Salle salle=new Salle(resultSet.getInt("id")
                                        ,resultSet.getString("libelle"));
                       return salle;

                   }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
    }

    @Override
    public int insertOrUpdate(Salle arg0) {
        int lastInsertId=0;
        boolean insert=arg0.getId()==0?true:false;
        try {
            database.openConnexion();
            database.initPreparedStatement(insert?SQL_INSERT:SQL_UPDATE);
            database.getPs().setString(1,arg0.getLibelle());
            
            if(insert){
                database.getPs().setBoolean(2, false);
                
            }else{
                database.getPs().setInt(2, arg0.getId());
                lastInsertId=1;
            }
            database.executeUpdate();

            if(insert){
                ResultSet rs=  database.getPs().getGeneratedKeys();
               if(rs.next()){
                   lastInsertId=rs.getInt(1) ; 
               }
           }
            database.closeConnexion();
           
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lastInsertId;
    }
    @Override
    public boolean archiver(Salle arg0) {
        try {
            database.openConnexion();
           database.initPreparedStatement(SQL_ARCHIVER);
           database.getPs().setBoolean(1, true);
           database.getPs().setInt(2,arg0.getId());
           database.executeUpdate();
           database.closeConnexion();
       } catch (Exception e) {
           e.printStackTrace();
       }
       return true;
    }

    @Override
    public ArrayList<Salle> findSalleByDateAndTime(LocalDate date, LocalTime heureDebut) {
        ArrayList<Salle> salles=findAll();
        try {
            ArrayList<Salle> salleDispo=new ArrayList<>();
            database.openConnexion();
            for (Salle salle : salles) {
                //salle Dispo a cette heure et a cette date ?
                //Y'a t'il un cours dans cette salle a cette date
                database.initPreparedStatement(SQL_SALLE_DISPO);
                database.getPs().setDate(1, Date.valueOf(date));
                database.getPs().setInt(2, salle.getId());

                database.getPs().setTime(3, Time.valueOf(heureDebut));
                ResultSet resultSet= database.executeSelect();
                if(!resultSet.next()){
                    salleDispo.add(salle);
                }

            }
                     
            database.closeConnexion();
            return salleDispo;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
}
