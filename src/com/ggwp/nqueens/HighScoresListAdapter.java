package com.ggwp.nqueens;

import java.util.List;




import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HighScoresListAdapter extends ArrayAdapter<Integer>{

	 
	List<Integer> scores;
	Context context;
	public HighScoresListAdapter(Context context, int resource,	List<Integer> scores) {
		super(context, resource, scores);
		this.context = context;
		this.scores = scores;
	}
	
	class ViewHolder {
		TextView levelTag;
		TextView scoreTag;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		int score = scores.get(position);
		
		ViewHolder holder;
		
		LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        
		
        if (convertView == null) {
        	
            convertView = mInflater.inflate(R.layout.high_score_list_item, null);
            holder = new ViewHolder();
            holder.levelTag = (TextView) convertView.findViewById(R.id.levelTag);
            holder.scoreTag = (TextView) convertView.findViewById(R.id.scoreTag);
            convertView.setTag(holder);
            
        } else
            holder = (ViewHolder) convertView.getTag();
		
        int boardSize = position+4;
        holder.levelTag.setText("Level "+(position+1)+" "+boardSize+"x"+boardSize);
        holder.levelTag.setTextColor(Color.BLACK);
        holder.scoreTag.setTextColor(Color.BLACK);
        
        if(score == boardSize) {
        	holder.scoreTag.setText("Perfect! "+score+"");
        }
        else {
        	holder.scoreTag.setText(score+"");
        }
        
		return convertView;
	}

}
