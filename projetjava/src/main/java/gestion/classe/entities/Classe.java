package gestion.classe.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Classe {
    private int id;
    private String libelle;
    private boolean isArchived;
    private ArrayList<Professeur> professeurs=new ArrayList<>();
    private ArrayList<Cours> cours=new ArrayList<>();
    private Map<Professeur,ArrayList<Module>> professeurModules = new HashMap<>();

}
