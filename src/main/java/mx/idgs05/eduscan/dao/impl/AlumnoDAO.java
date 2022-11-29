package mx.idgs05.eduscan.dao.impl;

import mx.idgs05.eduscan.bean.AlumnoBean;
import mx.idgs05.eduscan.bean.GenericRequestBean;
import mx.idgs05.eduscan.bean.GenericResponseBean;
import mx.idgs05.eduscan.dao.IAlumnoDAO;

import java.sql.SQLException;
import java.util.Objects;

public class AlumnoDAO extends ScanEduDAO implements IAlumnoDAO {
    @Override
    public GenericResponseBean registrarAlumno(AlumnoBean alumnoBean) {
        GenericResponseBean response = new GenericResponseBean();
        String query="INSERT INTO ALUMNOS VALUES('REQMATRICULA','REQNOMBRE','REQAPELLIDOP','REQAPELLIDOM',REQESTATUS," +
                "'REQDIRECCION','REQNACIMIENTO','REQEMAILINS','REQEMAILPERS','REQCELULAR','REQNSS',REQTIPOSANGRE," +
                "'REQGRUPO','REQCARRERA','REQCUATRIMESTRE');";
        query=query.replace("REQMATRICULA",alumnoBean.getAcademico().getMatricula()).replace("REQNOMBRE",alumnoBean.getAlumno().getNombre())
                .replace("REQAPELLIDOP",alumnoBean.getAlumno().getApellidop()).replace("REQAPELLIDOM",alumnoBean.getAlumno().getApellidom())
                .replace("REQESTATUS", Objects.toString(alumnoBean.getAcademico().getIdestatus())).replace("REQDIRECCION",alumnoBean.getAlumno().getDireccion())
                .replace("REQNACIMIENTO",alumnoBean.getAlumno().getNacimiento()).replace("REQEMAILINS",alumnoBean.getContacto().getEmailins())
                .replace("REQEMAILPERS",alumnoBean.getContacto().getEmailper()).replace("REQCELULAR",alumnoBean.getContacto().getCelular())
                .replace("REQNSS",alumnoBean.getAlumno().getNss()).replace("REQTIPOSANGRE",Objects.toString(alumnoBean.getAlumno().getIdtiposangre()))
                .replace("REQGRUPO",alumnoBean.getAcademico().getGrupo()).replace("REQCARRERA",alumnoBean.getAcademico().getCarrera())
                .replace("REQCUATRIMESTRE",alumnoBean.getAcademico().getCuatrimestre());
        try{
            startConnection();
            int sqlResponse = statement.executeUpdate(query);
            closeConnection();
            response.setReturnCode(sqlResponse);
        } catch(SQLException e){
            System.out.println(e);
            response.setSqlCode(Objects.toString(e.getErrorCode()));
            response.setSqlMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public AlumnoBean consultarAlumno(GenericRequestBean requestBean) {
        AlumnoBean response= new AlumnoBean();
        String query = "SELECT MATRICULA, NOMBRE, APELLIDOP, APELLIDOM, ESTATUS, DIRECCION, NACIMIENTO, EMAILINS, EMAILPER, CELULAR, NSS, TIPOSANGRE, GRUPO, CARRERA, CUATRIMESTRE FROM ALUMNOS WHERE MATRICULA ='requestMatricula'";
        query = query.replace("requestMatricula", requestBean.getMatricula());
        try {
            startConnection();
            resultSet = statement.executeQuery(
                    query);
            while (resultSet.next()) {
                response.getAcademico().setMatricula(resultSet.getString("MATRICULA"));
                response.getAlumno().setNombre(resultSet.getString("NOMBRE"));
                response.getAlumno().setApellidop(resultSet.getString("APELLIDOP"));
                response.getAlumno().setApellidom(resultSet.getString("APELLIDOM"));
                response.getAcademico().setIdestatus(Integer.parseInt(resultSet.getString("ESTATUS")));
                response.getAlumno().setDireccion(resultSet.getString("DIRECCION"));
                response.getAlumno().setNacimiento(resultSet.getString("NACIMIENTO"));
                response.getContacto().setEmailins(resultSet.getString("EMAILINS"));
                response.getContacto().setEmailper(resultSet.getString("EMAILPER"));
                response.getContacto().setCelular(resultSet.getString("CELULAR"));
                response.getAlumno().setNss(resultSet.getString("NSS"));
                response.getAlumno().setIdtiposangre(Integer.parseInt(resultSet.getString("TIPOSANGRE")));
                response.getAcademico().setGrupo(resultSet.getString("GRUPO"));
                response.getAcademico().setCarrera(resultSet.getString("CARRERA"));
                response.getAcademico().setCuatrimestre(resultSet.getString("CUATRIMESTRE"));
            }
            closeConnection();

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }
        return response;
    }
}
