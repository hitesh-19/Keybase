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
import com.keybase.KeyBase.projects.Project;

@Repository
public class ProjectDao {

	private String path = "E:\\Projects\\KeyBase\\java application\\KeyBase\\KeyBase\\src\\main\\resources\\projects.json";
	
	public Project saveProject(Project project) {

		ObjectMapper objectMapper = new ObjectMapper();
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(path,true);
			if(!this.retriveAllProjects().isEmpty())
				fileWriter.write("\n");
			
			objectMapper.writeValue(fileWriter, project);
			fileWriter.close();
			return project;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Project retriveSingleProject(String project_name) {
		List<Project> project_list = this.retriveAllProjects();
		Iterator<Project> iterator = project_list.iterator();
		Project data = null;
		
		while(iterator.hasNext()) {
			data = iterator.next();
			if(data.getProject_name().equals(project_name)) {
				return data;
			}
		}
		return null;
	}
	
	public List<Project> retriveAllProjects() {
		

		ObjectMapper objectMapper = new ObjectMapper();
		ArrayList<Project> project_list = new ArrayList<Project>();
		
		FileReader filereader;
		try {
			filereader = new FileReader(path);

			BufferedReader bufferedreader = new BufferedReader(filereader); 
			String data = bufferedreader.readLine();
			
			while(data!=null) {
				
				project_list.add(objectMapper.readValue(data, Project.class));
				
				data = bufferedreader.readLine();
			}
			bufferedreader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return project_list;
	}
}
