package mx.idgs05.eduscan.bo.impl;

import mx.idgs05.eduscan.bean.GenericRequestBean;
import mx.idgs05.eduscan.bean.GenericResponseBean;
import mx.idgs05.eduscan.bean.RegistroUsuarioRequestBean;
import mx.idgs05.eduscan.bo.IUsuarioBO;
import mx.idgs05.eduscan.dao.impl.UsuarioDAO;
import mx.idgs05.eduscan.util.Utils;

import java.util.Random;

public class UsuarioBO implements IUsuarioBO {
    private final Utils utils= new Utils();
    private UsuarioDAO dao;
    @Override
    public GenericResponseBean registrarUsuario(RegistroUsuarioRequestBean requestBean) {
        dao = new UsuarioDAO();
        GenericResponseBean response = null;
        Random rnd = new Random();
        int n = 10000000 + rnd.nextInt(99999999);
        requestBean.setSalt(""+n);
        requestBean.setHash(dao.hashQuery(requestBean.getPswd(),requestBean.getSalt()));
        response = dao.insertarUsuario(requestBean);
        System.out.println(response.getReturnCode());
        return response;
    }

    @Override
    public GenericResponseBean login(GenericRequestBean requestBean) {
        GenericResponseBean response=null;
        dao = new UsuarioDAO();
        response= dao.login(requestBean);
        if(response.getMatricula()!=null){
            response.setMensaje("OK");
            dao.updateUltimoAcceso(requestBean);
        }else{
            response.setMensaje("NOK");
            response.setMatricula("");
            response.setAcceso("");
        }
        return response;
    }
}
