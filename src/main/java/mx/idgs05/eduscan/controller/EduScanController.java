package mx.idgs05.eduscan.controller;


import mx.idgs05.eduscan.bean.*;
import mx.idgs05.eduscan.bo.ConsultasBO;
import mx.idgs05.eduscan.bo.InsertsBO;
import mx.idgs05.eduscan.dao.InsertsDAO;
import mx.idgs05.eduscan.util.RSA;
import mx.idgs05.eduscan.util.Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

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
    String ConsultarAlumno() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, IOException, InvalidKeySpecException, NoSuchProviderException {
        RSA rsa = new RSA();
        rsa.genKeyPair(512);

        String str = "Este es el texto a cifrar";

        System.out.println("\nTexto a cifrar:");
        System.out.println(str);
        String file_private = "d:/tmp/rsa.pri";
        String file_public = "d:/tmp/rsa.pub";

        //Las guardamos asi podemos usarlas despues
        //a lo largo del tiempo
        rsa.saveToDiskPrivateKey("D:/tmp/rsa.pri");
        rsa.saveToDiskPublicKey("D:/tmp/rsa.pub");

        String secure = rsa.Encrypt(str);

        System.out.println("\nCifrado:");
        System.out.println(secure);



        //A modo de ejemplo creamos otra clase rsa
        RSA rsa2 = new RSA();

        //A diferencia de la anterior aca no creamos
        //un nuevo par de claves, sino que cargamos
        //el juego de claves que habiamos guadado
        rsa2.openFromDiskPrivateKey("d:/tmp/rsa.pri");
        rsa2.openFromDiskPublicKey("d:/tmp/rsa.pub");

        //Le pasamos el texto cifrado (secure) y nos
        //es devuelto el texto ya descifrado (unsecure)
        String unsecure = rsa2.Decrypt(secure);

        //Imprimimos
        System.out.println("\nDescifrado:");
        System.out.println(unsecure);
        return "";
    }

}
