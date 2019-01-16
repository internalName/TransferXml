package ru.bocharov.dmitry;



import org.apache.log4j.PropertyConfigurator;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import ru.bocharov.dmitry.editor.ManageFolder;
import ru.bocharov.dmitry.listeners.AbstractListener;
import ru.bocharov.dmitry.listeners.CarrierListener;
import ru.bocharov.dmitry.sheduler.Sheduler;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LaunchPoint implements Job {
    static ManageFolder manageFolder;

    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        try {
            InputStream inStream = LaunchPoint.class.getResourceAsStream("/log4j.properties");
            props.load(inStream);
        } catch (Exception e){

        }
        PropertyConfigurator.configure(props);
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
