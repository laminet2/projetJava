package gestion.classe.entities;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Professeur {
    private int id;
    private String nomComplet;
    private boolean isArchived;
    private ArrayList<Classe>classes;
    private ArrayList<Module>modules; 
    
}
