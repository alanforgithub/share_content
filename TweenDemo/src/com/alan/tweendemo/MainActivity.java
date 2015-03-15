package com.alan.tweendemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView animIV;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        animIV = (ImageView) findViewById(R.id.animIV);
        
    }
    
    public void onClick(View view) {
    	Animation anim = null;
    	
    	switch (view.getId()) {
			case R.id.alphaBT:
				anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
				break;
			case R.id.scaleBT:
				anim = AnimationUtils.loadAnimation(this, R.anim.scale);
				break;
			case R.id.tanslateBT:
				anim = AnimationUtils.loadAnimation(this, R.anim.translate);
				break;
			case R.id.rotateBT:
				anim = AnimationUtils.loadAnimation(this, R.anim.rotate);
				break;
			case R.id.allBT:
				anim = AnimationUtils.loadAnimation(this, R.anim.all);
				break;
		}
    	
    	animIV.startAnimation(anim);
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
    	// TODO Auto-generated method stub
    	super.overridePendingTransition(R.anim.rotate, R.anim.translate);
    }
    
    
    
}
