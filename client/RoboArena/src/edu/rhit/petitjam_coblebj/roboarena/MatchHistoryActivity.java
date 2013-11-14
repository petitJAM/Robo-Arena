package edu.rhit.petitjam_coblebj.roboarena;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MatchHistoryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_match_history);
		
		ListView listView = (ListView)findViewById(R.id.list_view);
		
		final MatchHistoryAdapter adapter = new MatchHistoryAdapter(this);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(MatchHistoryActivity.this, "pressed " + position, Toast.LENGTH_SHORT).show();
				Intent matchDetailsIntent = new Intent(MatchHistoryActivity.this, MatchDetailsActivity.class);
				startActivity(matchDetailsIntent);
			}
		});
		
		findViewById(R.id.add_match_button).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(MainMenuActivity.RA, "add match pressed");
				adapter.addMatch();
				adapter.notifyDataSetChanged();
			}
		});
		
		listView.setAdapter(adapter);
	}

	private class MatchHistoryAdapter extends BaseAdapter {
		
		private Context mContext;
		private int mCount;
		
		public MatchHistoryAdapter(Context context) {
			mContext = context;
			mCount = 0;
		}
		
		public void addMatch() {
			mCount++;
			Log.d(MainMenuActivity.RA, "add match " + mCount);
		}

		@Override
		public int getCount() {
			return mCount;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			RowView view = null;
			if (convertView == null) {
				view = new RowView(mContext);
			} else {
				view = (RowView)convertView;
			}
			
			view.setOutcome(position % 2 == 0);
			view.setOpponentName(getString(R.string.default_opponent_name));
			view.setDate(getString(R.string.default_date));
			
			return view;
		}

		private class RowView extends LinearLayout {

			private Context mContext;
			
			private TextView mOutcome;
			private TextView mOpponent;
			private TextView mDate;

			public RowView(Context context) {
				super(context);
				mContext = context;
				
				LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
				inflater.inflate(R.layout.match_history_row_view, this);
				
				mOutcome = (TextView)findViewById(R.id.outcome);
				mOpponent = (TextView)findViewById(R.id.opponent);
				mDate = (TextView)findViewById(R.id.date_tv);
			}
			
			public void setOutcome(boolean didWin) { // TODO: doesn't do draws
				if (didWin) {
					mOutcome.setText(R.string.win);
				} else {
					mOutcome.setText(R.string.lose);
				}
			}
			
			public void setOpponentName(String opponentName) {
				mOpponent.setText(opponentName);
			}
			
			public void setDate(String date) { // TODO: use a Date object?
				mDate.setText(date);
			}
		}
	}
}
