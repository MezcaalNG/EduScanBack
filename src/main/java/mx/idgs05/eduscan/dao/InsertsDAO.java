package mx.idgs05.eduscan.dao;

import mx.idgs05.eduscan.bean.*;
import mx.idgs05.eduscan.util.Utils;

import java.sql.SQLException;
import java.util.Objects;

public class InsertsDAO extends ScanEduDAO{

    public RegistroUsuarioResponseBean insertarUsuario(RegistroUsuarioRequestBean requestBean){
        RegistroUsuarioResponseBean response = new RegistroUsuarioResponseBean();
        Utils utils = new Utils();
        try {
            startConnection();
            int sqlResponse = statement.executeUpdate(
                    "INSERT INTO USUARIOS " +
                            "VALUES ('"+requestBean.getMatricula()+"', '"+requestBean.getEmail()+"', '"+
                            requestBean.getHash()+"', '"+requestBean.getSalt()+"', "+requestBean.getAcceso()+", '"+utils.fecha()+"')");


            closeConnection();
            response.setReturnCode(sqlResponse);
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
            response.setSqlCode(Objects.toString(e.getErrorCode()));
            response.setSqlMessage(e.getMessage());
        }
        return response;
    }

    public RegistroAlumnoResponseBean registrarAlumno(RegistroAlumnoRequeatBean request){
        RegistroAlumnoResponseBean response = new RegistroAlumnoResponseBean();
        String query="INSERT INTO ALUMNOS VALUES('REQMATRICULA','REQNOMBRE','REQAPELLIDOP','REQAPELLIDOM',REQESTATUS," +
                "'REQDIRECCION','REQNACIMIENTO','REQEMAILINS','REQEMAILPERS','REQCELULAR','REQNSS',REQTIPOSANGRE," +
                "'REQGRUPO','REQCARRERA','REQCUATRIMESTRE');";
        query=query.replace("REQMATRICULA",request.getMatricula()).replace("REQNOMBRE",request.getNombre())
                .replace("REQAPELLIDOP",request.getApellidop()).replace("REQAPELLIDOM",request.getApellidom())
                .replace("REQESTATUS",Objects.toString(request.getEstatus())).replace("REQDIRECCION",request.getDireccion())
                .replace("REQNACIMIENTO",request.getNacimiento()).replace("REQEMAILINS",request.getEmailins())
                .replace("REQEMAILPERS",request.getEmailper()).replace("REQCELULAR",request.getCelular())
                .replace("REQNSS",request.getNss()).replace("REQTIPOSANGRE",Objects.toString(request.getTiposangre()))
                .replace("REQGRUPO",request.getGrupo()).replace("REQCARRERA",request.getCarrera())
                .replace("REQCUATRIMESTRE",request.getCuatrimestre());
        try{
            startConnection();
            int sqlResponse = statement.executeUpdate(query);
            closeConnection();
            response.setReturnCode(sqlResponse);
        } catch(SQLException e){
            System.out.println(e);
            response.setSqlCode(Objects.toString(e.getErrorCode()));
            response.setSqlMessage(e.getMessage());
        }

        return response;
    }

    public void updateUltimoAcceso(LoginRequestBean request){
        Utils utils = new Utils();
        String query="UPDATE USUARIOS SET ACCESO='REQFECHA' WHERE EMAIL ='REQEMAIL'";
        query=query.replace("REQFECHA", utils.fecha()).replace("REQEMAIL",request.getEmail());
        try{
            startConnection();
            int sqlResponse = statement.executeUpdate(query);
            closeConnection();
        } catch(SQLException e){
            System.out.println(e);
        }
    }
}
