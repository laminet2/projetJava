package gestion.classe.repository.implementation;

import java.util.ArrayList;

import com.database.services.Database;

import gestion.classe.entities.Salle;
import gestion.classe.repository.SalleRepository;

public class SalleRepositoryImple implements SalleRepository{
    private Database database;
    public SalleRepositoryImple(Database database){
        this.database=database;
    }
    
    @Override
    public ArrayList<Salle> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Salle findById(int arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public int insertOrUpdate(Salle arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }
    @Override
    public boolean archiver(Salle arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'archiver'");
    }
    
}
