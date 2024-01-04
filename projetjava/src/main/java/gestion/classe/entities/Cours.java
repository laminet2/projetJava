package gestion.classe.entities;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private boolean isArchived=false;
    private Module module;
    private String lieux;
    private String lien;
    private Salle salle;
    private ArrayList<Classe>classes;
    public Cours(int id,String libelle){
        this.id=id;
        this.libelle=libelle;
    }
    public Cours(int id,String libelle,LocalDate data,LocalTime heureDebut,LocalTime heureFin,Module module,String lieux)
    {
        this.id=id;
        this.libelle=libelle;
        this.date=data;
        this.heureDebut=heureDebut;
        this.heureFin=heureFin;
        this.module=module;
        this.lieux=lieux;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
        result = prime * result + ((professeur == null) ? 0 : professeur.hashCode());
        result = prime * result + ((heureDebut == null) ? 0 : heureDebut.hashCode());
        result = prime * result + ((heureFin == null) ? 0 : heureFin.hashCode());
        result = prime * result + (isArchived ? 1231 : 1237);
        result = prime * result + ((module == null) ? 0 : module.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cours other = (Cours) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (libelle == null) {
            if (other.libelle != null)
                return false;
        } else if (!libelle.equals(other.libelle))
            return false;
        if (professeur == null) {
            if (other.professeur != null)
                return false;
        } else if (!professeur.equals(other.professeur))
            return false;
        if (heureDebut == null) {
            if (other.heureDebut != null)
                return false;
        } else if (!heureDebut.equals(other.heureDebut))
            return false;
        if (heureFin == null) {
            if (other.heureFin != null)
                return false;
        } else if (!heureFin.equals(other.heureFin))
            return false;
        if (isArchived != other.isArchived)
            return false;
        if (module == null) {
            if (other.module != null)
                return false;
        } else if (!module.equals(other.module))
            return false;
        return true;
    }
   
    public Map<String, String> toTable(){
        Map<String, String> informations = new HashMap<>();
        informations.put("Date", date.toString());
        informations.put("Module", module.getLibelle());
        informations.put("Professeur", professeur.getNomComplet());
        informations.put("Lieux", lieux);
        if(lieux.equals("EN PRESENTIEL")){
            informations.put("Salle", salle.getLibelle());
        }else{
            informations.put("Lien", lien);
        }
        informations.put("HeureDebut", heureDebut.toString());
        informations.put("HeureFin", heureFin.toString());
        return informations;  
    }
    
    @Override
    public String toString(){
        String tete= "[ Date: "+date+ " Module:"+module.getLibelle()+" Heure Debut:"+this.heureDebut+" HeureFin:"+heureFin+" Lieux:"+this.lieux ;
        if(lieux.equals("EN PRESENTIEL")){
                    return tete+" "+this.salle +"]";

        }else{
                    return tete+" "+this.lien +"]";
        }
    }
}
