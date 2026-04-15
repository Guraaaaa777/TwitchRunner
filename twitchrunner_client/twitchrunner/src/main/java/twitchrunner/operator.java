package twitchrunner;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class operator {
	ObjectMapper mapper = new ObjectMapper();
	private target target;
	private String path = "src/main/resources/data.json";
	//data.json読み込み
	void readjson() {
		try {
			this.target = mapper.readValue(new File(path), target.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//data.json書き込み
	void writejson() {
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//登録
	void subscribe(String name) {
		for(streamer i:target.data) {
			if(i.name.equals(name)) {
				System.out.println("user subscribed");
				return;
			}
		}
		this.target.data.add(new streamer(name));
		writejson();
	}
	//登録解除
	void unsubscribe(String name) {
		this.target.data.removeIf(s -> s.name.equals(name));
		writejson();
	}
	operator(){
		readjson();
	}
	//表示
	void show() {
		for(streamer i:this.target.data) {
			System.out.print(i.name+" ");
		}
		System.out.println();
	}
}

class target{
	public ArrayList<streamer> data = new ArrayList<streamer>();
}
class streamer{
	public String name;
	private streamer() {}
	streamer(String name){
		this.name = name;
	}
	
}

