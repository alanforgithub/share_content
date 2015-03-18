package com.chaos.youku;

import com.chaos.youku.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener{
	private boolean isLevel2showed,isLevel3showed;
	
	private RelativeLayout level1,level2,level3;
	private ImageButton home,menu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		isLevel2showed = false;
		isLevel3showed = false;
		initLayout();
		initImageButton();
	}

	public void initLayout() {
		level1 = (RelativeLayout)findViewById(R.id.relate_level1);
		level2 = (RelativeLayout)findViewById(R.id.relate_level2);
		level3 = (RelativeLayout)findViewById(R.id.relate_level3);
		
		level2.setVisibility(View.INVISIBLE);
		level3.setVisibility(View.INVISIBLE);
	}

	public void initImageButton() {
		home = (ImageButton)level1.findViewById(R.id.home);
		menu = (ImageButton)level2.findViewById(R.id.menu);
		
		home.setOnClickListener(this);
		menu.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.home:
			if(!isLevel2showed){
				isLevel2showed = true;
				MyAnimation.animationIn(level2);
			}else if(!isLevel3showed){
				isLevel2showed = false;
				MyAnimation.animationOut(level2);
			}else{
				isLevel2showed = false;
				isLevel3showed = false;
				MyAnimation.animationOut(level3);
				MyAnimation.animationOut(level2,500);
			}
			
			break;
		case R.id.menu:
			if(!isLevel3showed){
				isLevel3showed = true;
				MyAnimation.animationIn(level3);
			}else{
				isLevel3showed = false;
				MyAnimation.animationOut(level3);
			}
			break;
		}
	}
}
