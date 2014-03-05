package com.kue.mobile;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ListView mForgottenListView;
	private MissingItemsAdapter mForgottenListAdapter;
	public ArrayList<Item> mForgottenList;

	static private final int[] MISSING_ITEMS_LAYOUT_IDS = {
		R.id.missing_items, R.id.x_button
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); //instantiating
		setContentView(R.layout.activity_main); //slapping XML onto screen
		mForgottenListView = (ListView) findViewById(R.id.forgotten_items_list); //cast
		mForgottenList = new ArrayList<Item>();
		mForgottenListAdapter = new MissingItemsAdapter(this, mForgottenList); //constructor
		mForgottenListView.setAdapter(mForgottenListAdapter); //setting the adapter

		//		mObjectListView = (ListView) findViewById(R.id.items_list); //cast
		//		mObjectListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		//		mObjectListView.setAdapter(mObjectListAdapter); //setting the adapter

		mForgottenList.add(new Item(1,"Car Keys",true));
		mForgottenList.add(new Item(2,"Water Bottle",true));
		mForgottenList.add(new Item(3,"Umbrella",true));
		mForgottenList.add(new Item(4,"Tablet",true));
		mForgottenList.add(new Item(5,"Charger",true));
		mForgottenList.add(new Item(6,"Lunch Box",true));
		mForgottenList.add(new Item(7,"Wallet",true));
		mForgottenList.add(new Item(8,"Travel Mug",true));
		mForgottenList.add(new Item(9,"Buss Pas",true));
		mForgottenList.add(new Item(10,"Kindle",true));

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == 0) {
			if (data.hasExtra("returnKey1")) {
				Intent i = getIntent();
				// mForgottenList = i.getStringArrayListExtra("returnKey1");
				Toast.makeText(this, data.getExtras().getString("returnKey1"), Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// open settings activity
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent myIntent = new Intent(this, SettingsActivity.class);
			startActivityForResult(myIntent, 0);
			return true;
		case R.id.action_notifications:
			Intent myIntent2 = new Intent(this, NotificationsActivity.class);
			startActivityForResult(myIntent2, 0);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public class MissingItemsAdapter extends ViewAdapterBase<Item>{

		public MissingItemsAdapter(Activity a, ArrayList<Item> mForgottenList) {
			super(a, R.layout.missing_item_list_item, MISSING_ITEMS_LAYOUT_IDS, mForgottenList);
		}

		@Override
		protected void setWidgetValues(int position, Item item, View[] elements, View layout) {
			// item will change; http://www.ezzylearning.com/tutorial.aspx?tid=6816874 stuff goes inside here
			// text; boolean for green/red
			TextView textview = (TextView) elements[0];
			textview.setText(item.getName());

			ImageView xButton = (ImageView) elements[1];
			if (item.getRange()){
				xButton.setImageDrawable(getResources().getDrawable(R.drawable.badge_square_check));
			}
			else{
				xButton.setImageDrawable(getResources().getDrawable(R.drawable.alert_square_red));
			}
		}

	}

	//	SharedPreferences
	//	private void showUserSettings() {
	//		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
	//
	//		StringBuilder builder = new StringBuilder();
	//
	//		builder.append("\n Notification Sound Alert: " + sharedPrefs.getBoolean("soundAlert", false));
	//
	//		builder.append("\n Notification Vibrate Alert: " + sharedPrefs.getBoolean("vibrateAlert", false));
	//
	//		TextView settingsTextView = (TextView) findViewById(R.id.textUserSettings);
	//
	//		settingsTextView.setText(builder.toString());
	//	}

}
