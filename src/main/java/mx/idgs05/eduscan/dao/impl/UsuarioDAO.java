package mx.idgs05.eduscan.dao.impl;

import mx.idgs05.eduscan.bean.GenericRequestBean;
import mx.idgs05.eduscan.bean.GenericResponseBean;
import mx.idgs05.eduscan.bean.RegistroUsuarioRequestBean;
import mx.idgs05.eduscan.dao.IUsuarioDAO;
import mx.idgs05.eduscan.util.Utils;

import java.sql.SQLException;
import java.util.Objects;

public class UsuarioDAO extends ScanEduDAO implements IUsuarioDAO {
    @Override
    public GenericResponseBean insertarUsuario(RegistroUsuarioRequestBean requestBean) {
        GenericResponseBean response = new GenericResponseBean();
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

    @Override
    public GenericResponseBean login(GenericRequestBean requestBean) {
        GenericResponseBean response = new GenericResponseBean();
        String query = "SELECT MATRICULA, NIVEL, ACCESO FROM USUARIOS WHERE EMAIL='requestMail' AND " +
                "PSWD=(SELECT SHA1(CONCAT((SELECT SALT FROM USUARIOS WHERE EMAIL='requestMail'), 'requestPass')))";
        query = query.replace("requestMail", requestBean.getEmail()).replace("requestPass", requestBean.getPassword());
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

    @Override
    public void updateUltimoAcceso(GenericRequestBean requestBean) {
        Utils utils = new Utils();
        String query="UPDATE USUARIOS SET ACCESO='REQFECHA' WHERE EMAIL ='REQEMAIL'";
        query=query.replace("REQFECHA", utils.fecha()).replace("REQEMAIL",requestBean.getEmail());
        try{
            startConnection();
            int sqlResponse = statement.executeUpdate(query);
            closeConnection();
        } catch(SQLException e){
            System.out.println(e);
        }
    }
}
