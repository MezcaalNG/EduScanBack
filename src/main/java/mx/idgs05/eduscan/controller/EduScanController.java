package mx.idgs05.eduscan.controller;


import mx.idgs05.eduscan.bean.*;
import mx.idgs05.eduscan.bo.impl.AlumnoBO;
import mx.idgs05.eduscan.bo.impl.UsuarioBO;
import mx.idgs05.eduscan.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EduScanController {
    private static Logger log = LoggerFactory.getLogger(EduScanController.class);
Utils utils = new Utils();
AlumnoBO alumnoBO = null;
UsuarioBO usuarioBO=null;

    @PostMapping("/login")
    String Login(@RequestBody GenericRequestBean loginRequestBean) {
        System.out.println("entra login");
        GenericResponseBean responseObject = null;
        usuarioBO = new UsuarioBO();
        responseObject = usuarioBO.login(loginRequestBean);
        return utils.jsonFormatter(responseObject);
    }

    @PostMapping("/addUser")
    String RegistrarUsuario(@RequestBody RegistroUsuarioRequestBean requestBean) {
        GenericResponseBean response = new GenericResponseBean();
        usuarioBO = new UsuarioBO();
        response = usuarioBO.registrarUsuario(requestBean);
        return utils.jsonFormatter(response);
    }

    @PostMapping("/addAlumno")
    String RegistrarAlumno(@RequestBody AlumnoBean requestBean) {
        GenericResponseBean response = new GenericResponseBean();
        alumnoBO = new AlumnoBO();
        response = alumnoBO.registrarAlumno(requestBean);
        return utils.jsonFormatter(response);
    }

    @PostMapping("/consultarAlumno")
    String ConsultarAlumno(@RequestBody GenericRequestBean request){
        AlumnoBean response = null;
        alumnoBO = new AlumnoBO();
        response= alumnoBO.consultarAlumno(request);
        return utils.jsonFormatter(response);
    }

}
