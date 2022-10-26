package mx.idgs05.eduscan.dao;
import mx.idgs05.eduscan.bean.ConsultaAlumnoRequestBean;
import mx.idgs05.eduscan.bean.ConsultaAlumnoResponseBean;
import mx.idgs05.eduscan.bean.LoginRequestBean;
import mx.idgs05.eduscan.bean.LoginResponseBean;

import java.sql.*;
public class ConsultasDAO extends ScanEduDAO{

    public LoginResponseBean login(LoginRequestBean request){
        LoginResponseBean response = new LoginResponseBean();
        String query = "SELECT MATRICULA, NIVEL, ACCESO FROM USUARIOS WHERE EMAIL='requestMail' AND " +
                "PSWD=(SELECT SHA1(CONCAT((SELECT SALT FROM USUARIOS WHERE EMAIL='requestMail'), 'requestPass')))";
        query = query.replace("requestMail", request.getEmail()).replace("requestPass", request.getPassword());
        try {
            startConnection();
            resultSet = statement.executeQuery(
                    query);
            while (resultSet.next()) {
                response.setMatricula(resultSet.getString("MATRICULA"));
                response.setAcceso(resultSet.getString("ACCESO"));
                response.setNivel(resultSet.getInt("NIVEL"));
            }
            closeConnection();

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }
        return response;
    }

    public ConsultaAlumnoResponseBean consultarAlumno(ConsultaAlumnoRequestBean request){
        ConsultaAlumnoResponseBean response= new ConsultaAlumnoResponseBean();
        String query = "SELECT MATRICULA, NOMBRE, APELLIDOP, APELLIDOM, ESTATUS, DIRECCION, NACIMIENTO, EMAILINS, EMAILPER, CELULAR, NSS, TIPOSANGRE, GRUPO, CARRERA, CUATRIMESTRE FROM ALUMNOS WHERE MATRICULA ='requestMatricula'";
        query = query.replace("requestMatricula", request.getMatricula());
        try {
            startConnection();
            resultSet = statement.executeQuery(
                    query);
            while (resultSet.next()) {
                response.setMatricula(resultSet.getString("MATRICULA"));
                response.setNombre(resultSet.getString("NOMBRE"));
                response.setApellidop(resultSet.getString("APELLIDOP"));
                response.setApellidom(resultSet.getString("APELLIDOM"));
                response.setEstatus(Integer.parseInt(resultSet.getString("ESTATUS")));
                response.setDireccion(resultSet.getString("DIRECCION"));
                response.setNacimiento(resultSet.getString("NACIMIENTO"));
                response.setEmailins(resultSet.getString("EMAILINS"));
                response.setEmailper(resultSet.getString("EMAILPER"));
                response.setCelular(resultSet.getString("CELULAR"));
                response.setNss(resultSet.getString("NSS"));
                response.setTiposangre(Integer.parseInt(resultSet.getString("TIPOSANGRE")));
                response.setGrupo(resultSet.getString("GRUPO"));
                response.setCarrera(resultSet.getString("CARRERA"));
                response.setCuatrimestre(resultSet.getString("CUATRIMESTRE"));
            }
            closeConnection();

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }
        return response;
    }
}
