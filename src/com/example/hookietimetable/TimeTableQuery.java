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

import com.example.hookietimetable.MainActivity;

import android.os.AsyncTask;

/**
 * Time Table Query class is responsible for reading the classes
 * from "https://banweb.banner.vt.edu/ssb/prod/HZSKVTSC.P_ProcRequest"
 * 
 * @author Arjun Passi
 *
 */
public class TimeTableQuery extends AsyncTask<Void, Void, String>
{

	/** Reference to the MainActivity class*/
	private MainActivity mActivity;

	/**
	 * Constructs TimeTable Query Object
	 * @param activityToCallBack
	 */
	public TimeTableQuery( MainActivity activityToCallBack ){
		mActivity = activityToCallBack;
	}

	public String getTimeTableData() throws Exception {
		
		BufferedReader in = null;
		String data = null;
		try {
			// setup HTTP client
			HttpClient client = new DefaultHttpClient();
			URI website = new URI(
					"https://banweb.banner.vt.edu/ssb/prod/HZSKVTSC.P_ProcRequest");
			// request using post method
			HttpPost post = new HttpPost(website);
			
			String subject = mActivity.getCourseName();
			String number = mActivity.getCourseNumber();
			
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(10);
			nameValuePairs.add(new BasicNameValuePair("CAMPUS", "0")); 
			nameValuePairs.add(new BasicNameValuePair("TERMYEAR", "201309"));
			nameValuePairs.add(new BasicNameValuePair("CORE_CODE", "AR%"));
			nameValuePairs.add(new BasicNameValuePair("SCHDTYPE", "%"));
			//nameValuePairs.add(new BasicNameValuePair("open_only", "on"));
			nameValuePairs.add(new BasicNameValuePair("subj_code", subject));
			nameValuePairs.add(new BasicNameValuePair("CRSE_NUMBER", number));
			nameValuePairs.add(new BasicNameValuePair("BTN_PRESSED", "FIND class sections"));
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs);
			post.setEntity(entity);
			
			HttpResponse response = client.execute(post);
			response = client.execute(post);
			// string using buffered reader
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer buffer = new StringBuffer( "" );
			String line = "";
			String newline = System.getProperty( "line.separator" );
			while ( (line = in.readLine()) != null ) {
				buffer.append( line + newline );
			}
			
			in.close();
			data = buffer.toString();
			
			return data;
			
		} finally {
			if ( in != null ){
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
	protected String doInBackground( Void... params ) {
		try{
			return getTimeTableData();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	protected void onPostExecute( String result ) {
		Parse parse = new Parse(result);
		parse.execute((Void) null);
	}
	
	
	/**
	 * This parse class is responsible for parsing the data received in
	 * a separate thread. It uses a HtmlTableParse class defined.
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
			HtmlTableParser parser = new HtmlTableParser(htmlData);
			List<VTClass> list = parser.getClasses();
			
			List<Map<String, String>> m_classList = new ArrayList<Map<String, String>>(); 
			
			for(int a = 0; a < list.size(); a++){
				HashMap<String, String> vtClass = new HashMap<String, String>();
			    vtClass.put("key", list.get(a).toString());
			    m_classList.add(vtClass);
			}
			
			return m_classList;
		}
		
		protected void onPostExecute(List<Map<String, String>> result){
			StringBuilder build = new StringBuilder();
			build.append("Number of classes found: " + result.size());
			mActivity.dataReceivedFromNetwork(result);
			mActivity.dataReceivedFromNetwork(build.toString());
		}
		
	}

	
}