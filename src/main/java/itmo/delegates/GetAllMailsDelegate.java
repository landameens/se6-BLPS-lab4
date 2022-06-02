package itmo.delegates;

import itmo.services.ImportStatService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named("getAllMailsDelegate")
@RequiredArgsConstructor
public class GetAllMailsDelegate implements JavaDelegate {
    private final ImportStatService importStatService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        execution.setVariable("mails", importStatService.getMails());
        System.out.println(importStatService.getMails());
    }
}
