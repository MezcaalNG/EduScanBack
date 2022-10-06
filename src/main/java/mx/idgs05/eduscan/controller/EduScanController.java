package mx.idgs05.eduscan.controller;


import mx.idgs05.eduscan.bean.LoginRequestBean;
import mx.idgs05.eduscan.bean.LoginResponseBean;
import mx.idgs05.eduscan.bean.RegistroUsuarioRequestBean;
import mx.idgs05.eduscan.bo.ConsultasBO;
import mx.idgs05.eduscan.bo.InsertsBO;
import mx.idgs05.eduscan.util.Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EduScanController {
Utils utils = new Utils();

    @PostMapping("/login")
    String Login(@RequestBody LoginRequestBean loginRequestBean) {
        LoginResponseBean responseObject = null;
        ConsultasBO bo = new ConsultasBO();
        responseObject = bo.login(loginRequestBean);

        return utils.jsonFormatter(responseObject);
    }

    @PostMapping("/addUser")
    String RegistrarUsuario(@RequestBody RegistroUsuarioRequestBean requestBean) {
        InsertsBO bo = new InsertsBO();
        bo.insertarUsuario(requestBean);
        return "";
    }

}
