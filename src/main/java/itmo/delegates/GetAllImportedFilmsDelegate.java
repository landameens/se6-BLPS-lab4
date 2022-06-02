package itmo.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named("getAllImportedFilms")
public class GetAllImportedFilmsDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        //todo: "playlist_id_from" => "imported_movies"
    }
}
