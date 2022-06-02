package itmo.delegates;

import itmo.model.Film;
import itmo.services.FilmService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.util.List;

@Named("getFilmsDelegate")
@RequiredArgsConstructor
public class GetFilmsDelegate implements JavaDelegate {
    private final FilmService filmService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<Film> films = filmService.findByNameContains((String) execution.getVariable("film_name"));
        execution.setVariable("films", films.toString());
        execution.setVariable("is_exist", films.size() > 0);
    }
}