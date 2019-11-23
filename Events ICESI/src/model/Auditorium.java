package model;
import java.util.*;
import java.time.*;

public class Auditorium{
	public final static String EMPTY = "Empty";
	public final static String FULL = "Full";
	public char[] rows;
	private String name;
	private ArrayList<Event> events;
	private String id;
	private String location;
	private String status;
	private Chair[][] chairs;
	
	
	
	public Auditorium(String name, String id, String location, String status){
		this.name = name;
		events = new ArrayList<Event>();
		this.id = id;
		this.location = location;
		this.status = status;
		int rows = (int)((Math.random() * 15) + 1);
		chairs = new Chair[rows][20];
		for (int i = 0; i < chairs.length; i++ ) {
			for(int j = 0 ; j < chairs[0].length && j < (int)((Math.random() * 20) + 1) ; j ++) {
				chairs[i][j] = new Chair(Chair.EMPTY);
			}
		}
		this.rows = new char[rows];
		for ( int i=0; i<this.rows.length; i++) {
			this.rows[i] = (char)('A' + i );
		}
	}
	
	public void addEvent(Event event){
		boolean check = false;
		for (int i = 0;i<events.size() && !check;i++){
			if (events.get(i)!=null){
				events.add(i,event);
				check = true;
			}			
		}
		if (!check){
			events.add(event);
		}
	}
	
	public void removeEvent(String id){
		boolean check = false;
		for (int i = 0;i<events.size() && !check;i++){
			if (events.get(i)!=null&&((events.get(i)).getId()).equals(id)){
				events.remove(i);
			}			
		}
	}
	
	public String getEventsInfo() {
		String msg = "No events yet \n";
		for (int i = 0; i<events.size(); i++) {
			if (events.get(i)!=null && i == 0) {
				msg = (events.get(i)).toString() + "\n";
			}
			if (events.get(i)!=null && i!=0) {
				msg += (events.get(i)).toString() + "\n";
			}
		}
		return msg;
	}
	
	public String toString() {
		String msg = "";
		msg = ("\nName: " + this.name + " Id: " + this.id + "\n" + "Events: " + getEventsInfo() + "Location: " + this.location + "\n" + "Status: " + this.status + "\n\n");
		return msg;		
	}
	
	public void fillChairs() {
		int ran = 0;
		for (int i = 0; i<chairs.length; i++) {
			for (int j = 0; j < chairs[0].length; j++ ) {
				if(chairs[i][j] != null && chairs[i][j].getStatus() != Chair.DAMAGED) {
					ran = (int)Math.random()*2 + 1;					
					if (ran == 1) {
						chairs[i][j].setStatus(Chair.OCUPIED);
					}
					else {
						chairs[i][j].setStatus(Chair.EMPTY);
					}
				}
			}
		}
	}
	
	public void emptyChairs() {
		for (int i = 0; i<chairs.length; i++) {
			for (int j = 0; j < chairs[0].length; j++ ) {
				if(chairs[i][j] != null) {
					if (chairs[i][j].getStatus().equals(Chair.OCUPIED)) {
						chairs[i][j].setStatus(Chair.EMPTY);
					}					
				}
			}
		}
	}
	
	public ArrayList<Event> getEvents(){
		return this.events;
	}
	
	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

	public void setStatus(String status) {
		this.status = status;		
	}

	public String reportChair(char row, int column) {
		String msg = "Chair not found";
		for(int i = 0; i< rows.length;i++) {
			if (rows[i] == row && chairs[i][column] != null) {
				chairs[i][column].setStatus(Chair.DAMAGED);
				msg = "Chair reported succesfully!";
			}
		}
		return msg;
	}
	
	public double damagedChairs() {
		double percentage = 0;
		int damagedCount = 0;
		int total = 0;
		for (int i = 0; i<chairs.length; i++) {
			for (int j = 0; j < chairs[0].length; j++ ) {
				if(chairs[i][j] != null) {
					total++;
					if(chairs[i][j].getStatus().equals(Chair.DAMAGED)) {
						damagedCount++;
					}
				}
			}
		}
		percentage = (damagedCount/total)*100;
		return percentage;
	}
}