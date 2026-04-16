package twitchrunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class generator {
	ObjectMapper mapper = new ObjectMapper();
	runner runner = new runner();
	private String path = "src/main/resources/data.json";
	void readjson() {
		try {
			this.runner = mapper.readValue(new File(path), runner.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	generator(){
		requestor.setserver();
		readjson();
	}
}
