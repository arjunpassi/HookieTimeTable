package com.example.hookietimetable;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * This class is responsible for parsing an HTML string. 
 * It uses Jsoup to parse the HTML string. Implements a function
 * that returns a LIST of VTCLass objects
 * @author arjun
 *
 */
public class HtmlTableParser {

	private String htmlString;
	
	/**
	 * Constructor -HTML TABLE Parser
	 * @param str
	 */
	HtmlTableParser(String str){
		htmlString = str;
	}
	
	/**
	 * This method returns a list of VTClass objects
	 * @return
	 */
	public List<VTClass> getClasses(){
		
		List<VTClass> classes = new ArrayList<VTClass>();
		
		//Checking if the html string is valid
		if(htmlString == null)
			return classes;
		
		Document document = Jsoup.parse(htmlString);
		
		//Table of classes is the 4th one
		if(document.select("table").size() < 5)
			return classes;
		
		Elements rows = document.select("table").get(4).select("tr");
		Elements columns;
		String data = "";
		
		if(rows.size() == 0)
			return classes;
		
		for(int i = 1; i < rows.size(); i++){
			columns = rows.get(i).select("td");
			data = columns.toString();
			
			//Checking if the class has additional times
			if(data.contains("Additional Times")){
				VTClass vtCourse = classes.get(classes.size() - 1);
				
				String additionalDay = columns.get(5).text();
				String additionalBeginTime = columns.get(6).text();
				String additionalEndTime = columns.get(7).text();
				String additionalLocation = columns.get(8).text();
				
				vtCourse.setAdditionalDay(additionalDay);
				vtCourse.setAdditionalBeginTime(additionalBeginTime);
				vtCourse.setAdditionalEndTime(additionalEndTime);
				vtCourse.setAdditionalLocation(additionalLocation);
			}
			
			else{
				if(columns.size() < 11)
					return classes;
				
				String CRN = columns.get(0).text();
				String course = columns.get(1).text();
				String title = columns.get(2).text();
				String type = columns.get(3).text();
				String credits = columns.get(4).text();
				String capacity = columns.get(5).text();
				String instructor = columns.get(6).text();
				String days = columns.get(7).text();
				
				String beginTime = columns.get(8).text();
				String endTime = columns.get(8).text();
				String location = columns.get(9).text();
				
				if(columns.size() == 12){
					beginTime = columns.get(8).text();
					endTime = columns.get(9).text();
					location = columns.get(10).text();
				}
				
				//Create a new VT Class and add that to the list
				VTClass vtCourse = new VTClass(CRN,  course, title, type, 
						credits, capacity, instructor, days, beginTime,
						endTime, location);
				
				classes.add(vtCourse);
			}
			
		}
		
		return classes;
	
	}
}
