package gestion.classe.repository.implementation;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.database.services.Database;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Module;
import gestion.classe.entities.Professeur;
import gestion.classe.repository.ClasseRepository;
import gestion.classe.repository.ModuleRepository;
import gestion.classe.repository.ProfesseurRepository;

public class ProfesseurRepositoryImple implements ProfesseurRepository{
    private Database database;
    private ClasseRepository classeRepository;
    private ModuleRepository moduleRepository;
    private final String SQL_INSERT="INSERT INTO `professeur` (`nomComplet`,`isArchived`) VALUES (?,?)";
    private final String SQL_UPDATE="UPDATE `professeur` SET `nomComplet` = ? WHERE id = ? ";
    private final String SQL_ARCHIVER="UPDATE `professeur` SET `isArchived`= ? WHERE id = ?";
    private final String SQL_SELECT_ALL="SELECT * FROM professeur where isArchived=false";
    private final String SQL_ADD_MODULE_PROF="INSERT INTO `profmodule` (`id`, `idProf`, `idMod`) VALUES (NULL, ?, ?)";
    private final String SQL_VERIFY_MODULE_PROF="SELECT * FROM `profmodule` WHERE `idProf`=? AND `idMod`=?";
    private final String SQL_SELECT_PROF_BY_ID="SELECT * FROM professeur where isArchived=? AND id=?";
    private final String SQL_SELECT_CLASSE_BY_PROF="SELECT * FROM classeprofesseur where idProf=?";
    private final String SQL_FIND_ALL_PROFESSEUR_BY_MODULE="SELECT * FROM `profmodule` where idMod=?";
    private final String SQL_FIND_CLASSE_BY_PORFESSEUR_AND_MODULE="SELECT * FROM `profmodule` where `idProf`= ? and `idMod`= ?   ";


    public ProfesseurRepositoryImple(Database database,ClasseRepository classeRepository,ModuleRepository moduleRepository){
        this.database=database;
        this.classeRepository=classeRepository;
        this.moduleRepository=moduleRepository;
    }
    @Override
    public ArrayList<Professeur> findAll() {
       ArrayList<Professeur> professeurs=new ArrayList<>();
        try {
            database.openConnexion();
            database.initPreparedStatement(SQL_SELECT_ALL);
            ResultSet resultSet= database.executeSelect();
            while (resultSet.next()) {
                professeurs.add(new Professeur(resultSet.getInt("id"),
                                resultSet.getString("nomComplet")
                               ));
            }
            resultSet.close();
            database.closeConnexion();

        } catch (Exception e) {
            // TODO: handle exception
        }

        return professeurs;
    }

    @Override
    public Professeur findById(int id) {
        try {
             database.openConnexion();
                    database.initPreparedStatement(SQL_SELECT_PROF_BY_ID);
                     database.getPs().setBoolean(1,false);
                    database.getPs().setInt(2,id);
                    ResultSet resultSet=database.executeSelect();
                    if (resultSet.next()) {
                        
                        Professeur prof= new Professeur(id,resultSet.getString("nomComplet"));
                        return prof;

                    }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

  
    @Override
    public int insertOrUpdate(Professeur arg0) {
        int lastInsertId=0;
        boolean insert=arg0.getId()==0?true:false;
        try {
            database.openConnexion();
            database.initPreparedStatement(insert?SQL_INSERT:SQL_UPDATE);
            database.getPs().setString(1,arg0.getNomComplet());
            
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
    public boolean archiver(Professeur arg0) {
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
    public ArrayList<Module> getProfesseursModules(Professeur prof) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProfesseursModules'");
    }
    @Override
    public boolean addModuleProf(Professeur prof, Module module) {
         try {
            database.openConnexion();
            
           database.initPreparedStatement(SQL_VERIFY_MODULE_PROF);
           database.getPs().setInt(1, prof.getId());
           database.getPs().setInt(2, module.getId());
           ResultSet resultSet=database.executeSelect();
           if(!resultSet.next()){
             database.initPreparedStatement(SQL_ADD_MODULE_PROF);
                database.getPs().setInt(1, prof.getId());
                 database.getPs().setInt(2, module.getId());  
                    database.executeUpdate();
                 resultSet.close();
                database.closeConnexion();
                return true;
           }
           
       } catch (Exception e) {
           e.printStackTrace();
       }
       return false;
    }

    @Override
    public Map<Classe, ArrayList<Module>> getModulesAndClasseProfesseur(Professeur professeur) {
        Map<Classe,ArrayList<Module>> classeModules = new HashMap<>();

        try {
            database.openConnexion();
            database.initPreparedStatement(SQL_SELECT_CLASSE_BY_PROF);
            database.getPs().setInt(1, professeur.getId());
            ResultSet resultSet= database.executeSelect();
            while (resultSet.next()) {
                Classe classe=this.classeRepository.findById(resultSet.getInt("idClasse"));
                if(!classeModules.keySet().contains(classe)){
                    classeModules.put(classe,new ArrayList<Module>());
                }
                Module module=moduleRepository.findById(resultSet.getInt("idModule"));
                classeModules.get(classe).add(module);
            }
            resultSet.close();
            database.closeConnexion();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return classeModules;

    }

    @Override
    public ArrayList<Professeur> getProfesseurByModule(Module module) {
        ArrayList<Professeur> professeurs=new ArrayList<>();
        try {
            database.openConnexion();
            database.initPreparedStatement(SQL_FIND_ALL_PROFESSEUR_BY_MODULE);
            database.getPs().setInt(1, module.getId());
            ResultSet resultSet= database.executeSelect();
            while (resultSet.next()) {
                professeurs.add(findById(resultSet.getInt("id")));
            }
            resultSet.close();
            database.closeConnexion();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return professeurs;
    }
    @Override
    public ArrayList<Classe> findClasseByModuleAndProfesseur(Professeur professeur, Module module) {
        ArrayList<Classe> classes=new ArrayList<>();
        try {
             database.openConnexion();
                    database.initPreparedStatement(SQL_FIND_CLASSE_BY_PORFESSEUR_AND_MODULE);
                     database.getPs().setInt(1,professeur.getId());
                    database.getPs().setInt(2,module.getId());
                    ResultSet resultSet=database.executeSelect();
                    while (resultSet.next()) {
                        
                        Classe classe= classeRepository.findById(resultSet.getInt("idClasse"));
                        
                    classes.add(classe);
                    }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }
    
}
