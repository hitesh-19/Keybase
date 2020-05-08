package com.keybase.KeyBase.projects;

import org.springframework.stereotype.Service;

@Service
public class ApiKey {
	
	private String key_title;
	private String api_key;
	private String table_name;
	private String schema_name;
	private String project_name;
	String allApiKeys;
	
	public String getKey_title() {
		return key_title;
	}

	public void setKey_title(String key_title) {
		this.key_title = key_title;
	}

	public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getSchema_name() {
		return schema_name;
	}

	public void setSchema_name(String schema_name) {
		this.schema_name = schema_name;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	@Override
	public String toString() {
		return "ApiKey {key_title=" + key_title + ", api_key=" + api_key + ", table_name=" + table_name
				+ ", schema_name=" + schema_name + ", project_name=" + project_name + "}";
	}
}