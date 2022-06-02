package itmo.services;

import itmo.model.ImportStat;
import itmo.repositories.ImportStatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImportStatService {
    private final ImportStatRepository importStatRepository;

    @RabbitListener(queues = "queue")
    public void listen(ImportStat importStat) {
        importStatRepository.save(importStat);
    }

    public String generateEmailText(String ownerMail) {
        StringBuilder text = new StringBuilder();
        List<ImportStat> importStats = importStatRepository.findByOwnerMail(ownerMail);
        Map<String, List<ImportStat>> res = importStats.stream().collect(Collectors.groupingBy(ImportStat::getPlaylistName));
        for (Map.Entry<String, List<ImportStat>> item : res.entrySet()) {
            text.append("Playlist '").append(item.getKey()).append("' has been imported by users:\n\t");
            Iterator<ImportStat> iterator = item.getValue().iterator();
            while (iterator.hasNext()) {
                text.append(iterator.next().getImporterName());
                if (iterator.hasNext()) text.append(", ");
            }
            text.append("\n");
        }
        return text.toString();
    }

    public void deleteStatByOwnerMail(String mail) {
        importStatRepository.deleteByOwnerMail(mail);
    }

    public List<String> getMails() {
        return new ArrayList<>(importStatRepository.getMails());
    }
}
