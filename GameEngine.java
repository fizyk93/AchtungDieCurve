/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package achtungdiecurve;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Piotr
 */
public abstract class GameEngine
{
    private Scene gameScene;
    private Group sceneNodes;
    private static Timeline gameLoop;
    
    private final int fps;
    
    private final String windowTitle;
    
    private final SpriteManager spriteManager;
    
    public GameEngine(final int fps, final String title)
    {
        this.fps = fps;
        windowTitle = title;
        buildAndSetGameLoop();
        spriteManager = new SpriteManager();
    }

    private void buildAndSetGameLoop()
    {
        final Duration oneFrameAmt = Duration.millis(1000/fps);
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt, new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent t)
            {
                updateSprites();
                checkCollisions();
            }

           
        });
        
        gameLoop = (TimelineBuilder.create()
                .cycleCount(Animation.INDEFINITE)
                .keyFrames(oneFrame)
                .build()
                );
    }
    
    public abstract void initialize(Stage primaryStage);
    
    public void beginGameLoop()
    {
        getGameLoop().play();
    }
    
    protected void updateSprites()
    {
        for (Sprite sprite : spriteManager.getAllSprites())
        {
            handleUpdate(sprite);
        }
    }

    /**
     *
     * @param sprite
     */
    protected abstract void handleUpdate(Sprite sprite);
    
    protected void checkCollisions()
    {
        for(Sprite spriteA : SpriteManager.getAllSprites())
            for(Sprite spriteB : SpriteManager.getAllSprites())
                handleCollision(spriteA, spriteB);
                    
    }
    
    protected boolean handleCollision(Sprite spriteA, Sprite spriteB)
    {
        return false;
    }
    
    protected int getFPS()
    {
        return fps;
    }

    public Scene getGameScene()
    {
        return gameScene;
    }

    public Group getSceneNodes()
    {
        return sceneNodes;
    }

    protected static Timeline getGameLoop()
    {
        return gameLoop;
    }

    public String getWindowTitle()
    {
        return windowTitle;
    }

    protected SpriteManager getSpriteManager()
    {
        return spriteManager;
    }

    protected void setGameScene(Scene gameScene)
    {
        this.gameScene = gameScene;
    }

    protected void setSceneNodes(Group sceneNodes)
    {
        this.sceneNodes = sceneNodes;
    }

    protected static void setGameLoop(Timeline gameLoop)
    {
        GameEngine.gameLoop = gameLoop;
    }
    
    
    
    
}
