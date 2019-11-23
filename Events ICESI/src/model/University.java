package model;
import java.util.*;
import java.time.*;
public class University{
	//CONSTANTS
	public static final int MINHOUR = 7;
	public static final int MAXHOUR = 20;
	public static final int MINTIME = 2;
	//ATTRIBUTES
	private String name;
	
	//RELATIONS
	private ArrayList<Event> events;
	private Auditorium[] auditoriums;
	private final String[] audName = {"Manuelita","Sidoc","Cementos Argos","Ernesto Delima","Banco de Occidente","Varela"};
	private final String[] audLoc = {"Edificio Auditorios","Edificio Auditorios","Edificio Auditorios","Edificio L","Edificio E","Edificio D"};
	
	//CONSTRUCTOR
	public University(String name){
		this.name = name;
		events = new ArrayList<Event>();
		auditoriums = new Auditorium[6];
		
		for (int i = 0 ; i<auditoriums.length; i++){
			auditoriums[i] = new Auditorium(audName[i],Integer.toString(i), audLoc[i], Auditorium.EMPTY);
		}
		updateStatus(LocalDateTime.now());
	}
	
	public String addEvent(String name, LocalDateTime dateI, LocalDateTime dateF, int[] aud, String faculty, String professor, int estAssistants){ 
		String msg = null;
		boolean check = false;
		LocalDateTime tempDateI = null;
		LocalDateTime tempDateF = null;
		ArrayList<Event> tempEvents = null;
		
		for (int i = 0; i<aud.length; i++){
			if(aud[i]!=-1) {
				tempEvents = auditoriums[aud[i]].getEvents();
				for (int z = 0; z < tempEvents.size() ; z++){
					if(tempEvents.get(z)!=null) {
						tempDateI = (tempEvents.get(z)).getDateI();
						tempDateF = (tempEvents.get(z)).getDateF();
						if (dateI.isAfter(tempDateI) && dateI.isBefore(tempDateF) || (dateI.isBefore(tempDateI) && dateF.isAfter(tempDateF))){
							check = true;		
						}
					}
				}
			}
		}
		if (!check){
			Auditorium[] tempAuditoriums = new Auditorium[aud.length];
			
			for (int i = 0; i < tempAuditoriums.length;i++) {
				if(aud[i]!=-1) {
					tempAuditoriums[i] = auditoriums[aud[i]];
				}
				if (tempAuditoriums[i]!=null) {
					System.out.println(tempAuditoriums[i].getName());
				}
			}
			
			events.add(new Event(name,dateI,dateF,tempAuditoriums,faculty,professor,estAssistants));			
			for (int i = 0; i < tempAuditoriums.length;i++) {
				if(aud[i]!=-1) {
					tempEvents = auditoriums[aud[i]].getEvents();
					tempEvents.add(new Event(name,dateI,dateF,tempAuditoriums,faculty,professor,estAssistants));
					auditoriums[aud[i]].setEvents(tempEvents);
				}
			}			
			msg = "Event created succesfully";			
		}
		return msg;		
	}
	
	public String removeEvent(String id){
		String msg = "Error, event not found.";
		int pos = findEventById(id);
		int pos2 = 0;
		ArrayList<Event> tempEvents = null;
		Auditorium[] tempAuditoriums = null;
		if (pos!=-1) {
			tempAuditoriums = (events.get(pos)).getAuditoriums();
			for (int i = 0; i < tempAuditoriums.length;i++) {
				pos2 = findAuditoriumById(tempAuditoriums[i].getId());
				tempEvents = auditoriums[pos2].getEvents();
				
				pos2 = findEventById(id,tempEvents);
				tempEvents.remove(pos2);
			}
			events.remove(pos);
			msg = "Event removed succesfully";
		}
		return msg;
		
	}	
	
	public int findEventById(String id, ArrayList<Event> tempEvents) {//Returns -1 if no event was found
		int pos = -1;
		boolean check = false;
		Event tempEvent = null;
		for(int i =0; i< tempEvents.size() && !check;i++) {
			tempEvent = tempEvents.get(i);
			if (tempEvent.getId().equals(id)) {
				pos = i;
				check = true;
			}
		}
		return pos;
	}
	
	public int findEventById(String id) {//Returns -1 if no event was found
		int pos = -1;
		Event tempEvent = null;
		boolean check = false;
		for(int i =0; i< events.size() && !check;i++) {
			tempEvent = events.get(i);
			if (tempEvent.getId().equals(id)) {
				pos = i;
				check = true;
			}
		}
		return pos;
	}
	
	public int findAuditoriumById(String id) {//Returns -1 if no auditorium was found
		int pos = -1;
		Event tempAuditorium = null;
		boolean check = false;
		for(int i =0; i< auditoriums.length && !check;i++) {
			tempAuditorium = events.get(i);
			if ((tempAuditorium.getId()).equals(id)) {
				pos = i;
				check = true;
			}
		}
		return pos;
	}
	
	public String toString() {
		String msg = "";
		for(int i =0; i< auditoriums.length;i++) {
			msg += "Name: " + auditoriums[i].getName() + " Id: " + auditoriums[i].getId() + "\n";
		}
		return msg;
	}
	
	public String toStringEvents() {
		String msg = "";
		for(int i =0; i< auditoriums.length;i++) {
			msg += auditoriums[i].toString();
		}
		return msg;
	}
	
	public void updateStatus(LocalDateTime currentDate) {
		int pos = 0;
		Auditorium[] eventAuditoriums = null;
		for (int i =0; i< events.size();i++) {
			System.out.println(currentDate);
			if(events.get(i) != null && ((events.get(i)).getDateI().equals(currentDate) || (events.get(i)).getDateI().isBefore(currentDate)) && (events.get(i)).getDateF().isAfter(currentDate) ) {
				eventAuditoriums = (events.get(i)).getAuditoriums();
				for (int j= 0; j< eventAuditoriums.length ; j++) {
					if(eventAuditoriums[i] != null) {
						pos = Integer.parseInt(eventAuditoriums[i].getId());
						auditoriums[pos].fillChairs();
						auditoriums[pos].setStatus(Auditorium.FULL + " - " + (events.get(i)).getName() );
						eventAuditoriums[i] = auditoriums[pos];
						(events.get(i)).setAuditoriums(eventAuditoriums);
					}
				}			
			}
			else {
				eventAuditoriums = (events.get(i)).getAuditoriums();
				for (int j= 0; j< eventAuditoriums.length ; j++) {
					if(eventAuditoriums[i] != null) {
						pos = Integer.parseInt(eventAuditoriums[i].getId());
						auditoriums[pos].emptyChairs();
						auditoriums[pos].setStatus(Auditorium.EMPTY);
						eventAuditoriums[i] = auditoriums[pos];
						(events.get(i)).setAuditoriums(eventAuditoriums);
					}
				}
			}
			eventAuditoriums = null;
		}		
	}
	
	public String reportChair(String audId, char row, int column) {
		String msg = auditoriums[Integer.parseInt(audId)].reportChair(row, column);
		return msg;
	}
	
	public double damagedChairs(String audId) {		
		double percentage = auditoriums[Integer.parseInt(audId)].damagedChairs();	
		return percentage;
	}
}