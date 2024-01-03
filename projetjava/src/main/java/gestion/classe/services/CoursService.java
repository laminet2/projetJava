package gestion.classe.services;

import java.util.List;

import gestion.classe.entities.Cours;
import gestion.classe.entities.Lieux;
import gestion.classe.entities.Niveau;

public interface CoursService {
    boolean plannifierCours(Cours cours);
    List<Lieux> listerLieux();
    
} 