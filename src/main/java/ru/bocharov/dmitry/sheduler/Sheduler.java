package ru.bocharov.dmitry.sheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import ru.bocharov.dmitry.LaunchPoint;

public class Sheduler {
    public Sheduler() {
        shedule();
    }

    private void shedule() {
        try {
            JobDetail job = JobBuilder.newJob(LaunchPoint.class).withIdentity("folder").build();

            Trigger trigger = TriggerBuilder.newTrigger().
                    withSchedule(SimpleScheduleBuilder.simpleSchedule().
                            withIntervalInSeconds(3).withRepeatCount(10)).build();

            SchedulerFactory schFactory = new StdSchedulerFactory();
            Scheduler scheduler = schFactory.getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);

        } catch (SchedulerException e) {

            e.printStackTrace();
        }
    }
}
