package mx.idgs05.eduscan.dao;

import mx.idgs05.eduscan.bean.AlumnoBean;
import mx.idgs05.eduscan.bean.GenericRequestBean;
import mx.idgs05.eduscan.bean.GenericResponseBean;

public interface IAlumnoDAO {

    GenericResponseBean registrarAlumno(AlumnoBean alumnoBean);
    AlumnoBean consultarAlumno(GenericRequestBean requestBean);

}
