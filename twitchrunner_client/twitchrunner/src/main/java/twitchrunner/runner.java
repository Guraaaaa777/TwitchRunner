package twitchrunner;

import java.util.ArrayList;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.*;
public class runner {
	public ArrayList<streamer> data = new ArrayList<streamer>();
	public void setid() {
		for(streamer i:data) {
			i.setid();
		}
	}
	public void run() {
		while(true) {
			for(streamer i:data) {
				i.checkstream();
			}
		}
	}
}
class streamer{
	public String name;
	private int userid;
	private boolean stream = false;
	
	public void checkstream() {
		boolean now = requestor.getstream(this.userid);
		if(this.stream != now) {
			if(this.stream==false) {
				//open browser
				System.out.println(this.name+"start live");
				try {
					Desktop.getDesktop().browse(new URI("https://twitch.tv/"+this.name));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.stream=now;
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		System.out.println("name:"+this.name+" id:"+this.userid+"stream:"+this.stream);
	}
	public void setid() {
		this.userid = requestor.getid(this.name);
	}
	private streamer() {
	}
}