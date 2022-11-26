package mx.idgs05.eduscan.controller;


import mx.idgs05.eduscan.bean.*;
import mx.idgs05.eduscan.bo.ConsultasBO;
import mx.idgs05.eduscan.bo.InsertsBO;
import mx.idgs05.eduscan.util.EncryptDecryptRSAUtil;
import mx.idgs05.eduscan.util.KeyPairRSAGeneratorUtil;
import mx.idgs05.eduscan.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
public class EduScanController {
    private static Logger log = LoggerFactory.getLogger(EduScanController.class);
Utils utils = new Utils();
InsertsBO insertsBO = null;
ConsultasBO consultasBO=null;

    @PostMapping("/login")
    String Login(@RequestBody LoginRequestBean loginRequestBean) {
        System.out.println("entra login");
        LoginResponseBean responseObject = null;
        consultasBO = new ConsultasBO();
        insertsBO = new InsertsBO();
        responseObject = consultasBO.login(loginRequestBean);
        insertsBO.updateAcceso(loginRequestBean);
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

    @PostMapping("/consultarAlumno")
    String ConsultarAlumno(@RequestBody ConsultaAlumnoRequestBean request){
        ConsultaAlumnoResponseBean response = null;
        consultasBO = new ConsultasBO();
        response= consultasBO.consultaAlumno(request);
        return utils.jsonFormatter(response);
    }

    @PostMapping("/generateRSA")
    String ConsultarAlumno()  {
        KeyPairRSAGeneratorUtil KPRSAGU = new KeyPairRSAGeneratorUtil();
        try {
            //KPRSAGU.createKeys();
            KPRSAGU.loadPrivateKey();
            KPRSAGU.loadPublicKey();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (InvalidKeySpecException e) {
            System.out.println(e);
        }
        return "";
    }

    @PostMapping("/testRSA")
    String test(){
        EncryptDecryptRSAUtil cryptoRSAUtil = new EncryptDecryptRSAUtil();

        try {
            String textToEncrypt = "moises.navarro1408@gmail.com.mx";
            log.debug("Encrypting text {} ", textToEncrypt);
            String encoded = cryptoRSAUtil.encode(textToEncrypt);
            log.debug("Encrypted result:");
            log.debug(encoded);
            System.out.println(encoded);
            log.debug("Decrypting result:");
            String decode = null;
            decode = cryptoRSAUtil.decode(encoded);
            log.debug(decode);
            System.out.println(decode);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

}
