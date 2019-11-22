public class Auditorium{
	private String name;
	private ArrayList<Event> events;
	
	public Auditorium(String name){
		this.name = name;
		events = new ArrayList<Event>();		
	}
	
	public void addEvent(Event event){
		boolean check = false;
		int i = 0;
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
	
	public void removeEvent(){
		boolean check = false;
		for (int i = 0;i<events.size() && !check;i++){
			if (events.get(i)!=null&&((events.get(i)).getId()).equal(id)){
				events.remove(i);
			}			
		}
	}

}