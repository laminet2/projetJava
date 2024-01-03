package gestion.classe.repository.implementation;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.database.services.Database;
import com.mysql.cj.protocol.Resultset;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Module;
import gestion.classe.entities.Professeur;
import gestion.classe.repository.ModuleRepository;
import gestion.classe.repository.ProfesseurRepository;

public class ModuleRepositoryImple implements ModuleRepository{
    private final String SQL_INSERT="INSERT INTO `module` (`libelle`,`isArchived`) VALUES (?,?)";
    private final String SQL_ARCHIVER="UPDATE `module` SET `isArchived`= ? WHERE id = ?";
    private final String SQL_SELECT_ALL="SELECT * FROM `module` where isArchived=false";
    private final String SQL_UPDATE="UPDATE `module` SET `libelle` = ? WHERE id = ? ";
    private final String SQL_FIND_ALL_PROFESSEUR_BY_MODULE="SELECT * FROM `profmodule` where idMod=?";

    private Database database;
    private ProfesseurRepository professeurRepository;
    private ModuleRepository moduleRepository;
    public ModuleRepositoryImple(Database database,ProfesseurRepository professeurRepository){
        this.database=database;
        this.professeurRepository=professeurRepository;
        
    }
    @Override
    public ArrayList<Module> findAll() {
        ArrayList<Module> modules=new ArrayList<>();
        try {
            database.openConnexion();
            database.initPreparedStatement(SQL_SELECT_ALL);
            ResultSet resultSet= database.executeSelect();
            while (resultSet.next()) {
                modules.add(new Module(resultSet.getInt("id"),
                                resultSet.getString("libelle")));
            }
            resultSet.close();
            database.closeConnexion();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return modules;
    }

    @Override
    public Module findById(int arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public int insertOrUpdate(Module arg0) {
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
    public boolean archiver(Module arg0) {
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
    public ArrayList<Professeur> getProfesseur(Module module) {
        ArrayList<Professeur> professeurs=new ArrayList<>();
        try {
            database.openConnexion();
            database.initPreparedStatement(SQL_FIND_ALL_PROFESSEUR_BY_MODULE);
            database.getPs().setInt(1, module.getId());
            ResultSet resultSet= database.executeSelect();
            while (resultSet.next()) {
                professeurs.add(professeurRepository.findById(resultSet.getInt("id")));
            }
            resultSet.close();
            database.closeConnexion();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return professeurs;
    }

}
