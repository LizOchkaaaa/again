package com.example.c.FX;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public interface CloseAction {
    EventHandler<WindowEvent> close();
}
