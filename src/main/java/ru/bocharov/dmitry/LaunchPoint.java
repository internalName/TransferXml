package ru.bocharov.dmitry;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import ru.bocharov.dmitry.editor.ManageFolder;
import ru.bocharov.dmitry.listeners.AbstractListener;
import ru.bocharov.dmitry.listeners.CarrierListener;
import ru.bocharov.dmitry.sheduler.Sheduler;

public class LaunchPoint implements Job {
    static ManageFolder manageFolder;

    public static void main(String[] args) {

        manageFolder=new ManageFolder();
        AbstractListener listener=new CarrierListener(manageFolder);

        Sheduler sheduler=new Sheduler();

    }


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            manageFolder.notifyListeners();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
