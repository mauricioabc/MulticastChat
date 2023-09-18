import org.junit.Test;
import com.chat.Manager.Security;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import com.chat.Manager.IntegrationService;

/**
 *
 * @author mauricio.rodrigues
 */
public class Test_Integration {
    
    public Test_Integration() {
    }
    
    @Test
    public void getServerPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException, Exception {
        IntegrationService integration = new IntegrationService("https://nameserver-api-prd.azurewebsites.net");
        String teste = integration.getServerPublicKey();
        
        System.out.println("Teste");
    }
}
