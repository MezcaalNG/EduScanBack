package mx.idgs05.eduscan.controller;


import mx.idgs05.eduscan.bean.*;
import mx.idgs05.eduscan.bo.ConsultasBO;
import mx.idgs05.eduscan.bo.InsertsBO;
import mx.idgs05.eduscan.util.Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EduScanController {
Utils utils = new Utils();
InsertsBO insertsBO = null;
ConsultasBO consultasBO=null;

    @PostMapping("/login")
    String Login(@RequestBody LoginRequestBean loginRequestBean) {
        System.out.println("entra login");
        LoginResponseBean responseObject = null;
        consultasBO = new ConsultasBO();
        responseObject = consultasBO.login(loginRequestBean);
        System.out.println(utils.jsonFormatter(responseObject));
        return utils.jsonFormatter(responseObject);
    }

    @PostMapping("/addUser")
    String RegistrarUsuario(@RequestBody RegistroUsuarioRequestBean requestBean) {
        RegistroUsuarioResponseBean response = new RegistroUsuarioResponseBean();
        insertsBO = new InsertsBO();
        response = insertsBO.insertarUsuario(requestBean);
        return utils.jsonFormatter(response);
    }

    @PostMapping("/addAlumno")
    String RegistrarAlumno(@RequestBody RegistroAlumnoRequeatBean requestBean) {
        RegistroAlumnoResponseBean response = new RegistroAlumnoResponseBean();
        insertsBO = new InsertsBO();
        response = insertsBO.registrarAlumno(requestBean);
        return utils.jsonFormatter(response);
    }

}
