package twitchrunner_background;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class requestor {
	private static server server = new server();
	public static void setserver() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			server = mapper.readValue(new File("src/main/resources/server.json"), server.class);
		} catch (IOException e) {e.printStackTrace();}
		System.out.println("ip:"+server.serveripaddress+" port:"+server.serverport);
	}
	
	//id取得
	public static int getid(String name) {
		ObjectMapper mapper = new ObjectMapper();
		HttpClient client = HttpClient.newHttpClient();
		
        HttpRequest request = HttpRequest.newBuilder()
        		.uri(URI.create("http://"+server.serveripaddress+":"+server.serverport+"/getid"))
                .header("Content-Type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.ofString("{\"name\":\""+name+"\"}"))
                .build();
        try {
        	HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());        	
        	JsonNode root = mapper.readTree(response.body());
            return root.get("id").asInt();
		} catch (IOException | InterruptedException e) {e.printStackTrace();}
		
        return 0;
	}
	public static boolean getstream(int id) {
		ObjectMapper mapper = new ObjectMapper();
		HttpClient client = HttpClient.newHttpClient();
		
        HttpRequest request = HttpRequest.newBuilder()
        		.uri(URI.create("http://"+server.serveripaddress+":"+server.serverport+"/getstream"))
                .header("Content-Type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.ofString("{\"id\":\""+id+"\"}"))
                .build();
        try {
        	HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());        	
        	JsonNode root = mapper.readTree(response.body());
            return root.get("stream").asBoolean();
		} catch (IOException | InterruptedException e) {e.printStackTrace();}
		return false;
	}
	requestor(){
	}
}
class server {
	public String serveripaddress;
	public int serverport;
}
