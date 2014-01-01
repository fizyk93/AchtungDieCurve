/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package achtungdiecurve;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Piotr
 */
public class SpriteManager
{
//    private final static List Sprites;
    
    private static final List<Sprite> sprites = new ArrayList<Sprite>();

    public static List<Sprite> getAllSprites()
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
