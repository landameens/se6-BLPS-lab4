package itmo.delegates;

import itmo.services.ImportStatService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named("generateEmailTextDelegate")
@RequiredArgsConstructor
public class GenerateEmailTextDelegate implements JavaDelegate {
    private final ImportStatService importStatService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String mail = (String) execution.getVariable("mail");
        String text = importStatService.generateEmailText(mail);
        execution.setVariable("text", text);
    }
}
