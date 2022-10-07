package mx.idgs05.eduscan.bo;

import mx.idgs05.eduscan.bean.LoginRequestBean;
import mx.idgs05.eduscan.bean.LoginResponseBean;
import mx.idgs05.eduscan.dao.ConsultasDAO;

public class ConsultasBO {
    public LoginResponseBean login(LoginRequestBean request){
        LoginResponseBean response=null;
        ConsultasDAO dao = new ConsultasDAO();
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
}
