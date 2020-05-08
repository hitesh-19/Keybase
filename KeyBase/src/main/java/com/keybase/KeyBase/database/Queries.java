package com.keybase.KeyBase.database;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keybase.KeyBase.storage.dao.ColumnDao;

@Service
public class Queries {
	
	@Autowired
	ColumnDao columnDao;

	String selectAll(String api_type, String api_name, String schema_name, String table_name) {
		return "\t@GetMapping(\"/"+api_name+"\")\n"+
			"\tpublic List<JSONObject> "+api_type+schema_name+table_name+"Data() {\n"+
			"\t\tList<Map<String, Object>> list = jdbcTemplate.queryForList(\"select * from "+schema_name+"."+table_name+"\");\n"+
			"\t\tIterator<Map<String, Object>> iterator = list.iterator();\n"+
			"\t\tList<JSONObject> arrayList = new ArrayList<JSONObject>();\n"+
			"\t\twhile(iterator.hasNext()) {\n"+
			"\t\t\tJSONObject jsonObject = new JSONObject(iterator.next());\n"+
			"\t\t\tarrayList.add(jsonObject);\n"+
			"\t\t}\n"+
			"\t\treturn arrayList;\n"+
			"\t}\n";
	}
	
	String insert(String api_type, String api_name, String schema_name, String table_name) {
		
		String columns[] = {this.gettingColumnIndex(schema_name, table_name)[0],this.gettingColumnIndex(schema_name, table_name)[1]};
		System.out.println(columns[0]);
		System.out.println(columns[1]);
		
		return "\t@PostMapping(path = \"/"+api_name+"\", consumes = \"application/json\", produces = \"application/json\")\n"+
				"\tpublic String "+api_type+schema_name+table_name+"Data(@RequestBody JSONObject json) {\n"+
				"\t\tint i = jdbcTemplate.update(\"insert into users("+columns[0]+") values("+columns[1]+");\n"+
				"\t\tif(i==1)\n"+
				"\t\t\treturn \"Data added Successfully\";\n\n"+
				"\treturn \"Error occured\";\n"+
				"\t}\n";
	}
	
	String[] gettingColumnIndex(String schema_name, String table_name) {
		
		String columns[] = new String[2];
		columns[0] = "";
		columns[1] = "";
		
		List<String> list = this.columnDao.getColumns(schema_name, table_name);
		List<Integer> datatype_list = this.columnDao.getColumnsDatatype();
		
		int i = list.size();
		
		Iterator<String> iterator = list.iterator();
		Iterator<Integer> datatype_iterator = datatype_list.iterator();
		
		while(iterator.hasNext()) {
			String data = iterator.next();
			int datatype = datatype_iterator.next();
			
			columns[0] += data;
			
			if(datatype==12) {
				columns[1] += "'";
				columns[1] += "\"+json.get(\"";
				columns[1] += data;
				columns[1] += "\")+\"";
				columns[1] += "'";
			} else if(datatype==4) {
				columns[1] += "\"+json.get(\"";
				columns[1] += data;
				columns[1] += "\")+\"";
			}
			
			if(i>1) {
				columns[0] += ",";
				columns[1] += ",";
			} else if(i==1) {
				columns[1] += ")\"";
			}
			
			i--;
		}
		return columns;
	}
}
