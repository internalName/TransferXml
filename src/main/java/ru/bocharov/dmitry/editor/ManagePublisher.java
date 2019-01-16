package ru.bocharov.dmitry.editor;

import ru.bocharov.dmitry.listeners.AbstractListener;

public interface ManagePublisher {
        void subscribe(AbstractListener listeneer);
        void unsubscribe(AbstractListener listeneer);
        void notifyListeners() throws Exception;
}
