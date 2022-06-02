package itmo.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named("getPlaylistsByName")
public class GetPlaylistsByNameDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        //todo: "playlist_name" => {"playlists" - с таким именем, "is_exist" -  есть ли что-либо}
    }
}
