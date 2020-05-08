package com.keybase.KeyBase.database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/api")
public class Api {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@GetMapping("/user-data")
	public List<JSONObject> selectmessenger_applicationusersData() {
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from messenger_application.users");
		Iterator<Map<String, Object>> iterator = list.iterator();
		List<JSONObject> arrayList = new ArrayList<JSONObject>();
		while(iterator.hasNext()) {
			JSONObject jsonObject = new JSONObject(iterator.next());
			arrayList.add(jsonObject);
		}
		return arrayList;
	}
}