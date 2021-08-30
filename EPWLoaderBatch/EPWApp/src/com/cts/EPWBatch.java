package com.cts;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class EPWBatch {
	
	//read json to object
	//query DB for jobnames
	//compare command job names and load the box names in list(unsuccessful)
	
	
	/**
	 * @param environment
	 * @return
	 */
	private List<EPWLoaderSummary> getEPWLoaderSummaryFromJsonProp(String environment) {
		List<EPWLoaderSummary> epwLoaderSummaries = new ArrayList<EPWLoaderSummary>();
		StringBuilder file = new StringBuilder("resource/EPW_BI_");
		file.append(environment);
		file.append(".json");
		System.out.println(file);
		JSONParser parser = new JSONParser();
		try {
			Object object =  parser.parse(new FileReader(file.toString()));
			JSONArray jsonArray = (JSONArray) object;
			jsonArray.stream().forEach(job -> {
				JSONObject epwJob = (JSONObject) job;
				JSONObject epwSummary = (JSONObject) epwJob.get("job");
				EPWLoaderSummary epwLoaderSummary = new EPWLoaderSummary(
						(String) epwSummary.get("commandJobName"), 
						(String)epwSummary.get("environment"), 
						(String)epwSummary.get("boxJobName"));
				epwLoaderSummaries.add(epwLoaderSummary);
			});
			
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(epwLoaderSummaries);
		return epwLoaderSummaries;
	}
	
	
	public List<String> getFailureBoxNames(String environment){
		List<String> failureBoxNames = new ArrayList<String>();
		List<String> epwJobsFromDB = getEPWJobFromDB(environment);
		List<EPWLoaderSummary> epwLoaderSummaries = getEPWLoaderSummaryFromJsonProp(environment);
		for(EPWLoaderSummary epwLoaderSummary : epwLoaderSummaries) {
			if(!epwJobsFromDB.contains(epwLoaderSummary.getCommandJobName())) {
				failureBoxNames.add(epwLoaderSummary.getBoxJobName());
			}
		}
		return failureBoxNames;
	}
	
	private List<String> getEPWJobFromDB(String environment){
		//i've hard coded this. the method will have the logic to fetch epwjob details from DB.
		
		return Arrays.asList("commandJobName1","commandJobName3");
	}
	

}
