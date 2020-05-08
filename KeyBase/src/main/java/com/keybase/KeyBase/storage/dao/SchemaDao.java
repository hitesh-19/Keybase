package com.keybase.KeyBase.storage.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchemaDao {
	
	private List<String> removeDuplicates(List<String> list) {
		
		List<String> newList = new ArrayList<String>(); 
		   
        for (String element : list) {
            if (!newList.contains(element)) { 
                newList.add(element); 
            }
        }
        return newList;
	}

	public List<String> getDatabaseSchemas() {
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/","root","Hack.this@19");
			DatabaseMetaData metaData = connection.getMetaData();
			
			List<String> schema_list = new ArrayList<String>();
			
			ResultSet rs = metaData.getTables(null,null,"%",new String[] {"TABLE"});
			
			while (rs.next()) {
				schema_list.add(rs.getString(1));
            }	
			schema_list = removeDuplicates(schema_list);
			return schema_list; 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
