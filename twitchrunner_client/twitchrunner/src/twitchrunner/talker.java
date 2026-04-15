package twitchrunner;

import java.util.ArrayList;
import java.util.Scanner;

public class talker {
	talker(){
		operator operator = new operator();
		System.out.println("choose action\nshow : show subscribed channel\nsub : subscribe new channel\nunsub : unsubscribe channel\nend : close this console");
		boolean end = false;
		Scanner sc = new Scanner(System.in);
		while(!end) {
			System.out.print("input : ");
			switch(sc.nextLine()) {
		        case "show":
		        	operator.show();
		        	break;
		        case "sub":
		        	System.out.print("input channel name : ");
		        	operator.subscribe(sc.nextLine());
		        	break;
		        case "unsub":
		        	System.out.print("input channel name : ");
		        	operator.unsubscribe(sc.nextLine());
		        	break;
		        case "end":
		        	end = true;
		        	break;
		        default:
		        	System.out.println("command undefined");
		        	
		        	break;
	        }
		}
		sc.close();
	}
}
