package itmo.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named("removeFilmDelegate")
@RequiredArgsConstructor
public class RemoveFilmDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        //ToDo: удалить фильм ("film_id") из плейлиста ("playlist_id")
        // => "is_success" удачность удаления хз, может как-то по-другому
    }
}
