package itmo.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named("addFilmDelegate")
@RequiredArgsConstructor
public class AddFilmDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        //ToDo: добавить фильм ("film_id") в плейлист ("playlist_id")
        // => "is_success" удачность удаления хз, может как-то по-другому
    }
}
