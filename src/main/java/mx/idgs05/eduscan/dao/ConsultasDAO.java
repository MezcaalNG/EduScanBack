package mx.idgs05.eduscan.dao;
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
}
