public class Event{
	private String name;
	private String id;
	private LocalDate dateI;
	private LocalDate dateF;
	private Auditorium[] auditoriums;
	public final String idFormat = "Q00"
	public int partialId = 1
	
	public Event(String name, LocalDateTime dateI, LocalDateTime dateF, Auditorium[] auditoriums){
		this.name = name;
		this.id = idFormat + Integer.toString(partialId++);
		this.dateI = dateI;
		this.dateF = dateF;
		this.auditoriums = new Auditorium[6];
		for (int i = 0; i < auditoriums.length; i++){
			this.auditoriums[i] = auditoriums[i];
		}
	}
}