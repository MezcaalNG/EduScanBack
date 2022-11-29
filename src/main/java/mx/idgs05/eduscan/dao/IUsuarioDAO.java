package mx.idgs05.eduscan.dao;

import mx.idgs05.eduscan.bean.GenericRequestBean;
import mx.idgs05.eduscan.bean.GenericResponseBean;
import mx.idgs05.eduscan.bean.RegistroUsuarioRequestBean;

public interface IUsuarioDAO {

    GenericResponseBean insertarUsuario(RegistroUsuarioRequestBean requestBean);
    GenericResponseBean login(GenericRequestBean requestBean);
    void updateUltimoAcceso(GenericRequestBean requestBean);

}
