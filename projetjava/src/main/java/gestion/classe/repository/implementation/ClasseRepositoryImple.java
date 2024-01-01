package gestion.classe.repository.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.database.services.Database;

import gestion.classe.entities.Classe;
import gestion.classe.repository.ClasseRepository;

public class ClasseRepositoryImple implements ClasseRepository{
    private final String SQL_INSERT="INSERT INTO `classe` (`libelle`,`filiere`,`niveau`,`isArchived`) VALUES (?,?,?,?)";
    private final String SQL_SELECT_ALL="SELECT * FROM classe where isArchived=false";
    private final String SQL_UPDATE="UPDATE `classe` SET `libelle` = ?, `filiere` = ? ,`niveau`= ? WHERE id = ? ";
    private final String SQL_ARCHIVER="UPDATE `classe` SET `isArchived`= ? WHERE id = ?";
    private Database dataBase;
    public ClasseRepositoryImple(Database database){
        this.dataBase=database;
    }
    @Override
        public int insertOrUpdate(Classe data) {

            int lastInsertId=0;
            boolean insert= data.getId()==0 ? true:false;
            
                try {
                    dataBase.openConnexion();
                    dataBase.initPreparedStatement(insert?SQL_INSERT:SQL_UPDATE);
                    dataBase.getPs().setString(1,data.getLibelle());
                    dataBase.getPs().setString(2,data.getFiliere());
                    dataBase.getPs().setString(3,data.getNiveau());

                    if (insert) {
                     dataBase.getPs().setBoolean(4, data.getIsArchived());
                    }else{
                     dataBase.getPs().setInt(4, data.getId());
                     lastInsertId=1;
                    }

                    dataBase.executeUpdate();
                    if(insert){
                         ResultSet rs=  dataBase.getPs().getGeneratedKeys();

                        if(rs.next()){
                            lastInsertId=rs.getInt(1) ; 
                        }
                    }
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
                    ResultSet resultSet=dataBase.executeSelect();
                    while (resultSet.next()) {
                        
                        Classe data=new Classe( resultSet.getInt("id")
                                         ,resultSet.getString("libelle")
                                          ,resultSet.getString("niveau") 
                                          ,resultSet.getString("filiere"));
                         datas.add(data);
                           
                      }
                      
                   resultSet.close();
                   dataBase.closeConnexion();
                } catch (SQLException e) {
                    System.out.println("Erreur execution de Requete");
                }
            
            return datas;
    }

    @Override
    public Classe findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    
}
