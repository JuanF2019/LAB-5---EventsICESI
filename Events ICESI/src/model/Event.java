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
	public final String idFormat = "Q00";
	public int partialId = 1;
	
	
	public Event(String name, LocalDateTime dateI, LocalDateTime dateF, Auditorium[] auditoriums, String professor, String faculty, int estAssistant){
		this.name = name;
		this.id = idFormat + Integer.toString(partialId++);
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

	public LocalDateTime getDateI() {
		return this.dateI;
	}
	
	public LocalDateTime getDateF() {
		return this.dateF;
	}
	
	public String getId() {
		return this.id;
	}
	
	public Auditorium[] getAuditoriums() {
		return this.auditoriums;
	}
	
	public void setAuditoriums(Auditorium[] auditoriums) {
		this.auditoriums = auditoriums;
	}

	public String getName() {
		return this.name;
	}
	
	public String toString() {
		String msg = "";
		msg += "\n Event name: " + this.name + " Id: " + this.id + "\nFaculty: " + this.faculty + "\nProfessor: " + this.professor + "\nEstimated Assistants" + this.estAssistant + "\n Start date: " + this.dateI + "\n End date:" +  this.dateF;		
		return msg;
		
	}
}