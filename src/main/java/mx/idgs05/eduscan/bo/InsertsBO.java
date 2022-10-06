package mx.idgs05.eduscan.bo;

import mx.idgs05.eduscan.bean.GenericResponseBean;
import mx.idgs05.eduscan.bean.RegistroUsuarioRequestBean;
import mx.idgs05.eduscan.dao.InsertsDAO;

import java.util.Random;

public class InsertsBO {

    public GenericResponseBean insertarUsuario(RegistroUsuarioRequestBean requestBean){
        InsertsDAO dao = new InsertsDAO();
        GenericResponseBean response = null;
        Random rnd = new Random();
        int n = 10000000 + rnd.nextInt(100000000);
        requestBean.setSalt(""+n);
        requestBean.setHash(dao.hashQuery(requestBean.getPswd(),requestBean.getSalt()));
        response = dao.insertarUsuario(requestBean);
        return response;
    }
}
