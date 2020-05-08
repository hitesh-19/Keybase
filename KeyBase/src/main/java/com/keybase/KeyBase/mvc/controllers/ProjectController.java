package com.keybase.KeyBase.mvc.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.keybase.KeyBase.projects.ApiKey;
import com.keybase.KeyBase.projects.Project;
import com.keybase.KeyBase.storage.dao.ApiDao;
import com.keybase.KeyBase.storage.dao.ProjectDao;
import com.keybase.KeyBase.storage.dao.TableDao;

@Controller
public class ProjectController {
	
	@Autowired
	Project project;
	@Autowired
	TableDao tableDao;
	@Autowired
	ProjectDao projectDao;
	@Autowired
	ApiDao apiDao;
	@Autowired
	MenuProject menuProject;
	
	private String table_name;

	@RequestMapping("/")
	public String get_Started() {
		return "Get_started"; 
	}
	
	@RequestMapping("/project-tabs")
	public String project_Tabs(Model model) {
		List<Project> allProjects = this.projectDao.retriveAllProjects();
		model.addAttribute("projects",allProjects);
		return "Project_tab";
	}
	
	@RequestMapping("/add-project")
	public String all_Projects() {
		return "Add_Project";
	}
	
	@RequestMapping(value="/menu-create", method=RequestMethod.POST)
	public String create_project(@ModelAttribute Project recieve_Project, Model model) {
		project.setProject_name(recieve_Project.getProject_name());
		project.setSchema_name(recieve_Project.getSchema_name());
		List<String> table_list = tableDao.getDatabaseTables(recieve_Project.getSchema_name());
		Iterator<String> iterator = table_list.iterator();
		String[] tables = new String[table_list.size()];
		int i = 0;
		while(iterator.hasNext()) {
			tables[i] = iterator.next();
			i++;
		}
		project.setTables(tables);
		project = projectDao.saveProject(project);
		menuProject.setProject(project);
		model.addAttribute("project",menuProject);
		menuProject.setApikeys(null);
		model.addAttribute("apikey",menuProject.getApikeys());
		return "Menu";
	}
	@RequestMapping(value="/menu-login")
	public String log_Project(@RequestParam(value = "project_name", required = false) String project_name, Model model) {
		menuProject.setProject(projectDao.retriveSingleProject(project_name));
		menuProject.setApikeys(apiDao.retriveProjectApi(project_name));
		model.addAttribute("project",menuProject);
		model.addAttribute("apikey",menuProject.getApikeys());
		return "Menu";
	}
	
	@RequestMapping(value="/select-table")
	public void select_table(@ModelAttribute String table_name) {
		this.table_name = table_name;
	}
	
	@RequestMapping(value="/create-api")
	public String create_Api(@ModelAttribute ApiKey apiKey, Model model) {
		apiKey.setProject_name(menuProject.getProject().getProject_name());
		apiKey.setSchema_name(menuProject.getProject().getSchema_name());
		apiKey.setTable_name(table_name);
		model.addAttribute("project",menuProject);
		model.addAttribute("apikey",menuProject.getApikeys());
		return "Menu";
	}
}
