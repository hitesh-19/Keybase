package com.keybase.KeyBase.storage.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keybase.KeyBase.projects.ApiKey;

@Repository
public class ApiDao {
	
	private final String path = "E:\\Projects\\KeyBase\\java application\\KeyBase\\KeyBase\\src\\main\\resources\\api's.json"; 

	public String saveApiKeys(ApiKey apiKey) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(path,true);
			
			if(!this.retriveAllApis().isEmpty())
				fileWriter.write("\n");
				
			objectMapper.writeValue(fileWriter, apiKey);
			fileWriter.close();
			
			return apiKey.getApi_key();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private List<ApiKey> retriveAllApis() {
		
		ObjectMapper objectMapper = new ObjectMapper();
		ArrayList<ApiKey> all_api_list = new ArrayList<ApiKey>();
		
		FileReader filereader;
		try {
			filereader = new FileReader(path);

			BufferedReader bufferedreader = new BufferedReader(filereader); 
			String data = bufferedreader.readLine();
			
			while(data!=null) {

				all_api_list.add(objectMapper.readValue(data, ApiKey.class));
				
				data = bufferedreader.readLine();
			}
			bufferedreader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return all_api_list;
	}
	
	public List<ApiKey> retriveProjectApi(String project_name) {
		
		List<ApiKey> apikey_list = new ArrayList<ApiKey>();
		
		Iterator<ApiKey> iterator = this.retriveAllApis().iterator();
		ApiKey data = null;
		
		while(iterator.hasNext()) {
			data = iterator.next();
			if(data.getProject_name().equals(project_name))
				apikey_list.add(data);
		}
		
		return apikey_list;
	}
}
