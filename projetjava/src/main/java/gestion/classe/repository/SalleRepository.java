package gestion.classe.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.database.services.Repository;

import gestion.classe.entities.Salle;

public interface SalleRepository extends Repository<Salle>{
   ArrayList<Salle> findSalleByDateAndTime(LocalDate date,LocalTime  heureDebut);
}
