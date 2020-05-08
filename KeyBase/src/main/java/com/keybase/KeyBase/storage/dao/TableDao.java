package com.keybase.KeyBase.storage.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public class TableDao {
	
	public List<String> getDatabaseTables(String schema_name) {
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/","root","Hack.this@19");
			DatabaseMetaData metaData = connection.getMetaData();
			
			String[] types = {"TABLE"};
			List<String> table_list = new ArrayList<String>();
			
			ResultSet rs = metaData.getTables(null,schema_name,"%",types);
			
			while (rs.next()) {
                if(rs.getString(1).equals(schema_name))
                	table_list.add(rs.getString("TABLE_NAME"));
            }
			return table_list; 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
