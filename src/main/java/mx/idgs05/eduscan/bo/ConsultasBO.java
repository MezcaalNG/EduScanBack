package mx.idgs05.eduscan.bo;

import mx.idgs05.eduscan.bean.*;
import mx.idgs05.eduscan.dao.ConsultasDAO;
import mx.idgs05.eduscan.util.EncryptDecryptRSAUtil;
import mx.idgs05.eduscan.util.Utils;

public class ConsultasBO {
    private ConsultasDAO dao = null;
    private Utils utils = new Utils();
    public LoginResponseBean login(LoginRequestBean request){
        LoginResponseBean response=null;
        dao = new ConsultasDAO();
        response= dao.login(request);
        if(response.getMatricula()!=null){
            response.setMensaje("OK");
        }else{
            response.setMensaje("NOK");
            response.setMatricula("");
            response.setAcceso("");
        }
        return response;
    }

    public ConsultaAlumnoResponseBean consultaAlumno(ConsultaAlumnoRequestBean request){
        ConsultaAlumnoResponseBean response = null;
        dao= new ConsultasDAO();
        response=dao.consultarAlumno(request);
        ConsultaAlumnoResponseBean unCypheredResponse = new ConsultaAlumnoResponseBean();
        unCypheredResponse = unCypherResponse(response);
        unCypheredResponse.setTiposangre(utils.generateBloodType(response.getIdtiposangre()));
        unCypheredResponse.setEstatus(utils.generateStatus(response.getIdestatus()));
        return unCypheredResponse;
    }
    public ConsultaAlumnoResponseBean unCypherResponse(ConsultaAlumnoResponseBean response){
        ConsultaAlumnoResponseBean unCypheredResponse = new ConsultaAlumnoResponseBean();
        EncryptDecryptRSAUtil cryptoRSAUtil = new EncryptDecryptRSAUtil();
        unCypheredResponse.setMatricula(response.getMatricula());
        unCypheredResponse.setIdestatus(response.getIdestatus());
        unCypheredResponse.setIdtiposangre(response.getIdtiposangre());
        try {
            unCypheredResponse.setNombre(cryptoRSAUtil.decode(response.getNombre()));
            unCypheredResponse.setApellidop(cryptoRSAUtil.decode(response.getApellidop()));
            unCypheredResponse.setApellidom(cryptoRSAUtil.decode(response.getApellidom()));
            unCypheredResponse.setDireccion(cryptoRSAUtil.decode(response.getDireccion()));
            unCypheredResponse.setNacimiento(cryptoRSAUtil.decode(response.getNacimiento()));
            unCypheredResponse.setEmailins(cryptoRSAUtil.decode(response.getEmailins()));
            unCypheredResponse.setEmailper(cryptoRSAUtil.decode(response.getEmailper()));
            unCypheredResponse.setCelular(cryptoRSAUtil.decode(response.getCelular()));
            unCypheredResponse.setNss(cryptoRSAUtil.decode(response.getNss()));
            unCypheredResponse.setGrupo(cryptoRSAUtil.decode(response.getGrupo()));
            unCypheredResponse.setCarrera(cryptoRSAUtil.decode(response.getCarrera()));
            unCypheredResponse.setCuatrimestre(cryptoRSAUtil.decode(response.getCuatrimestre()));
        } catch (Exception e) {
            System.out.println(e);
        }
        return unCypheredResponse;
    }

}
