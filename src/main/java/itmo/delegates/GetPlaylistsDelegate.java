package itmo.delegates;


import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;


@Named("getPlaylistsDelegate")
@RequiredArgsConstructor
public class GetPlaylistsDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        //ToDo: получить все плейлисты пользователя...
        // => "playlists"
    }
}
