package itmo.delegates;

import itmo.services.ImportStatService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named("deleteStatDelegate")
@RequiredArgsConstructor
public class DeleteStatDelegate implements JavaDelegate {
    private final ImportStatService importStatService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String mail = (String) execution.getVariable("mail");
//        importStatService.deleteStatByOwnerMail(mail);
    }
}
