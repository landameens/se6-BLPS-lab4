package itmo.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.mail.javamail.JavaMailSender;

import javax.inject.Named;

@Named("sendMailDelegate")
@RequiredArgsConstructor
public class SendMailDelegate implements JavaDelegate {
    private final JavaMailSender javaMailSender;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String mail = (String) execution.getVariable("mail");
        String text = (String) execution.getVariable("text");

//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(mail);
//        message.setSubject("Your stats for the week");
//        message.setText(text);
//        javaMailSender.send(message);
        System.out.println(mail);
        System.out.println(text);
    }
}
