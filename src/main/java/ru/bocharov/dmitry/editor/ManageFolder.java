package ru.bocharov.dmitry.editor;

import ru.bocharov.dmitry.listeners.AbstractListener;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class ManageFolder implements ManagePublisher {
    private String folderIn = Paths.get("").toAbsolutePath().toString() + "\\in";

    public String getFolderIn() {
        return folderIn;
    }

    public List<AbstractListener> listeners;

    public ManageFolder() {
        listeners = new LinkedList<>();

        checkFolders(Paths.get(folderIn));
    }

    private void checkFolders(Path folder) {
        File file = new File(String.valueOf(folder));

        if (!file.exists()) {
            try {
                Files.createDirectory(folder);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void subscribe(AbstractListener listener) {

        listeners.add(listener);
    }

    public void unsubscribe(AbstractListener listener) {
        listeners.remove(listener);
    }

    public void notifyListeners() throws Exception {
        for (AbstractListener listener : listeners)
            listener.update(Paths.get(folderIn));
    }

}
