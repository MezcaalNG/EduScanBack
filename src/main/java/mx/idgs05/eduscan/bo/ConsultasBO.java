package mx.idgs05.eduscan.bo;

import mx.idgs05.eduscan.bean.ConsultaAlumnoRequestBean;
import mx.idgs05.eduscan.bean.ConsultaAlumnoResponseBean;
import mx.idgs05.eduscan.bean.LoginRequestBean;
import mx.idgs05.eduscan.bean.LoginResponseBean;
import mx.idgs05.eduscan.dao.ConsultasDAO;

public class ConsultasBO {
    private ConsultasDAO dao = null;
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
        return response;
    }
}
