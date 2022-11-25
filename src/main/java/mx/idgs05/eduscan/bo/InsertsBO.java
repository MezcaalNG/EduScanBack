package mx.idgs05.eduscan.bo;

import mx.idgs05.eduscan.bean.*;
import mx.idgs05.eduscan.dao.InsertsDAO;

import java.util.Random;

public class InsertsBO {
    private InsertsDAO dao = null;
    public RegistroUsuarioResponseBean insertarUsuario(RegistroUsuarioRequestBean requestBean){
        dao = new InsertsDAO();
        RegistroUsuarioResponseBean response = null;
        Random rnd = new Random();
        int n = 10000000 + rnd.nextInt(99999999);
        requestBean.setSalt(""+n);
        requestBean.setHash(dao.hashQuery(requestBean.getPswd(),requestBean.getSalt()));
        response = dao.insertarUsuario(requestBean);
        System.out.println(response.getReturnCode());
        return response;
    }

    public RegistroAlumnoResponseBean registrarAlumno(RegistroAlumnoRequeatBean request){
        dao = new InsertsDAO();
        RegistroAlumnoResponseBean response = new RegistroAlumnoResponseBean();
        response=dao.registrarAlumno(request);
        if(response.getReturnCode()==1){
            response.setMatricula(request.getMatricula());
        }
        return response;
    }

    public void updateAcceso(LoginRequestBean request){
        dao = new InsertsDAO();
        dao.updateUltimoAcceso(request);
    }
}
