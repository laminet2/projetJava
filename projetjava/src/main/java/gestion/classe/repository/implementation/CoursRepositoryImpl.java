package gestion.classe.repository.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import com.database.services.Database;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Cours;
import gestion.classe.entities.Module;
import gestion.classe.entities.Professeur;
import gestion.classe.entities.Salle;
import gestion.classe.repository.CoursRepository;
import gestion.classe.repository.ModuleRepository;
import gestion.classe.repository.SalleRepository;

import java.sql.Date;

public class CoursRepositoryImpl implements CoursRepository{
    private Database database;
    private ModuleRepository moduleRepository;
    private SalleRepository salleRepository;
    private final String ONLINE="EN LIGNE";
    private final String  SQL_INSERT="INSERT INTO `cours` (`id`, `libelle`, `date`, `heureDebut`, `heureFin`, `isArchived`, `idModule`, `idProfesseur`, `lieux`, `lien`, `idSalle`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String  SQL_INSERT_WITHOUT_SALLE="INSERT INTO `cours` (`id`, `libelle`, `date`, `heureDebut`, `heureFin`, `isArchived`, `idModule`, `idProfesseur`, `lieux`, `lien`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private final String SQL_INSERT_COURS_CLASSE="INSERT INTO `classecours` (`id`, `idCours`, `idClasse`) VALUES (NULL, ?, ?)";
    private final String SQL_FIND_COURS_BY_PROF="Select * from `cours` WHERE idProfesseur=? ORDER BY `date` DESC";
    private final String SQL_FIND_COURS_BY_ClASSE="Select * from `classecours` WHERE idClasse=? ";
    private final String SQL_SELECT_COURS_BY_ID="SELECT * FROM `cours` where isArchived=? and `id`=?";

    public CoursRepositoryImpl(Database database,ModuleRepository moduleRepository,SalleRepository salleRepository){
        this.database=database;
        this.moduleRepository=moduleRepository;
        this.salleRepository=salleRepository;
    }
    
    @Override
    public ArrayList<Cours> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Cours findById(int id) {
        try {
            database.openConnexion();
                   database.initPreparedStatement(SQL_SELECT_COURS_BY_ID);
                    database.getPs().setBoolean(1,false);
                   database.getPs().setInt(2,id);
                   ResultSet resultSet=database.executeSelect();
                   if (resultSet.next()) {
                       Module module=this.moduleRepository.findById(resultSet.getInt("idModule"));
                       String lieux=resultSet.getString("lieux");
                       Cours cours=new Cours(resultSet.getInt("id")
                                        ,resultSet.getString("libelle"),
                                        resultSet.getDate("date").toLocalDate(),
                                        resultSet.getTime("heureDebut").toLocalTime(),
                                        resultSet.getTime("heureFin").toLocalTime(),
                                        module,lieux);
                    if(lieux.equals(ONLINE)){
                        cours.setLien(resultSet.getString("lien"));
                    }else{
                       Salle salle=salleRepository.findById(resultSet.getInt("idSalle"));
                        cours.setSalle(salle);
                    }
                    return cours;

                   }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
    }

    @Override
    public int insertOrUpdate(Cours cours) {
        int lastInsertId=0;
        try {
            database.openConnexion();
            database.initPreparedStatement(cours.getSalle()==null?SQL_INSERT_WITHOUT_SALLE:SQL_INSERT);
            database.getPs().setString(1,cours.getLibelle());
            database.getPs().setDate(2,Date.valueOf(cours.getDate()));
            database.getPs().setTime(3,Time.valueOf(cours.getHeureDebut()));
            database.getPs().setTime(4,Time.valueOf(cours.getHeureFin()));
            database.getPs().setBoolean(5,cours.isArchived());
            database.getPs().setInt(6, cours.getModule().getId());
            database.getPs().setInt(7, cours.getProfesseur().getId());
            
            database.getPs().setString(8,cours.getLieux());
            database.getPs().setString(9,cours.getLien());
            if(cours.getSalle()!=null){
                database.getPs().setInt(10,cours.getSalle().getId());                
            }
            database.executeUpdate();

                ResultSet rs=  database.getPs().getGeneratedKeys();
               if(rs.next()){
                   lastInsertId=rs.getInt(1) ; 
                   rs.close();
                   for (Classe clas : cours.getClasses()) {
                        database.initPreparedStatement(SQL_INSERT_COURS_CLASSE);
                        database.getPs().setInt(1,lastInsertId);
                        database.getPs().setInt(2,clas.getId());
                        database.executeUpdate();
                    }
                   
               }
           
            database.closeConnexion();
           
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lastInsertId;
    }

  
    @Override
    public boolean archiver(Cours arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'archiver'");
    }

    @Override
    public ArrayList<Cours> findCoursByProfesseur(Professeur professeur) {
        ArrayList<Cours> datas=new ArrayList<>();
                  try {
                      database.openConnexion();
                        database.initPreparedStatement(SQL_FIND_COURS_BY_PROF);
                        database.getPs().setInt(1, professeur.getId());

                    ResultSet resultSet=database.executeSelect();
                    while (resultSet.next()) {
                        
                        Module module=this.moduleRepository.findById(resultSet.getInt("idModule"));
                       String lieux=resultSet.getString("lieux");
                       Cours cours=new Cours(resultSet.getInt("id")
                                        ,resultSet.getString("libelle"),
                                        resultSet.getDate("date").toLocalDate(),
                                        resultSet.getTime("heureDebut").toLocalTime(),
                                        resultSet.getTime("heureFin").toLocalTime(),
                                        module,lieux);
                    if(lieux.equals(ONLINE)){
                        cours.setLien(resultSet.getString("lien"));
                    }else{
                       Salle salle=salleRepository.findById(resultSet.getInt("idSalle"));
                        cours.setSalle(salle);
                    }
                         datas.add(cours);                       
                      }
                      resultSet.close();
                   database.closeConnexion();
                } catch (SQLException e) {

                    e.printStackTrace();
                }
            return datas;
    }

    @Override
    public ArrayList<Cours> findCoursByClasse(Classe classe) {
        ArrayList<Cours> datas=new ArrayList<>();
                  try {
                      database.openConnexion();
                        database.initPreparedStatement(SQL_FIND_COURS_BY_ClASSE);
                        database.getPs().setInt(1, classe.getId());

                    ResultSet resultSet=database.executeSelect();
                    while (resultSet.next()) {
                        
                        Cours data= findById(resultSet.getInt("idCours"));
                         datas.add(data);                       
                      }
                      resultSet.close();
                   database.closeConnexion();
                } catch (SQLException e) {

                    e.printStackTrace();
                }
            return datas;
    }
    
}
