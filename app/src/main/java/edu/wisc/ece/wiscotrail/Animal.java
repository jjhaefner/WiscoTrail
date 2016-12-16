package edu.wisc.ece.wiscotrail;

import android.content.Context;
import android.widget.ImageView;

/**
Not used yet
 */

public class Animal extends ImageView {
    private int hitPoints;
    private ImageView[] bulletHoles;
    private boolean alive = true;
    private int meat;

    public Animal(Context context, int hitPoints, int meat) {
        super(context);
        this.hitPoints = hitPoints;
        this.meat = meat;
        bulletHoles = new ImageView[hitPoints];
    }

    public void shoot(){
        if(hitPoints <= 0) return;
        else if(hitPoints == 1){
            //TODO kill method?
            alive = false;
        }
        hitPoints--;
        return;
    }

    public int getMeat(){
        return meat;
    }
}
