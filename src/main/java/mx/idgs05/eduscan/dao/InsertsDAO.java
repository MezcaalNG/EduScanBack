package mx.idgs05.eduscan.dao;

import mx.idgs05.eduscan.bean.GenericResponseBean;
import mx.idgs05.eduscan.bean.RegistroUsuarioRequestBean;
import mx.idgs05.eduscan.util.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertsDAO extends ScanEduDAO{

    public GenericResponseBean insertarUsuario(RegistroUsuarioRequestBean requestBean){
        GenericResponseBean response = new GenericResponseBean();
        Utils utils = new Utils();
        try {
            startConnection();
            int sqlResponse = statement.executeUpdate(
                    "INSERT INTO USUARIOS " +
                            "VALUES ('"+requestBean.getMatricula()+"', '"+requestBean.getEmail()+"', '"+
                            requestBean.getHash()+"', '"+requestBean.getSalt()+"', "+requestBean.getAcceso()+", '"+utils.fecha()+"')");

            closeConnection();
            response.setSqlResponseCode(sqlResponse);
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }
        return response;
    }
}
