/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package achtungdiecurve;

import static achtungdiecurve.Curve.copyNode;
import java.util.Random;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author Piotr
 */
public class AchtungDieCurve extends GameEngine
{

    final static int WIDTH = 800;
    final static int HEIGHT = 600;
    

    
    public AchtungDieCurve(int fps, String title)
    {
        super(fps, title);
//        turnRight = false;
//        turnLeft = false;
    }


    @Override
    public void initialize(Stage primaryStage)
    {
        primaryStage.setTitle(getWindowTitle());
        
        setSceneNodes(new Group());
        setGameScene(new Scene(getSceneNodes(), WIDTH, HEIGHT));
        primaryStage.setScene(getGameScene());
        
        generateCurves();
        
        beginGameLoop();
        
        EventHandler keyPressed = new EventHandler<KeyEvent>()
        {

            @Override
            public void handle(KeyEvent t)
            {
                for(Sprite s : getSpriteManager().getAllSprites())
                {
                    if(s instanceof Curve)
                    {
                        Curve c = (Curve)s;
                        if(t.getCode() == KeyCode.LEFT) 
                        {
                            c.turnLeft = true;
                        } 
                        else if(t.getCode() == KeyCode.RIGHT) 
                        {
                            c.turnRight = true;
                        }
                        
                        System.out.println("Angle = " + c.getAngle());
                    }
                    
                }              
            }
        };
        EventHandler keyReleased = new EventHandler<KeyEvent>()
        {

            @Override
            public void handle(KeyEvent t)
            {
                for(Sprite s : getSpriteManager().getAllSprites())
                {
                    if(s instanceof Curve)
                    {
                        Curve c = (Curve)s;
                        if(t.getCode() == KeyCode.LEFT) 
                        {
                            c.turnLeft = false;
                        } 
                        else if(t.getCode() == KeyCode.RIGHT) 
                        {
                            c.turnRight = false;
                        }
                        
                        System.out.println("Angle = " + c.getAngle());
                    }
                    
                }              
            }
        };
        
          
        
        primaryStage.getScene().setOnKeyPressed(keyPressed);
        primaryStage.getScene().setOnKeyReleased(keyReleased);

    }
    
    private void generateCurves()
    {
        Random rnd = new Random();
        Scene gameScene = getGameScene();
        
        for(int i = 0; i < 2; i++)
        {
            Curve c = new Curve(rnd.nextInt((int)gameScene.getWidth()-100)+50, rnd.nextInt((int)gameScene.getHeight()-100)+50);
            getSpriteManager().addSprites(c);
            
            getSceneNodes().getChildren().add(c.node);
        }
    }
    
    
    
    @Override
    protected void handleUpdate(Sprite sprite)
    {
        if(sprite instanceof Curve)
        {
            Curve c = (Curve)sprite;         
                                 
            Node node = copyNode((Circle)c.node);
            ((Circle)node).setFill(Color.BLUE);
            //System.out.println("X = " + ((Circle)c.node).getCenterX() + "Y = " + ((Circle)c.node).getCenterY());
            getSceneNodes().getChildren().add(node);
            
            c.update();       
        }
    }
    
}
