/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package achtungdiecurve;

import java.util.Random;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Piotr
 */
public class Curve extends Sprite
{
    private KeyCode rightKeyCode, leftKeyCode;
    
    private static Random  rnd = new Random();
    private double angle;
    
    private int length;

    private boolean turnLeft, turnRight;
    
    private Color color;
   
    private int drawCounter, breakCounter;
    private boolean makeBreak;
    
      
    public Curve(int x, int y, Color color, KeyCode l, KeyCode r)
    {
        rightKeyCode = r;
        leftKeyCode = l;
        
        Circle point = new Circle(0, 0, 2);
        point.setTranslateX(x);
        point.setTranslateY(y);
        point.setCache(true);
        point.setFill(Color.YELLOW);
        
        node = point;
        
        this.color = color;
        angle = rnd.nextInt(360);
        length = 0;
        turnLeft = false;
        turnRight = false;
        makeBreak = false;
        setBreakCounter(0);
        setDrawCounter();
        
    }
    
    public static Circle copyNode(Curve c)
    {
        Circle toCopy = c.getAsCircle();
        Circle tmp = new Circle(toCopy.getCenterX(), toCopy.getCenterY(), toCopy.getRadius(), c.color);
        tmp.setTranslateX(toCopy.getTranslateX());
        tmp.setTranslateY(toCopy.getTranslateY());
        
        return tmp;
    }
    
    @Override
    public void update()
    {
        if(turnLeft) angle -= 2;
        else if(turnRight) angle += 2;
        
        length++;
        
        node.setTranslateX(node.getTranslateX()+Math.cos(angle/180*Math.PI));
        node.setTranslateY(node.getTranslateY()+Math.sin(angle/180*Math.PI));
    }
   
    
    @Override
    public boolean collide(Node node)
    {
        if(node instanceof Circle)
        {   
            Circle otherPoint = (Circle)node;
            Circle thisPoint = getAsCircle();
            
            
            double dx = otherPoint.getTranslateX() - thisPoint.getTranslateX();
            double dy = otherPoint.getTranslateY() - thisPoint.getTranslateY();
            double distance = Math.sqrt( dx * dx + dy * dy );
            double minDist  = otherPoint.getRadius() + thisPoint.getRadius();
            
            return (distance < minDist);
        }
        else return false;
    }

    public KeyCode getRightKeyCode()
    {
        return rightKeyCode;
    }

    public KeyCode getLeftKeyCode()
    {
        return leftKeyCode;
    }
  
    public Circle getAsCircle()
    {
        return (Circle)node;
    }
    
    public double getAngle()
    {
        return angle;
    }

    public void setAngle(double newAngle)
    {
        if(newAngle > 360) angle -= 360;
        else if(newAngle < 0) angle += 360;
        else angle = newAngle;
    }
    
    public boolean getTurnRight()
    {
        return turnRight;
    }

    public void setTurnRight(boolean turnLeft)
    {
        this.turnRight = turnLeft;
    }

    public boolean getTurnLeft()
    {
        return turnLeft;
    }

    public void setTurnLeft(boolean turnLeft)
    {
        this.turnLeft = turnLeft;
    }
    
    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }
    
    public int getDrawCounter()
    {
        return drawCounter;
    }

    public final void setDrawCounter()
    {      
        this.drawCounter = (int)rnd.nextInt(100);
    }

    public int getBreakCounter()
    {
        return breakCounter;
    }

    public final void setBreakCounter(int n)
    {
        this.breakCounter = n;
    }
    
    public void decrementBreakCounter()
    {
        breakCounter--;
    }
    
    public void incrementBreakCounter()
    {
        breakCounter++;
    }
    
    public boolean isMakeBreak()
    {
        return makeBreak;
    }

    public void setMakeBreak(boolean makeBreak)
    {
        this.makeBreak = makeBreak;
    }
}
