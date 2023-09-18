import org.junit.Test;
import com.chat.Manager.Security;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import com.chat.Manager.KeyToPEMConverter;

/**
 *
 * @author mauricio.rodrigues
 */
public class Test_Keys {
    
    public Test_Keys() {
    }
    
    @Test
    public void generateKeys() throws NoSuchAlgorithmException, InvalidKeySpecException, Exception {
    
        Security sec = Security.getInstance();
        sec.generateKeyPair();
        PrivateKey teste = sec.getPrivateKey();
        PublicKey teste2 = sec.getPublicKey();
        
        KeyToPEMConverter kpc = new KeyToPEMConverter();
        String teste3 = KeyToPEMConverter.privateKeyToPEM(teste);
        String teste4 = kpc.publicKeyToPEM(teste2);
        
        System.out.println("Teste");
    }
}
