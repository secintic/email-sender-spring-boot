package in.mail;

import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmailController {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @RequestMapping(value = "/rest/mail/send", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> sendMail(@RequestBody Message message) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(message.getTo());
            mailMessage.setSubject(message.getSubject());
            mailMessage.setText(message.getBody());
            mailMessage.setCc(message.getCc());
            javaMailSender.send(mailMessage);
            return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Error>(HttpStatus.BAD_REQUEST);
        }
    }
}
