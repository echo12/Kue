package com.kue.mobile;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SettingsActivity extends Activity implements OnClickListener {

	// member variables
	private ListView mForgottenListView;
	private MissingItemsAdapter mForgottenListAdapter;
	private ArrayList<String> mForgottenList;
	private MenuItem addButton;
	static private final int[] PERSONAL_ITEMS_LAYOUT_IDS = {
		R.id.personal_belongings_list, R.id.edit_button, R.id.switch_toggle
	};

	@Override //onCreate was defined by its parents and we're using it
	//you initialize everything in onCreate for this activity
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); //instantiating; targeting the Activity; you need to have this first before setContentView
		setContentView(R.layout.settings); //slapping XML onto screen; allows us to add layout onto the screen/activity

		mForgottenListView = (ListView) findViewById(R.id.forgotten_items_list); //cast
		mForgottenList = new ArrayList<Item>();
		mForgottenListAdapter = new MissingItemsAdapter(this, mForgottenList); //constructor
		mForgottenListView.setAdapter(mForgottenListAdapter); //setting the adapter
		addButton = (MenuItem) findViewById(R.id.action_add_item);

		//		mForgottenList.add("Car Keys");
		//		mForgottenList.add("Water Bottle");
		//		mForgottenList.add("Umbrella");
		//		mForgottenList.add("Tablet");
		//		mForgottenList.add("Charger");
		//		mForgottenList.add("Lunch Box");
		//		mForgottenList.add("Wallet");
		//		mForgottenList.add("Travel Mug");
		//		mForgottenList.add("Bus Pass");
		//		mForgottenList.add("Kindle");
	}

	// action bar stuff
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	private void openAddItemDialog()
	{
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Please enter a new object name");
		final EditText input = new EditText(this);
		alert.setView(input);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				String value = input.getText().toString();
				mForgottenList.add(value);
				Log.i("Eura", value); // way to see what happens when button is pressed
			}
		});

		alert.setNegativeButton("Cancel", null);
		alert.show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_add_item:
			openAddItemDialog();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public class MissingItemsAdapter extends ViewAdapterBase<String>{

		public MissingItemsAdapter(Activity a, ArrayList<String> list) {
			super(a, R.layout.settings_list, PERSONAL_ITEMS_LAYOUT_IDS, list);
		}

		@Override
		protected void setWidgetValues(int position, String item, View[] elements, View layout) {
			TextView textview = (TextView) elements[0];
			textview.setText(item);
			Button editButton = (Button) elements[1];
			editButton.setOnClickListener(SettingsActivity.this);
			editButton.setTag(position);
		}
	}

	@Override
	public void onClick(View v) {

		Button editButton = (Button) v;
		final int position = (Integer) editButton.getTag();

		ViewGroup parent = (ViewGroup) v.getParent();
		final TextView itemName = (TextView) parent.findViewById(R.id.personal_belongings_list);

		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Please enter object name");
		final EditText input = new EditText(this);
		input.setText(itemName.getText());
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				String value = input.getText().toString();
				mForgottenList.set(position, value);
				mForgottenListAdapter.notifyDataSetChanged();
				Log.i("Eura", value); // way to see what happens when button is pressed
			}
		});

		alert.setNegativeButton("Cancel", null);

		alert.show();

	}

	public void GoBack(View view) {
		finish();
	}

	@Override
	public void finish() {
		// Prepare data intent
		String[] mStringArray = new String[mForgottenList.size()];
		mStringArray = mForgottenList.toArray(mStringArray);
		Intent data = new Intent();
		data.putExtra("returnKey1", mStringArray);
		setResult(RESULT_OK, data);
		super.finish();
	}

}