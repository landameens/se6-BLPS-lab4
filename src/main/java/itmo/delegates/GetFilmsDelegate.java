package itmo.delegates;

import itmo.services.FilmService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named("getFilmsDelegate")
@RequiredArgsConstructor
public class GetFilmsDelegate implements JavaDelegate {
    private final FilmService filmService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        execution.setVariable("films", filmService.getAllFilms());
    }
}