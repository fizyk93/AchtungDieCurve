/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package achtungdiecurve;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Piotr
 */
public class Curve extends Sprite
{
    private double angle;
    public boolean turnRight, turnLeft;
    
    public Curve(int x, int y)
    {
        Circle point = new Circle(x, y, 2);
        point.setCache(true);
        point.setFill(Color.RED);
        
        node = point;
        
        angle = 0;
        turnLeft = false;
        turnRight = false;
    }
    
    public static Circle copyNode(Circle c)
    {
        Circle tmp = new Circle(c.getCenterX(), c.getCenterY(), c.getRadius());
        tmp.setTranslateX(c.getTranslateX());
        tmp.setTranslateY(c.getTranslateY());
        
        return tmp;
    }
    
    @Override
    public void update()
    {
        if(turnLeft) angle -= 2;
        else if(turnRight) angle += 2;
        node.setTranslateX(node.getTranslateX()+Math.cos(angle/180*Math.PI));
        node.setTranslateY(node.getTranslateY()+Math.sin(angle/180*Math.PI));
    }
   
    
    @Override
    public boolean collide(Sprite other)
    {
        if(other instanceof Curve)
        {
            Circle otherPoint = ((Curve)other).getAsCircle();
            Circle thisPoint = getAsCircle();
            
            double dx = otherPoint.getTranslateX() - thisPoint.getTranslateX();
            double dy = otherPoint.getTranslateY() - thisPoint.getTranslateY();
            double distance = Math.sqrt( dx * dx + dy * dy );
            double minDist  = otherPoint.getRadius() + thisPoint.getRadius();
 
            return (distance < minDist);
        }
        else return false;
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

}
