/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package achtungdiecurve;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Piotr
 */
public class Game extends Application
{
    GameEngine game;
    
    @Override
    public void start(Stage primaryStage)
    {
        game = new AchtungDieCurve(100, "Achtung die Curve");
        
        game.initialize(primaryStage);
        
        game.beginGameLoop();
        
        primaryStage.show();
        
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
