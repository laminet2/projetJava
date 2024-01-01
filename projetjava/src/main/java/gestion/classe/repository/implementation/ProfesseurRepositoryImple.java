package gestion.classe.repository.implementation;

import java.util.ArrayList;

import com.database.services.Database;

import gestion.classe.entities.Professeur;
import gestion.classe.repository.ProfesseurRepository;

public class ProfesseurRepositoryImple implements ProfesseurRepository{
    private Database database;
    public ProfesseurRepositoryImple(Database database){
        this.database=database;
    }
    @Override
    public ArrayList<Professeur> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Professeur findById(int arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

  
    @Override
    public int insertOrUpdate(Professeur arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertOrUpdate'");
    }
    @Override
    public boolean archiver(Professeur arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'archiver'");
    }
    
}
