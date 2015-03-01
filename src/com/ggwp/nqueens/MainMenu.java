package com.ggwp.nqueens;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainMenu extends Activity{

	
	int height,width;
	Button playButton,highscoresButton;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_menu);
		
		RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.mainMenuLayout);
		
		
		DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);   
        height = metrics.heightPixels;
        width = metrics.widthPixels;
        
		Drawable background = getResources().getDrawable(R.drawable.background);
		int size = Math.min(height, width);
		background.setBounds(0, 0, size, size);
		
		int sdk = android.os.Build.VERSION.SDK_INT;
		if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
		    mainLayout.setBackgroundDrawable( background );
		} else {
		    mainLayout.setBackground( background);
		}
		
		
		playButton = (Button) findViewById(R.id.playButton);
		playButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				Intent intent = new Intent(getApplicationContext(),GameActivity.class);
				intent.putExtra("level", 1);
				startActivity(intent);
				
			}
		});
		
		highscoresButton = (Button) findViewById(R.id.highscoresButton);
		highscoresButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(getApplicationContext(),HighScores.class);
				startActivity(intent);
				
			}
		});
		
		AdView adView = (AdView) findViewById(R.id.adViewMainMenu);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
	}
}
