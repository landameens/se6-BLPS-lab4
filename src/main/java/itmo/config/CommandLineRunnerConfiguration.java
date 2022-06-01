package itmo.config;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class CommandLineRunnerConfiguration {
    @Bean
    public CommandLineRunner produceCommandLineRunner(
            @Autowired Scheduler quartzScheduler,
            @Autowired JobDetail importStatJobDetail
    ) {
        return args -> scheduleJob(
                quartzScheduler,
                importStatJobDetail
        );
    }

    private static void scheduleJob(
            Scheduler scheduler,
            JobDetail jobDetail
    ) throws SchedulerException {
        final String jobName = UUID.randomUUID().toString();
        final Trigger jobTrigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobName)
                .withSchedule(
                        CronScheduleBuilder.weeklyOnDayAndHourAndMinute(1, 10, 0)
                )
                .build();

        scheduler.scheduleJob(jobTrigger);
    }
}
