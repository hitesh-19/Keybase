package com.keybase.KeyBase.projects;

import java.util.Arrays;

import org.springframework.stereotype.Service;

@Service
public class Project {

	private String project_name;
	private String schema_name;
	private String[] tables;
	
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getSchema_name() {
		return schema_name;
	}
	public void setSchema_name(String schema_name) {
		this.schema_name = schema_name;
	}
	public String[] getTables() {
		return tables;
	}
	public void setTables(String[] tables) {
		this.tables = tables;
	}
	@Override
	public String toString() {
		return "ProjectStructure {project_name=" + project_name + ", schema_name=" + schema_name + ", tables="
				+ Arrays.toString(tables) + "}";
	}
}