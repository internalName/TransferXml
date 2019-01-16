package ru.bocharov.dmitry.listeners;

import java.nio.file.Path;

public interface EventListener {
    void update(Path folder) throws Exception;
}
