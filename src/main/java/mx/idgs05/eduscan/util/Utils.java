package mx.idgs05.eduscan.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public String fecha(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public String jsonFormatter(Object object){
        ObjectMapper mapper = new ObjectMapper();
        //Converting the Object to JSONString
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonString;
    }

    public String generateBloodType(int tipo){
        String type="";
        switch (tipo){
            case 0:type="A+";

            case 1:type="A-";

            case 2:type="B+";

            case 3:type="B-";

            case 4:type="AB+";

            case 5:type="AB-";

            case 6:type="O+";

            case 7:type="O-";
        }
        return type;
    }

    public String generateStatus(int tipo){
        String status="";
        switch (tipo){
            case 0:status="Activo";

            case 1:status="Baja Temporal";

            case 2:status="Egresado";

            case 3:status="En tramite";

        }
        return status;
    }
}
