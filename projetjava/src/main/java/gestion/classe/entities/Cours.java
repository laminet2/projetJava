package gestion.classe.entities;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cours {
    private int id;
    private LocalDate date;
    private String libelle;
    private Professeur professeur;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private boolean isArchived;
    private Module module;
    private ArrayList<Classe>classes;

}
