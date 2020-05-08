package com.keybase.KeyBase;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.keybase.KeyBase.projects.Project;


@SpringBootApplication
public class KeyBaseApplication {
	
	static Logger logger = LoggerFactory.getLogger(KeyBaseApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(KeyBaseApplication.class, args);
		
//		Project project = applicationContext.getBean(Project.class);
		
//		project.createProject("Demo2","messenger_application");
//		project.getAllProjects();
//		
//		ApiKey apiKey = applicationContext.getBean(ApiKey.class);
//		apiKey.addApiKey(project.getProject_name(), project.getSchema_name(), "user", "inser key", "add-user");
//		apiKey.getApi_key();
		
//		DatabaseOperation databaseOperation = applicationContext.getBean(DatabaseOperation.class);
//		
//		JSONObject obj = new JSONObject();
//		obj.put("s_no", 30);
//		obj.put("email_id", "c@c.com");
//		obj.put("password", "c");
//		obj.put("user_name", "c1");
//		//databaseOperation.insertData(obj);
//		System.out.println(databaseOperation.selectData());
		
//		databaseOperation.creatingApiOfData("Demo2","select", "user-data", "messenger_application", "users");
//		databaseOperation.creatingApiOfData("insert", "user-data", "messenger_application", "users");
//		databaseOperation.creatingApiOfData("select", "get-chatbot-user", "chatbot", "user");
//		databaseOperation.creatingApiOfData("insert", "chatbot-user", "chatbot", "user");
//		ApiClasses bean = applicationContext.getBean(ApiClasses.class);
//		System.out.println(bean.selectAllmessenger_applicationusersData());
		
//		ColumnDao columnDao = applicationContext.getBean(ColumnDao.class);
//		System.out.println(columnDao.getColumns("messenger_application", "users"));
	}
}
