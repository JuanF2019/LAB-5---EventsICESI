public class University{
	//ATTRIBUTES
	private String name;
	
	//RELATIONS
	private ArrayList<Event> events;
	private Auditorium[] auditoriums;
	private final String[] audName = {"Manuelita","Sidoc","Cementos Argos","Ernesto Delima","Banco de Occidente","Varela"};
	
	//CONSTRUCTOR
	public University(String name){
		this.name = name;
		events = new ArrayList<Event>;
		auditoriums = new Auditorium[6];
		
		for (int i = 0 ; i<auditoriums.length; i++){
			auditoriums[i] = new Auditorium(audName[i]);
		}
	}
	
	public String addEvent(String name, LocalDateTime dateI, LocalDateTime dateF, int[] aud){//Pasar nombre fecha y auditorios, verificar que la fecha no este ocupada
		String msg = "Error, event not created."
		boolean check = false;
		LocalDateTime date = null;
		for (int i = 0; i<aud.length; i++){
			for (int i = 0; i < events.size() ; i++){
				date = (events.get(i)).getDate();
				if (dateI.isAfter(date) && dateF.isBefore(date)){
					check = true;				
				}
			}
		}
		if (!check){
			
		}
		return msg;
		
	}
	
	public String removeEvent(){
		String msg = "Error, event not found."
		
	}	
}