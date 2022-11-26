package mx.idgs05.eduscan.bo;

import mx.idgs05.eduscan.bean.*;
import mx.idgs05.eduscan.dao.InsertsDAO;
import mx.idgs05.eduscan.util.EncryptDecryptRSAUtil;

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
        RegistroAlumnoRequeatBean cypheredRequest = cypherRequest(request);
        response=dao.registrarAlumno(cypheredRequest);
        if(response.getReturnCode()==1){
            response.setMatricula(request.getMatricula());
        }
        return response;
    }

    public void updateAcceso(LoginRequestBean request){
        dao = new InsertsDAO();
        dao.updateUltimoAcceso(request);
    }

    public RegistroAlumnoRequeatBean cypherRequest(RegistroAlumnoRequeatBean request){
        RegistroAlumnoRequeatBean cypheredRequest = new RegistroAlumnoRequeatBean();
        EncryptDecryptRSAUtil cryptoRSAUtil = new EncryptDecryptRSAUtil();
        cypheredRequest.setMatricula(request.getMatricula());
        cypheredRequest.setEstatus(request.getEstatus());
        cypheredRequest.setTiposangre(request.getTiposangre());
        try {
            cypheredRequest.setNombre(cryptoRSAUtil.encode(request.getNombre()));
            cypheredRequest.setApellidop(cryptoRSAUtil.encode(request.getApellidop()));
            cypheredRequest.setApellidom(cryptoRSAUtil.encode(request.getApellidom()));
            cypheredRequest.setDireccion(cryptoRSAUtil.encode(request.getDireccion()));
            cypheredRequest.setNacimiento(cryptoRSAUtil.encode(request.getNacimiento()));
            cypheredRequest.setEmailins(cryptoRSAUtil.encode(request.getEmailins()));
            cypheredRequest.setEmailper(cryptoRSAUtil.encode(request.getEmailper()));
            cypheredRequest.setCelular(cryptoRSAUtil.encode(request.getCelular()));
            cypheredRequest.setNss(cryptoRSAUtil.encode(request.getNss()));
            cypheredRequest.setGrupo(cryptoRSAUtil.encode(request.getGrupo()));
            cypheredRequest.setCarrera(cryptoRSAUtil.encode(request.getCarrera()));
            cypheredRequest.setCuatrimestre(cryptoRSAUtil.encode(request.getCuatrimestre()));
        } catch (Exception e) {
            System.out.println(e);
        }
        return cypheredRequest;
    }

}
