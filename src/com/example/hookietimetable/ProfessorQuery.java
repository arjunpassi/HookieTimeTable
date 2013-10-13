package com.example.hookietimetable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;

/**
 * Time Table Query class is responsible for reading the professer rating
 * from "http://www.ratemyprofessors.com/SelectTeacher.jsp"
 * 
 * @author Arjun Passi
 *
 */
public class ProfessorQuery extends AsyncTask<Void, Void, String>{

	MainActivity mActivity;
	
	private String url = "http://www.ratemyprofessors.com/SelectTeacher.jsp";
	
	public ProfessorQuery(MainActivity activity){
		mActivity = activity;
	}
	
	public String getProfessorData() throws Exception
	{
		BufferedReader in = null;
		String data = null;
		try
		{
			// setup http client
			HttpClient client = new DefaultHttpClient();
			// process data from
			URI website = new URI(url);
			// request using get method
			//HttpGet request = new HttpGet(website);
			HttpPost post = new HttpPost(website);
			
			String name = mActivity.getProfessorName();
			
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(10);
			nameValuePairs.add(new BasicNameValuePair("searchName", name));
			nameValuePairs.add(new BasicNameValuePair("search_submit1", "Search"));
			nameValuePairs.add(new BasicNameValuePair("sid", "1349"));
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs);
			post.setEntity(entity);
			
			HttpResponse response = client.execute(post);
			
			//response = client.execute(post);
			// string using buffered reader
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer( "" );
			String l = "";
			String newline = System.getProperty( "line.separator" );
			while ( (l = in.readLine()) != null ) {
				sb.append( l + newline );
			}
			
			in.close();
			data = sb.toString();
			
			return data;
			
		} 
		
		finally {
			if ( in != null ) {
				try {
					in.close();
					return data;
				} catch ( Exception e ) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	protected String doInBackground(Void... params) {
		try {
			return getProfessorData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void onPostExecute( String result )
	{
		Parse parse = new Parse(result);
		parse.execute((Void) null);
	}
	

	/**
	 * This parse class is responsible for parsing the data received in
	 * a separate thread. It uses a ProfessorDataParser class defined.
	 * @author Arjun Passi
	 *
	 */	
	class Parse extends AsyncTask<Void, Void, List<Map<String, String>> >{
		private String htmlData;
		
		Parse(String data){
			htmlData = data;
		}
		
		@Override
		protected List<Map<String, String>> doInBackground(Void... params) {
			ProfessorDataParser parser = new ProfessorDataParser(htmlData);
			List<Professor> list = parser.getProfessors();
			
			List<Map<String, String>> professors = new ArrayList<Map<String, String>>(); 
			
			for(int a = 0; a < list.size(); a++){
				HashMap<String, String> vtClass = new HashMap<String, String>();
			    vtClass.put("key", list.get(a).toString());
			    professors.add(vtClass);
			}
			
			return professors;
		}
		
		protected void onPostExecute(List<Map<String, String>> result){
			StringBuilder build = new StringBuilder();
			build.append("Number of professors found: " + result.size());
			mActivity.dataReceivedFromNetwork(result);
			mActivity.dataReceivedFromNetwork(build.toString());
		}
		
	}

}
