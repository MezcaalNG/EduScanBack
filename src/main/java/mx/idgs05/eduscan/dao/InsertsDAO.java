package mx.idgs05.eduscan.dao;

import mx.idgs05.eduscan.bean.RegistroUsuarioResponseBean;
import mx.idgs05.eduscan.bean.RegistroUsuarioRequestBean;
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
}
