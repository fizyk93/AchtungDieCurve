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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Piotr
 */
public class AchtungDieCurve extends GameEngine
{

    final static int WIDTH = 800;
    final static int HEIGHT = 600;

    
    
    private Group heads;
    private Group bodies;
    private boolean makeBreak;
    private int breakCounter;
    
    private int collisionCounter;
    

    
    public AchtungDieCurve(int fps, String title)
    {
        super(fps, title);
        makeBreak = false;
        breakCounter = 50;
        collisionCounter = 0;
    }


    @Override
    public void initialize(Stage primaryStage)
    {
        primaryStage.setTitle(getWindowTitle());
        
        setHeads(new Group());
        setBodies(new Group());
        setSceneNodes(new Group(getBodies(), getHeads()));
        setGameScene(new Scene(getSceneNodes(), WIDTH, HEIGHT));
        getGameScene().setFill(Color.BLACK);
        primaryStage.setScene(getGameScene());
        
        generateCurves();
        
        for(Sprite s : getSpriteManager().getAllSprites())
        {
            getHeads().getChildren().add(s.node);
        }
        
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
                            c.setTurnLeft(true);
                        } 
                        else if(t.getCode() == KeyCode.RIGHT) 
                        {
                            c.setTurnRight(true);
                        }                       
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
                            c.setTurnLeft(false);
                        } 
                        else if(t.getCode() == KeyCode.RIGHT) 
                        {
                            c.setTurnRight(false);
                        }
                      
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
            Color tmp;
            if(i == 0) tmp = Color.BLUE;
            else tmp = Color.RED;
            Curve c = new Curve(rnd.nextInt((int)gameScene.getWidth()-100)+50, rnd.nextInt((int)gameScene.getHeight()-100)+50, tmp);
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
            
                       
            if(c.isMakeBreak())
            {
                if(c.getBreakCounter() > 0)
                {
                    c.decrementBreakCounter();
                }
                else
                {
                    c.setMakeBreak(false);                  
                    c.setBreakCounter(0); 
                }
            }
            
            if(!c.isMakeBreak())
            {
                Node tmp = copyNode(c);
                getBodies().getChildren().add(tmp);
                SpriteManager.addCollisionNodes(tmp);
                c.incrementBreakCounter();
            }
            
            c.update();
            
            if((c.getBreakCounter() % (100+c.getDrawCounter()) == 0) && !(c.isMakeBreak()))
            {              
                c.setBreakCounter(20); 
                c.setMakeBreak(true);      
                c.setDrawCounter();
            }
            
            
            
        }   
    }
    
    
    
    @Override
    protected boolean handleCollision(Sprite sprite, Node node)
    {
        if(sprite.collide(node))
        {
            System.out.println((collisionCounter++) + "Kolizja!" );
            return true;
        }
        
        return false;
    }
    
    public Group getHeads()
    {
        return heads;
    }

    public void setHeads(Group heads)
    {
        this.heads = heads;
    }

    public Group getBodies()
    {
        return bodies;
    }

    public void setBodies(Group bodies)
    {
        this.bodies = bodies;
    }
    
}
