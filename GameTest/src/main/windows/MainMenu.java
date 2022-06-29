package main.windows;

import main.frames.BaseWindow;

import javax.swing.*;

public class MainMenu {

    private static BaseWindow mainWindow;

    public MainMenu() {

        mainWindow = new BaseWindow();
        mainWindow.setTitle("Game Widget");
        mainWindow.setSize(800, 600);
        mainWindow.setVisible( true );

    }

}
