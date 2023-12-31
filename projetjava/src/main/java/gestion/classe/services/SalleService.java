package gestion.classe.services;

import java.util.ArrayList;

import gestion.classe.entities.Salle;

public interface SalleService {
    ArrayList<Salle> listerSalle();
    boolean ajouterSalle(Salle salle);
    boolean modifierSalle(Salle salle);
    boolean archiverSalle(Salle salle);
}
