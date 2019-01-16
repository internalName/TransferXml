package ru.bocharov.dmitry.listeners;

import ru.bocharov.dmitry.editor.ManageFolder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.log4j.Logger;

public abstract class AbstractListener implements EventListener {
     final static Logger logger = Logger.getLogger(AbstractListener.class);

    String folderOut = Paths.get("").toAbsolutePath().toString() + "\\out";

    public AbstractListener(ManageFolder manageFolder) {
        manageFolder.subscribe(this);
        checkFolders(Paths.get(folderOut));
    }

    private void checkFolders(Path folder) {
        File file = new File(String.valueOf(folder));


            if (!file.exists()) {
                try {

                    Files.createDirectory(folder);
                    logger.info("Create folder " + new Date());
                } catch (Exception e) {
                    logger.error(e.getMessage());

                }
            }
        }


    public abstract void update(Path folder) throws Exception;
}
