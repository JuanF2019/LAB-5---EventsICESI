package model;
import java.util.*;
import java.time.*;
public class University{
	//CONSTANTS
	public static final int MINHOUR = 7;
	public static final int MAXHOUR = 20;
	public static final int MINTIME = 2;
	private final String[] audName = {"Manuelita","Sidoc","Cementos Argos","Ernesto Delima","Banco de Occidente","Varela"};
	private final String[] audLoc = {"Edificio Auditorios","Edificio Auditorios","Edificio Auditorios","Edificio L","Edificio E","Edificio D"};
	public final String idFormat = "Q00";
	public int partialId = 1;
	
	//ATTRIBUTES
	private String name;
	
	//RELATIONS
	private ArrayList<Event> events;
	private Auditorium[] auditoriums;
	
	
	//CONSTRUCTOR
	/**Name: University<br>
	 * Description: Constructor for controller class University.<br>
	 * @param name String university name
	 */
	public University(String name){
		this.name = name;
		events = new ArrayList<Event>();
		auditoriums = new Auditorium[6];
		
		for (int i = 0 ; i<auditoriums.length; i++){
			auditoriums[i] = new Auditorium(audName[i],Integer.toString(i), audLoc[i], Auditorium.EMPTY);
		}
		updateStatus(LocalDateTime.now());
	}
	/**Name: addEvent<br>
	 * Description: adds an Event to the university and to the corresponding auditorium.
	 * <b>PRE: </b>Auditoriums array must exist, auditoriums != null.<br>
	 * @param name String Event name.
	 * @param dateI LocalDateTime Event start date and hour.
	 * @param dateF LocalDateTime Event end date and hour.
	 * @param aud int[] with the id of the auditoriums the event has as an integer.
	 * @param faculty String encharged faculty.
	 * @param professor String encharged professor.
	 * @param estAssistants int estimated number of assistants.
	 * @return msg String with message of error or success.
	 */
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
			String idStr = idFormat + Integer.toString(partialId++);
			
			events.add(new Event(name,dateI,dateF,tempAuditoriums,faculty,professor,estAssistants,idStr));			
			for (int i = 0; i < tempAuditoriums.length;i++) {
				if(aud[i]!=-1) {
					tempEvents = auditoriums[aud[i]].getEvents();
					tempEvents.add(new Event(name,dateI,dateF,tempAuditoriums,faculty,professor,estAssistants,idStr));
					auditoriums[aud[i]].setEvents(tempEvents);
				}
			}			
			msg = "Event created succesfully";			
		}
		return msg;		
	}
	/**Name: removeEvent
	 * Description: Removes an event given its Id.
	 * <b>PRE: </b>Auditoriums array must exist, auditoriums != null.<br>
	 * @param id String event Id
	 * @return msg String with message of error or success.
	 */
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
	/**Name: findEventById.<br>
	 * Description: returns the position of an event in an ArrayList of events given its id and the events array. Returns -1 if no event was found.<br>
	 * <b>PRE: </b>Events ArrayList must exist, events != null.<br>
	 * @param id String event Id.
	 * @param tempEvents ArrayList of events.
	 * @return pos int Position of the event in the ArrayList.
	 */
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
	/**Name: findEventById.<br>
	 * Description: returns the position of an event in an ArrayList of events given its id. Returns -1 if no event was found.<br>
	 * <b>PRE: </b>Events ArrayList must exist, events != null.<br>
	 * @param id String event Id.
	 * @return pos int Position of the event in the ArrayList.
	 */
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
	/**Name: findAuditoriumById.<br>
	 * Description: returns the position of an auditorium in an array of auditoriums given its id. Returns -1 if no auditorium was found.<br>
	 * <b>PRE: </b>Auditoriums array must exist, auditoriums != null.<br>
	 * @param id String auditorium Id.
	 * @return pos int Position of the auditorium in the auditoriums array.
	 */
	public int findAuditoriumById(String id) {//Returns -1 if no auditorium was found
		int pos = -1;
		Auditorium tempAuditorium = null;
		boolean check = false;
		for(int i =0; i< auditoriums.length && !check;i++) {
			tempAuditorium = auditoriums[i];
			if ((tempAuditorium.getId()).equals(id)) {
				pos = i;
				check = true;
			}
		}
		return pos;
	}
	/**Name: toString<br>
	 * Description: Returns an String with the name and id of all the auditoriums.<br>
	 * <b>PRE: </b>Auditoriums array must exist, auditoriums != null.<br>
	 * @return msg String with the name and id of all the auditoriums.
	 */
	public String toString() {
		String msg = "";
		for(int i =0; i< auditoriums.length;i++) {
			msg += "Name: " + auditoriums[i].getName() + " Id: " + auditoriums[i].getId() + "\n";
		}
		return msg;
	}
	/**Name: toString<br>
	 * Description: Returns an String with the name, id and Events of all the auditoriums.<br>
	 * <b>PRE: </b>Auditoriums array must exist, auditoriums != null.<br>
	 * <b>PRE: </b>Events ArrayList must exist, events != null.<br>
	 * @return msg String with the name, id and events of all the auditoriums.
	 */
	public String toStringEvents() {
		String msg = "";
		for(int i =0; i< auditoriums.length;i++) {
			msg += auditoriums[i].toString();
		}
		return msg;
	}
	/**Name: updateStatus
	 * Description: Updates the status of all the auditoriums given the current set time.
	 * @param currentDate LocalDateTime current set time.
	 */
	public void updateStatus(LocalDateTime currentDate) {
		int pos = 0;
		Auditorium[] eventAuditoriums = null;
		for (int i =0; i< events.size();i++) {
			System.out.println(currentDate);
			if(events.get(i) != null && ((events.get(i)).getDateI().equals(currentDate) || (events.get(i)).getDateI().isBefore(currentDate)) && (events.get(i)).getDateF().isAfter(currentDate) ) {
				eventAuditoriums = (events.get(i)).getAuditoriums();
				for (int j= 0; j< eventAuditoriums.length ; j++) {
					if(eventAuditoriums[j] != null) {
						pos = Integer.parseInt(eventAuditoriums[j].getId());
						auditoriums[pos].fillChairs();
						auditoriums[pos].setStatus(Auditorium.FULL + " - " + (events.get(i)).getName() );
						eventAuditoriums[j] = auditoriums[pos];
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
	/**Name: reportChair
	 * Description: Set the status of a chair to damaged given its auditorium(Id), row and column.
	 * <b>PRE: </b>Auditoriums array must exist, auditoriums[any position] != null.<br>
	 * @param audId String id of the auditorium.
	 * @param row char row location
	 * @param column int column location
	 * @return msg String message of error or success.
	 */
	public String reportChair(String audId, char row, int column) {
		String msg = auditoriums[Integer.parseInt(audId)].reportChair(row, column);
		return msg;
	}
	/**Name: damagedChairs
	 * Description: gets the percentage of damaged chairs given the auditorium id.
	 * @param audId String Auditorium id
	 * @return percentage double Percentage of damaged chairs
	 */
	public double damagedChairs(String audId) {		
		double percentage = auditoriums[Integer.parseInt(audId)].damagedChairs();	
		return percentage;
	}
}