package gestion.classe.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import gestion.classe.entities.Salle;

public interface SalleService {
    ArrayList<Salle> listerSalle();
    int ajouterSalle(Salle salle);
    boolean modifierSalle(Salle salle);
    boolean archiverSalle(Salle salle);
    ArrayList<Salle> salleDispo(LocalDate date,LocalTime heureDebut,LocalTime heureFin);
}
