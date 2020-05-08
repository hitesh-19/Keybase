package com.keybase.KeyBase.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keybase.KeyBase.projects.ApiKey;
import com.keybase.KeyBase.storage.dao.ApiDao;


@Repository
public class DatabaseOperation {
	
	@Autowired
	Queries queries;
	@Autowired
	ApiDao apiDao;
	@Autowired
	ApiKey apiKey;
	
	private final String path = "E:\\Projects\\KeyBase\\java application\\KeyBase\\KeyBase\\src\\main\\java\\com\\keybase\\KeyBase\\database\\Api.java";
	
	public void creatingApiOfData(String project_name, String api_type, String api_name, String schema_name, String table_name) {
		
		String readed_data = this.readAllMethods();
		
		try {
			FileWriter fileWriter = new FileWriter(path);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.write(readed_data);
			
			if(api_type.equals("select")) {
				bufferedWriter.write(queries.selectAll(api_type,api_name, schema_name, table_name));
			} else if(api_type.equals("insert")) {
				bufferedWriter.write(queries.insert(api_type,api_name, schema_name, table_name));
			}
			bufferedWriter.write("}");
			bufferedWriter.close();
			
			apiKey.setProject_name(project_name);
			apiKey.setSchema_name(schema_name);
			apiKey.setTable_name(table_name);
			apiKey.setKey_title(api_type);
			apiKey.setApi_key(api_name);
			
			apiDao.saveApiKeys(apiKey);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String readAllMethods() {
		
		FileReader fileReader;
		String methods = "";
		try {
			int number_of_lines = this.countNumberOfLines();
			fileReader = new FileReader(path);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String new_data = bufferedReader.readLine();
			while(number_of_lines>1) {
				methods += new_data+"\n";
				new_data = bufferedReader.readLine();
				number_of_lines--;
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(methods);
		return methods;
	}
	
	private int countNumberOfLines() {
		FileReader fileReader;
		try {
			fileReader = new FileReader(path);
			BufferedReader bufferedLineReader = new BufferedReader(fileReader);
			String data = bufferedLineReader.readLine();
			int number_of_lines = 0;
			while(data!=null) {
				number_of_lines++;
				data = bufferedLineReader.readLine();
			}
			bufferedLineReader.close();
			return number_of_lines;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
