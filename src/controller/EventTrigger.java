package controller;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EventTrigger extends Timer {
    public EventTrigger(int time, ActionListener actionListener) {
        super(time, actionListener);
        setRepeats(false);
        start();
    }
}
