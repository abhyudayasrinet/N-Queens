package com.ggwp.nqueens;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class GameActivity extends Activity {

	
	int height;
	int width;
	int level;
	LinearLayout mainLayout,topBar;
	TextView movesTV;
	Button nextLevelBT;
	NQueensDB DB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);   
        height = metrics.heightPixels;
        width = metrics.widthPixels;
		
        Intent intent = getIntent();
        level = intent.getIntExtra("level", 1);
        
        DB = new NQueensDB(getApplicationContext());
        
        mainLayout = new LinearLayout(getApplicationContext());
        mainLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        
        
        topBar = new LinearLayout(getApplicationContext());
        topBar.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,0,0.1f));
        topBar.setOrientation(LinearLayout.HORIZONTAL);
        
        movesTV = new TextView(getApplicationContext());
        movesTV.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 0.5f));
        movesTV.setTextAppearance(this, android.R.style.TextAppearance_Large);
        movesTV.setText("Moves Made : 0");
        movesTV.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        movesTV.setTextColor(Color.BLACK);
        topBar.addView(movesTV);
        
        nextLevelBT = new Button(getApplicationContext());
        nextLevelBT.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 0.5f));
        nextLevelBT.setText("Next Level");
        nextLevelBT.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        if(DB.getScore(level) == 0)
        	nextLevelBT.setVisibility(View.INVISIBLE);
        nextLevelBT.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(getApplicationContext(),GameActivity.class);
				intent.putExtra("level", level+1);
				startActivity(intent);
			}
		});
        topBar.addView(nextLevelBT);
        mainLayout.addView(topBar);
     
		GameView view = new GameView(this, height, width, level, movesTV);
		view.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,0,0.8f));
		
		mainLayout.addView(view);
		
		AdView adView = new AdView(getApplicationContext());
		adView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,0,0.1f));
		adView.setAdUnitId("ca-app-pub-4192002242677873/2514655745");
		adView.setAdSize(AdSize.BANNER);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
		
		mainLayout.addView(adView);
		
		setContentView(mainLayout);
	}
}
