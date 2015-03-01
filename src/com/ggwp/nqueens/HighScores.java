package com.ggwp.nqueens;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

public class HighScores extends Activity {

	ListView listview;
	NQueensDB DB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.high_scores);
		listview = (ListView) findViewById(R.id.highScoreListView);
		DB = new NQueensDB(getApplicationContext());
		
		List<Integer> scores = new ArrayList<Integer>();
		
		for(int i =1 ; i <= 10 ; i++) {
			
			scores.add(DB.getScore(i));
			
		}
		
		HighScoresListAdapter adapter = new HighScoresListAdapter(getApplicationContext(), R.layout.high_score_list_item, scores);
		listview.setAdapter(adapter);
		
	}
	
}
