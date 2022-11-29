package mx.idgs05.eduscan.bo.impl;

import mx.idgs05.eduscan.bean.AlumnoBean;
import mx.idgs05.eduscan.bean.GenericRequestBean;
import mx.idgs05.eduscan.bean.GenericResponseBean;
import mx.idgs05.eduscan.bo.IAlumnoBO;
import mx.idgs05.eduscan.dao.impl.AlumnoDAO;
import mx.idgs05.eduscan.util.EncryptDecryptRSAUtil;
import mx.idgs05.eduscan.util.Utils;

public class AlumnoBO implements IAlumnoBO {
    private final Utils utils = new Utils();
    private AlumnoDAO dao;
    @Override
    public GenericResponseBean registrarAlumno(AlumnoBean alumnoBean) {
        dao = new AlumnoDAO();
        GenericResponseBean response = new GenericResponseBean();
        AlumnoBean cypheredRequest = cypherRequest(alumnoBean);
        response=dao.registrarAlumno(cypheredRequest);
        if(response.getReturnCode()==1){
            response.setMatricula(alumnoBean.getAcademico().getMatricula());
        }
        return response;
    }

    @Override
    public AlumnoBean consultarAlumno(GenericRequestBean requestBean) {
        AlumnoBean response = null;
        dao= new AlumnoDAO();
        response=dao.consultarAlumno(requestBean);
        AlumnoBean unCypheredResponse = new AlumnoBean();
        unCypheredResponse = unCypherResponse(response);
        unCypheredResponse.getAlumno().setTiposangre(utils.generateBloodType(response.getAlumno().getIdtiposangre()));
        unCypheredResponse.getAcademico().setEstatus(utils.generateStatus(response.getAcademico().getIdestatus()));
        return unCypheredResponse;
    }

    private AlumnoBean cypherRequest(AlumnoBean request){
        AlumnoBean cypheredRequest = new AlumnoBean();
        EncryptDecryptRSAUtil cryptoRSAUtil = new EncryptDecryptRSAUtil();
        cypheredRequest.getAcademico().setMatricula(request.getAcademico().getMatricula());
        cypheredRequest.getAcademico().setIdestatus(request.getAcademico().getIdestatus());
        cypheredRequest.getAlumno().setIdtiposangre(request.getAlumno().getIdtiposangre());
        try {
            cypheredRequest.getAlumno().setNombre(cryptoRSAUtil.encode(request.getAlumno().getNombre()));
            cypheredRequest.getAlumno().setApellidop(cryptoRSAUtil.encode(request.getAlumno().getApellidop()));
            cypheredRequest.getAlumno().setApellidom(cryptoRSAUtil.encode(request.getAlumno().getApellidom()));
            cypheredRequest.getAlumno().setDireccion(cryptoRSAUtil.encode(request.getAlumno().getDireccion()));
            cypheredRequest.getAlumno().setNacimiento(cryptoRSAUtil.encode(request.getAlumno().getNacimiento()));
            cypheredRequest.getContacto().setEmailins(cryptoRSAUtil.encode(request.getContacto().getEmailins()));
            cypheredRequest.getContacto().setEmailper(cryptoRSAUtil.encode(request.getContacto().getEmailper()));
            cypheredRequest.getContacto().setCelular(cryptoRSAUtil.encode(request.getContacto().getCelular()));
            cypheredRequest.getAlumno().setNss(cryptoRSAUtil.encode(request.getAlumno().getNss()));
            cypheredRequest.getAcademico().setGrupo(cryptoRSAUtil.encode(request.getAcademico().getGrupo()));
            cypheredRequest.getAcademico().setCarrera(cryptoRSAUtil.encode(request.getAcademico().getCarrera()));
            cypheredRequest.getAcademico().setCuatrimestre(cryptoRSAUtil.encode(request.getAcademico().getCuatrimestre()));
        } catch (Exception e) {
            System.out.println(e);
        }
        return cypheredRequest;
    }

    private AlumnoBean unCypherResponse(AlumnoBean response){
        AlumnoBean unCypheredResponse = new AlumnoBean();
        EncryptDecryptRSAUtil cryptoRSAUtil = new EncryptDecryptRSAUtil();
        unCypheredResponse.getAcademico().setMatricula(response.getAcademico().getMatricula());
        unCypheredResponse.getAcademico().setIdestatus(response.getAcademico().getIdestatus());
        unCypheredResponse.getAlumno().setIdtiposangre(response.getAlumno().getIdtiposangre());
        try {
            unCypheredResponse.getAlumno().setNombre(cryptoRSAUtil.decode(response.getAlumno().getNombre()));
            unCypheredResponse.getAlumno().setApellidop(cryptoRSAUtil.decode(response.getAlumno().getApellidop()));
            unCypheredResponse.getAlumno().setApellidom(cryptoRSAUtil.decode(response.getAlumno().getApellidom()));
            unCypheredResponse.getAlumno().setDireccion(cryptoRSAUtil.decode(response.getAlumno().getDireccion()));
            unCypheredResponse.getAlumno().setNacimiento(cryptoRSAUtil.decode(response.getAlumno().getNacimiento()));
            unCypheredResponse.getContacto().setEmailins(cryptoRSAUtil.decode(response.getContacto().getEmailins()));
            unCypheredResponse.getContacto().setEmailper(cryptoRSAUtil.decode(response.getContacto().getEmailper()));
            unCypheredResponse.getContacto().setCelular(cryptoRSAUtil.decode(response.getContacto().getCelular()));
            unCypheredResponse.getAlumno().setNss(cryptoRSAUtil.decode(response.getAlumno().getNss()));
            unCypheredResponse.getAcademico().setGrupo(cryptoRSAUtil.decode(response.getAcademico().getGrupo()));
            unCypheredResponse.getAcademico().setCarrera(cryptoRSAUtil.decode(response.getAcademico().getCarrera()));
            unCypheredResponse.getAcademico().setCuatrimestre(cryptoRSAUtil.decode(response.getAcademico().getCuatrimestre()));
        } catch (Exception e) {
            System.out.println(e);
        }
        return unCypheredResponse;
    }
}
