package com.example.hookietimetable;

import java.util.List;
import java.util.Map;

import com.example.hookiefootball_example.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * MainActivty class
 * @author arjun
 *
 */
public class MainActivity extends Activity {

	/** Reference to the TimeTableQueryThread*/
	private TimeTableQuery timeTableQuery;
	
	/** Reference to the ProfessorQuery class*/
	private ProfessorQuery professorQuery;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		timeTableQuery = new TimeTableQuery(this);
		professorQuery = new ProfessorQuery(this);
	}

	/**
	 * This method is called when the find course
	 * button is pressed
	 * @param view
	 */
	public void findCourse(View view){
		
		if(timeTableQuery.getStatus() == AsyncTask.Status.RUNNING)
			return;
		
		timeTableQuery.cancel(true);
		timeTableQuery = null;
		timeTableQuery = new TimeTableQuery(this);
		timeTableQuery.execute((Void) null);
		displayMessage("Searching for " + getCourseName() + " " + getCourseNumber() + "...");
	}
	
	/**
	 * This method is called when the find professor
	 * button is pressed
	 * @param view
	 */
	public void findProfessor(View view){
		
		if(professorQuery.getStatus() == AsyncTask.Status.RUNNING)
			return;
		
		if(professorQuery.getStatus() != AsyncTask.Status.RUNNING){
			professorQuery.cancel(true);
			professorQuery = null;
			professorQuery = new ProfessorQuery(this);
		}
		
		professorQuery.execute((Void) null);
		displayMessage("Searching for " + getProfessorName() + "...");
	}
	
	/**
	 * This method is called on the post execute method of TimeTableQuery or
	 *  ProfessorQuery
	 * @param result
	 */
	public void dataReceivedFromNetwork(String result) {
		displayMessage(result);
	}
	
	/**
	 * This method is called on the post execute method of TimeTableQuery or
	 *  ProfessorQuery
	 * @param result
	 */
	public void dataReceivedFromNetwork(List<Map<String, String>> data){
		ListAdapter adapter = new SimpleAdapter(this, data, 
				android.R.layout.simple_list_item_1, 
				new String[] {"key"}, new int[] {android.R.id.text1});
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
	}
	
	/**
	 * Returns the professor name
	 * @return
	 */
	public String getProfessorName(){
		EditText editText = (EditText) findViewById(R.id.course);
		return editText.getText().toString();
	}

	/**
	 * Returns the course name
	 * @return
	 */
	public String getCourseName() {
		EditText editText = (EditText) findViewById(R.id.course);
		String text = editText.getText().toString();
		
		String arr[] = text.split(" ");
		
		if(arr.length >= 1)
			return arr[0].toUpperCase();
		else
			return "";
	}

	/**
	 * Returns the course number
	 * @return
	 */
	public String getCourseNumber() {
		EditText editText = (EditText) findViewById(R.id.course);
		String text = editText.getText().toString();
		String arr[] = text.split(" ");
		
		if(arr.length == 2)
			return arr[1];
		else
			return new String();
	}
	
	/**
	 * Writes the string to the text view
	 * @param message
	 */
	private void displayMessage(String message){
		TextView textView = (TextView) findViewById(R.id.textView1);
		textView.setText(message);
	}

}
