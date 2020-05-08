package com.keybase.KeyBase.mvc.controllers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keybase.KeyBase.projects.ApiKey;
import com.keybase.KeyBase.projects.Project;

@Service
public class MenuProject {

	private Project project;
	private List<ApiKey> apikeys;

	public void setProject(Project project) {
		this.project = project;
	}

	public void setApikeys(List<ApiKey> apikeys) {
		this.apikeys = apikeys;
	}

	public Project getProject() {
		return project;
	}

	public List<ApiKey> getApikeys() {
		return apikeys;
	}
}
