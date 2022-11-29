package mx.idgs05.eduscan.bo;

import mx.idgs05.eduscan.bean.GenericRequestBean;
import mx.idgs05.eduscan.bean.GenericResponseBean;
import mx.idgs05.eduscan.bean.RegistroUsuarioRequestBean;

public interface IUsuarioBO {
    GenericResponseBean registrarUsuario(RegistroUsuarioRequestBean requestBean);
    GenericResponseBean login(GenericRequestBean requestBean);
}
