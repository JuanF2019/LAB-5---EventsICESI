package model;
import java.util.*;
import java.time.*;

import java.time.LocalDateTime;

public class Event{	
	private String name;
	private String id;
	private LocalDateTime dateI;
	private LocalDateTime dateF;
	private Auditorium[] auditoriums;
	private String professor;
	private String faculty;
	private int estAssistant;
	
	
	
	/**Name: Event <br>
	 * Description: Constructor for class Event. <br>
	 * @param name String Event name.
	 * @param dateI LocalDateTime Start date and hour.
	 * @param dateF LocalDateTime End date and hour.
	 * @param auditoriums Auditorium[] Auditorium array.
	 * @param faculty String Encharged faculty.
	 * @param professor String Encharged professor.
	 * @param estAssistant int Estimated number of assistants.
	 * @param id String Event id.
	 */
	public Event(String name, LocalDateTime dateI, LocalDateTime dateF, Auditorium[] auditoriums, String faculty, String professor, int estAssistant, String id){
		this.name = name;
		this.id = id;
		this.dateI = dateI;
		this.dateF = dateF;
		this.auditoriums = new Auditorium[6];
		for (int i = 0; i < auditoriums.length; i++){
			this.auditoriums[i] = auditoriums[i];
		}
		this.professor = professor;
		this.faculty = faculty;
		this.estAssistant = estAssistant;
	}
	/**Name: getDateI<br>
	 * Description: Returns the start date and hour of an event.<br>
	 * <b>PRE: </b>A event must be created event != null.<br>
	 * @return dateI LocalDateTime Event start date and hour.
	 */
	public LocalDateTime getDateI() {
		return this.dateI;
	}
	/**Name: getDateF<br>
	 * Description: Returns the end date and hour of an event.<br>
	 * <b>PRE: </b>A event must be created event != null.<br>
	 * @return dateF LocalDateTime Event end date and hour.
	 */
	public LocalDateTime getDateF() {
		return this.dateF;
	}
	/**Name: getId<br>
	 * Description: Returns the Id of an event.<br>
	 * <b>PRE: </b>A event must be created event != null.<br>
	 * @return Id String Event Id.
	 */
	public String getId() {
		return this.id;
	}
	/**Name: getAuditoriums<br>
	 * Description: Returns an array with the event auditoriums.<br>
	 * <b>PRE: </b>A event must be created event != null.<br>
	 * @return auditoriums Auditorium[] Array with the event auditoriums.
	 */
	public Auditorium[] getAuditoriums() {
		return this.auditoriums;
	}
	/**Name: setAuditoriums<br>
	 * Description: Set the event auditoriums to the given auditoriums.<br>
	 * <b>PRE: </b>A event must be created event != null.<br>
	 * <b>POS: </b>Attribute auditoriums updated.
	 * @param auditoriums Auditorium[] Event auditoriums.
	 */
	public void setAuditoriums(Auditorium[] auditoriums) {
		this.auditoriums = auditoriums;
	}
	/**Name: getName<br>
	 * Description: Returns the name of the event.<br>
	 * <b>PRE: </b>A event must be created event != null.<br>
	 * @return name String Name of the event.
	 */
	public String getName() {
		return this.name;
	}
	/**Name: toString<br>
	 * Description: Returns a String with the event attributes.<br>
	 * <b>PRE: </b> A event must be created event != null.<br>	
	 * @return msg String with the information of all the event attributes.
	 */
	public String toString() {
		String msg = "";
		msg += "\n Event name: " + this.name + " Id: " + this.id + "\nFaculty: " + this.faculty + "\nProfessor: " + this.professor + "\nEstimated Assistants" + this.estAssistant + "\n Start date: " + this.dateI + "\n End date:" +  this.dateF;		
		return msg;		
	}
}