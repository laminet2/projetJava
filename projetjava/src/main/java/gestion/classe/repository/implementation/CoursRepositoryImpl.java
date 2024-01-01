package gestion.classe.repository.implementation;

import java.util.ArrayList;

import com.database.services.Database;

import gestion.classe.entities.Cours;
import gestion.classe.repository.CoursRepository;

public class CoursRepositoryImpl implements CoursRepository{
    private Database database;
    public CoursRepositoryImpl(Database database){
        this.database=database;
    }
    
    @Override
    public ArrayList<Cours> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Cours findById(int arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public int insertOrUpdate(Cours arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertOrUpdate'");
    }

    @Override
    public boolean archiver(Cours arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'archiver'");
    }
    
}
