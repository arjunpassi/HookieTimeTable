package com.example.hookietimetable;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ProfessorDataParser {

	private String htmlData;
	
	ProfessorDataParser(String html){
		htmlData = html;
	}
	
	List<Professor> getProfessors(){
		List<Professor> professors = new ArrayList<Professor>();
		
		if(htmlData == null)
			return professors;
		
		Document doc = Jsoup.parse(htmlData);
		
		Elements elements = doc.select("div");
		String d = elements.text();
		
		
		if(elements.size() < 32)
			return professors;
		
		String number [] = elements.get(31).text().split(" ");
		int professorsFound = 0;
		
		if(number.length > 5)
			professorsFound = Integer.valueOf(number[4]);
		else if (professorsFound == 0)
			return professors;
		
		int start = 45;
		
		for(int i = 0; i < professorsFound; i++){
			Elements elem = elements.get(start).select("div");
			
			if(elem.size() > 9){
				String name = elem.get(4).text();
				String department = elem.get(5).text();
				String ratings = elem.get(6).text();
				String quality = elem.get(7).text();
				String easiness = elem.get(8).text();
				
				Professor professor = new Professor(name,
						department, ratings, quality, easiness);
				
				professors.add(professor);
			}
			
			start += 10;
		}
		
		
		return professors;
	}
}
