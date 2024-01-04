package gestion.classe.repository;

import java.util.ArrayList;

import com.database.services.Repository;

import gestion.classe.entities.Classe;
import gestion.classe.entities.Cours;
import gestion.classe.entities.Professeur;

public interface CoursRepository extends Repository<Cours>{
    ArrayList<Cours> findCoursByProfesseur(Professeur professeur);
    ArrayList<Cours> findCoursByClasse(Classe classe);
}
