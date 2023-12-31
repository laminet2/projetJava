package gestion.classe.services.implementation;

import gestion.classe.repository.SalleRepository;

public class SalleServiceImple {
    private SalleRepository salleRepository;
    
    public SalleServiceImple(SalleRepository salleRepository){
        this.salleRepository=salleRepository;
    }
}
