package gestion.classe.repository.implementation;

import java.util.ArrayList;

import com.database.services.Database;

import gestion.classe.entities.Module;
import gestion.classe.repository.ModuleRepository;

public class ModuleRepositoryImple implements ModuleRepository{

    private Database database;
    public ModuleRepositoryImple(Database database){
        this.database=database;
    }
    @Override
    public ArrayList<Module> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Module findById(int arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public int insertOrUpdate(Module arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertOrUpdate'");
    }
    @Override
    public boolean archiver(Module arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'archiver'");
    }
    
}
