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
        return salleRepository.findAll();
    }

    @Override
    public int ajouterSalle(Salle salle) {
        return salleRepository.insertOrUpdate(salle);
    }

    @Override
    public boolean modifierSalle(Salle salle) {
       return salleRepository.insertOrUpdate(salle)!=0;
    }

    @Override
    public boolean archiverSalle(Salle salle) {
        return salleRepository.archiver(salle);
    }

    @Override
    public ArrayList<Salle> salleDispo(LocalDate date, LocalTime heureDebut) {
        return salleRepository.findSalleByDateAndTime( date,  heureDebut);
    }
}
