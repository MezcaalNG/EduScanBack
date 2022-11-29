package mx.idgs05.eduscan.bo;

import mx.idgs05.eduscan.bean.AlumnoBean;
import mx.idgs05.eduscan.bean.GenericRequestBean;
import mx.idgs05.eduscan.bean.GenericResponseBean;

public interface IAlumnoBO {
    GenericResponseBean registrarAlumno(AlumnoBean alumnoBean);
    AlumnoBean consultarAlumno(GenericRequestBean requestBean);
}
