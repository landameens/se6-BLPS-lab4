package itmo.jobs;

import itmo.services.ImportStatService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@DisallowConcurrentExecution
public class ImportStatJob implements Job {
    @Autowired
    ImportStatService importStatService;

    @Override
    public void execute(JobExecutionContext context) {
        importStatService.userStats();
    }
}
