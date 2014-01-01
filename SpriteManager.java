/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package achtungdiecurve;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.Node;

/**
 *
 * @author Piotr
 */
public class SpriteManager
{
//    private final static List Sprites;
    
    private final List<Sprite> sprites = new ArrayList<Sprite>();
    private final List<Node> collisionNodes = new ArrayList<Node>();

    public SpriteManager()
    {
    }
    
    public  List<Node> getCollisionNodes()
    {
        return collisionNodes;
    }
    
    public  void addCollisionNodes(Node... nodes)
    {
        collisionNodes.addAll(Arrays.asList(nodes));
    }
    
    public List<Sprite> getAllSprites()
    {
        return sprites;
    }
    
    public void addSprites(Sprite... sprites)
    {
        this.sprites.addAll(Arrays.asList(sprites));
    }
    
    public void removeSprites(Sprite... sprites)
    {
        this.sprites.removeAll(Arrays.asList(sprites));
    }

    
}
