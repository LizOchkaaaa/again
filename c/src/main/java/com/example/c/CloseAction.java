package com.example.c;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public interface CloseAction {
    EventHandler<WindowEvent> close();
}
