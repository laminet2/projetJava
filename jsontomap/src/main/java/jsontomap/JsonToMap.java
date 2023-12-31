package jsontomap;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JsonToMap {
    
    private String fileDirectory ;

    public JsonToMap(String fileDirectory){
        this.fileDirectory=fileDirectory;
    }

    public  Map<String,String> convert() {

        try {
            // Création de l'objet ObjectMapper de Jackson
            ObjectMapper objectMapper = new ObjectMapper();

            // Lecture du fichier JSON et conversion en Map
            File jsonFile = new File(this.fileDirectory);

            Map<String,String> dataMap = objectMapper.readValue(jsonFile, Map.class);
            return dataMap;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,String> map=new HashMap<>();
        return map;
    }
}


