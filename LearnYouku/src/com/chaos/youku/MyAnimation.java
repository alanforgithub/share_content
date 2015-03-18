package com.chaos.youku;

import android.view.View;
import android.view.animation.RotateAnimation;

public class MyAnimation{
	public static void animationIn(View view){
		animationIn(view,0);
	}
	
	public static void animationOut(View view){
		animationOut(view,0);
	}
	
	public static void animationIn(View view,long delay){
		RotateAnimation animation = new RotateAnimation(180, 360, view.getWidth()/2, view.getHeight());
		animation.setDuration(500);
		animation.setFillAfter(true);
		animation.setStartOffset(delay);
		
		view.startAnimation(animation);
	}
	
	public static void animationOut(View view,long delay){
		RotateAnimation animation = new RotateAnimation(0, 180, view.getWidth()/2, view.getHeight());
		animation.setDuration(500);
		animation.setFillAfter(true);
		animation.setStartOffset(delay);
		
		view.startAnimation(animation);
	}
}
