package gestion.classe.repository.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
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

public class ClasseRepositoryImple implements ClasseRepository{
    private final String SQL_INSERT="INSERT INTO `classe` (`libelle`,`filiere`,`niveau`,`isArchived`) VALUES (?,?,?,?)";
    private final String SQL_SELECT_ALL="SELECT * FROM classe where isArchived=?";
    private final String SQL_UPDATE="UPDATE `classe` SET `libelle` = ?, `filiere` = ? ,`niveau`= ? WHERE id = ? ";
    private final String SQL_ARCHIVER="UPDATE `classe` SET `isArchived`= ? WHERE id = ?";
    private final String SQL_INSERT_MODULE_CLASSE="INSERT INTO `classeprofesseur` (`id`, `idProf`, `idClasse`, `idModule`) VALUES (NULL, ?, ?, ?) ";
    private final String SQL_DELETE_PROFCLASSE_BY_CLASSE="DELETE FROM `classeprofesseur` WHERE `idClasse`= ?";
    private final String SQL_SELECT_MODULE_CLASSE="SELECT * FROM `classeprofesseur` WHERE `idClasse`=? ";
    private final String SQL_VERIFY_MODULE_EXIST="SELECT * FROM `classeprofesseur` WHERE `idClasse`=? and `idModule`=? ";
    private final String SQL_SELECT_PROFESSEUR_BY_CLASSE="SELECT * FROM `classeprofesseur` WHERE `idClasse`=?";
    private final String SQL_SELECT_CLASSE_BY_ID="SELECT * FROM classe where isArchived=? and `id`=?";
    private final String SQL_FIND_MODULE_BY_CLASSE="SELECT * FROM classeprofesseur where  `idClasse`=?";
    private final String SQL_FIND_CLASSE_BY_MODULE="SELECT * FROM classeprofesseur where  `idModule`=?";
    

    private Database dataBase;
    private ModuleRepository moduleRepository;
    public ClasseRepositoryImple(Database database,ModuleRepository moduleRepository){
        this.dataBase=database;
        this.moduleRepository=moduleRepository;
    }
    @Override
        public int insertOrUpdate(Classe data) {

            int lastInsertId=0;
            boolean insert= data.getId()==0 ? true:false;
            
                try {
                    dataBase.openConnexion();
                    dataBase.initPreparedStatement(insert?SQL_INSERT:SQL_UPDATE);
                    dataBase.getPs().setString(1,data.getLibelle().toUpperCase());
                    dataBase.getPs().setString(2,data.getFiliere());
                    dataBase.getPs().setString(3,data.getNiveau());

                    if (insert) {
                     dataBase.getPs().setBoolean(4, data.getIsArchived());
                    }else{
                     dataBase.getPs().setInt(4, data.getId());
                     lastInsertId=data.getId();
                     
                    }

                    dataBase.executeUpdate();
                    if(insert){
                         ResultSet rs=  dataBase.getPs().getGeneratedKeys();

                        if(rs.next()){
                            lastInsertId=rs.getInt(1) ; 
                        }
                    }
                    dataBase.closeConnexion();

                //     //Update les info dans  `classeprofesseur`
                //     dataBase.openConnexion();
                //     dataBase.initPreparedStatement(SQL_DELETE_PROFCLASSE_BY_CLASSE);
                //     dataBase.getPs().setInt(1,data.getId());
                //     dataBase.executeUpdate(); 
                //     dataBase.initPreparedStatement(SQL_INSERT_MODULE_CLASSE);
                //     for (Professeur prof : data.getProfesseurModules().keySet()) {
                //         ArrayList<Module> modules = data.getProfesseurModules().get(prof);
                //         // System.out.println("Clé : " + cle + ", Valeur : " + valeur);
                //         for (Module module : modules) {
                //             dataBase.getPs().setInt(1,prof.getId());
                //             dataBase.getPs().setInt(2,data.getId());
                //             dataBase.getPs().setInt(3,module.getId());
                //             dataBase.executeUpdate();
                //         }
                       
                //    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return lastInsertId; 
        }
    
    @Override  
    public boolean archiver(Classe data){
        try {
            dataBase.openConnexion();
             dataBase.initPreparedStatement(SQL_ARCHIVER);

           dataBase.getPs().setBoolean(1,true);
           dataBase.getPs().setInt(2,data.getId());
           dataBase.executeUpdate();
             dataBase.closeConnexion();


        }catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public ArrayList<Classe> findAll() {
        ArrayList<Classe> datas=new ArrayList<>();
                  try {
                      dataBase.openConnexion();
                        dataBase.initPreparedStatement(SQL_SELECT_ALL);
                        dataBase.getPs().setBoolean(1, false);
                    ResultSet resultSet=dataBase.executeSelect();
                    while (resultSet.next()) {
                        
                        Classe data=new Classe( resultSet.getInt("id")
                                         ,resultSet.getString("libelle")
                                          ,resultSet.getString("niveau") 
                                          ,resultSet.getString("filiere"));

                        //     dataBase.initPreparedStatement(SQL_SELECT_PROFESSEUR_BY_CLASSE);
                        //     dataBase.getPs().setInt(1,resultSet.getInt("id"));
                        //     ResultSet resultSet2=dataBase.executeSelect();
                        //     ArrayList<Professeur>professeurs=new ArrayList<>();
                        //     while (resultSet2.next()) {
                        //        professeurs.add(professeurRepository.findById(resultSet2.getInt("id")));
                        //     }
                        //  data.setProfesseurs(professeurs);

                         datas.add(data);                       
                      }
                      resultSet.close();
                                          
                   
                   dataBase.closeConnexion();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            return datas;
    }

    @Override
    public Classe findById(int id) {
        try {
             dataBase.openConnexion();
                    dataBase.initPreparedStatement(SQL_SELECT_CLASSE_BY_ID);
                     dataBase.getPs().setBoolean(1,false);
                    dataBase.getPs().setInt(2,id);
                    ResultSet resultSet=dataBase.executeSelect();
                    if (resultSet.next()) {
                        
                        Classe classe=new Classe( resultSet.getInt("id")
                                         ,resultSet.getString("libelle")
                                          ,resultSet.getString("niveau") 
                                          ,resultSet.getString("filiere"));
                        return classe;

                    }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public Map<Professeur, ArrayList<Module>> getModulesAndProfesseurClasse(Classe classe) {
        Map<Professeur,ArrayList<Module>> professeurModules = new HashMap<>();

        // try {
        //     dataBase.openConnexion();
        //     dataBase.initPreparedStatement(SQL_SELECT_MODULE_CLASSE);
        //     dataBase.getPs().setInt(1, classe.getId());
        //     ResultSet resultSet= dataBase.executeSelect();
        //     while (resultSet.next()) {
        //         Professeur professeur=professeurRepository.findById(resultSet.getInt("idProf"));
        //         if(!professeurModules.keySet().contains(professeur)){
        //             professeurModules.put(professeur,new ArrayList<Module>());
        //         }
        //         Module module=moduleRepository.findById(resultSet.getInt("idModule"));
        //         professeurModules.get(professeur).add(module);
        //     }
        //     resultSet.close();
        //     dataBase.closeConnexion();

        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        return professeurModules;

    }

    @Override
    public boolean addModuleClasse(Classe classe, Professeur prof, Module module) {
        try {
            dataBase.openConnexion();
            //Verifions si il a déjà ajouter ce module a la classe     
            dataBase.initPreparedStatement(SQL_VERIFY_MODULE_EXIST);
            dataBase.getPs().setInt(1, classe.getId());
            dataBase.getPs().setInt(2, module.getId());
            ResultSet exist=dataBase.executeSelect();
            if(!exist.next()){
                dataBase.initPreparedStatement(SQL_INSERT_MODULE_CLASSE);
                dataBase.getPs().setInt(1, prof.getId());
                dataBase.getPs().setInt(2, classe.getId());
                dataBase.getPs().setInt(3, module.getId());
                
                dataBase.executeUpdate();
                return true;

            }
            exist.close();
            dataBase.closeConnexion();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public ArrayList<Module> findModuleByClasse(Classe classe) {
        ArrayList<Module> datas=new ArrayList<>();
                  try {
                      dataBase.openConnexion();
                        dataBase.initPreparedStatement(SQL_FIND_MODULE_BY_CLASSE);
                        dataBase.getPs().setInt(1, classe.getId());

                    ResultSet resultSet=dataBase.executeSelect();
                    while (resultSet.next()) {
                        
                        Module data=moduleRepository.findById(resultSet.getInt("idModule"));
                         datas.add(data);                       
                      }
                      resultSet.close();
                   dataBase.closeConnexion();
                } catch (SQLException e) {

                    e.printStackTrace();
                }
            return datas;
    }
    @Override
    public ArrayList<Classe> findClasseByModule(Module module) {
        ArrayList<Classe> datas=new ArrayList<>();
                  try {
                      dataBase.openConnexion();
                        dataBase.initPreparedStatement(SQL_FIND_CLASSE_BY_MODULE);
                        dataBase.getPs().setInt(1, module.getId());

                    ResultSet resultSet=dataBase.executeSelect();
                    while (resultSet.next()) {
                        
                        Classe data=findById(resultSet.getInt("idClasse"));
                         datas.add(data);                       
                      }
                      resultSet.close();
                   dataBase.closeConnexion();
                } catch (SQLException e) {

                    e.printStackTrace();
                }
            return datas;
    }
}
