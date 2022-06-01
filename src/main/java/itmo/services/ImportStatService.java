package itmo.services;

import itmo.model.ImportStat;
import itmo.repositories.ImportStatRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ImportStatService {
    private final ImportStatRepository importStatRepository;
    private final JavaMailSender javaMailSender;

    @Autowired
    public ImportStatService(ImportStatRepository importStatRepository, JavaMailSender javaMailSender) {
        this.importStatRepository = importStatRepository;
        this.javaMailSender = javaMailSender;
    }

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

    public void userStats() {
        Set<String> mails = importStatRepository.getMails();
        for (String mail : mails) {
            String text = generateEmailText(mail);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mail);
            message.setSubject("Your stats for the week");
            message.setText(text);
            javaMailSender.send(message);
            importStatRepository.deleteByOwnerMail(mail);
        }
    }
}
