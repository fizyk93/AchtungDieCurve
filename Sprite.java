/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package achtungdiecurve;

import javafx.scene.Node;

/**
 *
 * @author Piotr
 */
public abstract class Sprite
{
    public Node node;
    public double vX = 0;
    public double vY = 0;
    public boolean idDead = false;
    
    public abstract void update();
    
    public boolean collide(Sprite other)
    {
        return false;
    }

}
