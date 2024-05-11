package Email_verify;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Test;

import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.Code;
import com.mailosaur.models.Message;
import com.mailosaur.models.MessageSearchParams;
import com.mailosaur.models.SearchCriteria;



public class Emailverify{
 
@Test
public void email_verify() throws IOException, MailosaurException {
String apiKey = "dLDarfa54fDArRgUZ7LaIO7oG6ErBX3e";
   String serverId = "mjt1np6y";
   String serverDomain = "mjt1np6y.mailosaur.net";
//System.setProperty("webdriver.chrome.driver", null);
   MailosaurClient mailosaur = new MailosaurClient(apiKey);

   MessageSearchParams params = new MessageSearchParams();
   params.withServer(serverId);

   SearchCriteria criteria = new SearchCriteria();
   criteria.withSentTo("away-useful@" + serverDomain);

   Message message = mailosaur.messages().get(params, criteria);
   System.out.println(message.subject());
   System.out.println(message.to().get(0).email());
   System.out.println(message.from().get(0).email());
   
   
   //body
   System.out.println("-----Body-----");
   System.out.println(message.text().body());
   assertTrue(message.text().body().contains("Qa engineer. Apply below."));
   System.out.println(message.attachments().get(0).fileName());
   
   //how many codes
   System.out.println(message.html().codes().size());//2
   Code firstCode=message.html().codes().get(0);
   
   System.out.println(firstCode.value());//"323232"
  String abccode= firstCode.value();
   System.out.println(abccode);
   assertNotNull(message);
   //To verify the Email Subject of the email
   assertEquals("QA Engineer", message.subject());
   
}


}
