package gestion.classe.services.implementation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import gestion.classe.entities.Salle;
import gestion.classe.repository.SalleRepository;
import gestion.classe.services.SalleService;

public class SalleServiceImple implements SalleService{
    private SalleRepository salleRepository;
    
    public SalleServiceImple(SalleRepository salleRepository){
        this.salleRepository=salleRepository;
    }

    @Override
    public ArrayList<Salle> listerSalle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listerSalle'");
    }

    @Override
    public int ajouterSalle(Salle salle) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ajouterSalle'");
    }

    @Override
    public boolean modifierSalle(Salle salle) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifierSalle'");
    }

    @Override
    public boolean archiverSalle(Salle salle) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'archiverSalle'");
    }

    @Override
    public ArrayList<Salle> salleDispo(LocalDate date, LocalTime heureDebut, LocalTime heureFin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salleDispo'");
    }
}
