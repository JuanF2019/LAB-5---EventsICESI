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
	
	
	/**Name: Auditorium<br>
	 * Description: Constructor for class Auditorium.<br>
	 * @param name String Auditorium name.
	 * @param id String Auditorium id
	 * @param location Auditorium location in the university.
	 * @param status Auditorium initial status.
	 */
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
	/**Name: addEvent<br>
	 * Description: Adds an event to the auditorium ArrayList of events.<br>
	 * <b>PRE: </b>ArrayList of events must be created events != null.<br>
	 * <b>POS: </b>Event added to the events ArrayList.<br>
	 * @param event Object of class event event !=null.
	 */
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
	/**Name: removeEvent<br>
	 * Description: Removes an event given its id<br>
	 * <b>PRE: </b>ArrayList of events must be created events != null.<br>
	 * <b>POS: </b>Event removed from the events ArrayList.<br>
	 * @param id String id of the event to remove.
	 */
	public void removeEvent(String id){
		boolean check = false;
		for (int i = 0;i<events.size() && !check;i++){
			if (events.get(i)!=null&&((events.get(i)).getId()).equals(id)){
				events.remove(i);
			}			
		}
	}
	/**Name: getEventsInfo<br>
	 * Description: Adds to a String the information of all the events <br>
	 * that the auditorium has, keep the string as "No events yet" if there are no events.<br>
	 * @return msg String with the events information.
	 */
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
	/**Name: toString<br>
	 * Description: Returns a String with the auditorium attributes including its events, except its chairs.<br>
	 * <b>PRE: </b> A auditorium must be created auditorium != null.<br>	
	 * @return msg String with the auditorium attributes.
	 */
	public String toString() {
		String msg = "";
		msg = ("\nName: " + this.name + " Id: " + this.id + "\n" + "Events: " + getEventsInfo() + "Location: " + this.location + "\n" + "Status: " + this.status + "\n\n");
		return msg;		
	}
	/**Name: fillChairs<br>
	 * Description: Randomly fills the chairs of the auditorium, except the ones reported as damaged.<br>
	 * <b>PRE: </b>Chairs matrix must exist chairs != null<br>
	 * <b>PRE: </b>A auditorium must be created auditorium != null.<br>
	 * <b>POS: </b>Chairs status randomly changed.<br>
	 */
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
	/**Name: emptyChairs<br>
	 * Description: Sets the status of all the chairs of the auditorium to empty, except the ones reported as damaged.<br>
	 * <b>PRE: </b>Chairs matrix must exist chairs != null<br>
	 * <b>PRE: </b>A auditorium must be created auditorium != null.<br>
	 * <b>POS: </b>Chairs status changed.<br>
	 */
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
	/**Name: getEvents<br>
	 * Description: Returns the events ArrayList of the auditorium.<br>
	 * <b>PRE: </b>ArrayList of events must exist events != null.<br>
	 * <b>PRE: </b>An auditorium must be created auditorium != null.<br>
	 * @return events ArrayList of events
	 */
	public ArrayList<Event> getEvents(){
		return this.events;
	}
	/**Name: setEvents
	 * Description: Sets the auditorium events to the given one.
	 *<b>PRE: </b>ArrayList of events must exist events != null.<br>
	 *<b>PRE: </b>An auditorium must be created auditorium != null.<br>
	 *<b>POS: </b>Auditorium events changed
	 *@param events ArrayList events to set to the auditorium.
	 */
	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}
	/**Name: getId
	 * Description: Returns an auditorium Id.
	 * <b>PRE: </b>An auditorium must be created auditorium != null.<br>
	 * @return id Auditorium Id.
	 */
	public String getId() {
		return this.id;
	}
	/**Name: getName
	 * Description: Returns an auditorium name.
	 * <b>PRE: </b>An auditorium must be created auditorium != null.<br>
	 * @return name Auditorium name.
	 */
	public String getName() {
		return this.name;
	}
	/**Name: setStatus<br>
	 * Description: Set the auditorium status to the given value.<br>
	 * <b>PRE: </b>A must must be created auditorium != null.<br>
	 * <b>POS: </b>Attribute status updated.<br>
	 * @param status String auditorium status.
	 */
	public void setStatus(String status) {
		this.status = status;		
	}
	/**Name: reportChair<br>
	 * Description: Changes a chair status to damaged given its location in an auditorium
	 * <b>PRE: </b>An auditorium must be created auditorium != null.<br>
	 * <b>PRE: </b>Chairs matrix must exist chairs != null<br>
	 * <b>POS: </b>Updates status of the given chair to damaged.
	 * @param row char row letter.
	 * @param column int column number.
	 * @return msg String message of error or success.
	 */
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
	/**Name: damagedChairs
	 * Description: calculates and returns the percentage of damaged chairs.
	 * <b>PRE: </b>Chairs matrix must exist chairs != null<br>
	 * <b>PRE: </b>An auditorium must be created auditorium != null.<br>	 
	 * @return percentage int percentage of damaged chairs in the auditorium.
	 */
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