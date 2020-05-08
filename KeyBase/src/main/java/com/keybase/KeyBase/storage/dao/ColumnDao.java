package com.keybase.KeyBase.storage.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ColumnDao {

	List<Integer> datatype_list = new ArrayList<Integer>();
	
	public List<String> getColumns(String schema_name, String table_name) {
		List<String> list = new ArrayList<String>();
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+schema_name,"root","Hack.this@19");
			DatabaseMetaData metaData = connection.getMetaData();
			
			ResultSet columns = metaData.getColumns(null, schema_name, table_name, "%");
			
			while(columns.next()) {
				if(columns.getString("IS_AUTOINCREMENT").equals("NO") && columns.getString(1).equals(schema_name)) {
					list.add(columns.getString("COLUMN_NAME"));
					datatype_list.add(columns.getInt("DATA_TYPE"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<Integer> getColumnsDatatype() {
		System.out.println(datatype_list);
		return datatype_list;
	}
}
/*
 -7	BIT
-6	TINYINT
-5	BIGINT
-4	LONGVARBINARY 
-3	VARBINARY
-2	BINARY
-1	LONGVARCHAR
0	NULL
1	CHAR
2	NUMERIC
3	DECIMAL
4	INTEGER
5	SMALLINT
6	FLOAT
7	REAL
8	DOUBLE
12	VARCHAR
91	DATE
92	TIME
93	TIMESTAMP
1111 	OTHER
*/
