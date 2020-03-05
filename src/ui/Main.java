package ui;
import java.util.*;
import java.time.*;
import model.*;

public class Main{
	private Scanner inputNum;
	private Scanner inputStr;
	private University university;
	private LocalDateTime currentDate;
	/**Name: Main<br>
	 * Description: Constructor for main class Main<br>
	 */
	public Main(){
		university = new University("ICESI");
		LocalDateTime currentDate = LocalDateTime.now();
	}
	/**Name: main
	 * Description: Executable static main method 
	 * @param args arguments
	 */
	public static void main(String[] args){
		Main obj = new Main();
		Scanner inputNum = new Scanner(System.in);
		Scanner inputStr = new Scanner(System.in);
		boolean check = false;
		boolean menu = true;
		int menuOpt = 0;
		obj.setCurrentDate(LocalDateTime.now());
		System.out.println("Welcome to ICESI event system.");
		System.out.println("Note: Chairs will be randomly generated for each auditorium");
		while (menu){
			System.out.println("1. Add event");
			System.out.println("2. Remove event");
			System.out.println("3. Report damaged chair.");
			System.out.println("4. See porcentage of damaged chairs in a given auditorium.");
			System.out.println("5. Set current time (The system will automatically set auditorium status to in use if it applies to the given date.)");
			System.out.println("6. View auditoriums and its events(date and time)");
			System.out.println("7. Exit");
			System.out.println("Type the number corresponding to action you want to proceed with: ");
			menuOpt = inputNum.nextInt();
			switch(menuOpt){    
				case 1:
					obj.addEvent();
				break;
				case 2:
					obj.removeEvent();
				break;
				case 3:
					obj.reportChair();
				break;
				case 4:
					obj.damagedChairs();
				break;
				case 5:
					obj.updateStatus();
				break;
				case 6: 
					obj.toStringAuditoriums();
				break;
				case 7:
					System.out.println("Closing program");
					menu = false;
				break;
				default: 
					System.out.println("Invalid option, type a valid one!");
			} 
		}		
	}
	/**Name: addEvent
	 * Description: Interacts with the user to add an Event
	 */
	public void addEvent() {
		inputNum = new Scanner(System.in);
		inputStr = new Scanner(System.in);
		boolean check = false;
		boolean opt = true;
		int[] selAudArr = new int[6];
		for(int i =0; i<selAudArr.length; i++) {
			selAudArr[i] = -1;
		}
		LocalDateTime dateI = null;
		LocalDateTime dateF = null;
		String name = "";
		String faculty = "";
		String professor = "";
		int estAssistants = 0;
		int monthI = 0;
		int dayI = 0;
		int hourI = 0;
		int monthF = 0;
		int dayF = 0;
		int hourF = 0;
		int selAud = 0;
				
		System.out.println("Type event name");
		name = inputStr.nextLine();
		System.out.println("Type encharged faculty");
		faculty = inputStr.nextLine();
		System.out.println("Type encharged professor");
		professor = inputStr.nextLine();
		System.out.println("Type estimated number of assitants");
		estAssistants = inputNum.nextInt();
		do {
			System.out.println("Type event start month in number format");
			monthI = inputNum.nextInt();
			if (monthI <1 || monthI >12) {
				System.out.println("Invalid Month!");
			}
		}while(monthI <1 || monthI >12);
		
		
		
		do {
			System.out.println("Type event start day of the month");
			dayI = inputNum.nextInt();
			if (dayI <1 || dayI >31) {
				System.out.println("Invalid Day!");
			}
			else if ((monthI == 1 || monthI == 3 || monthI == 5 || monthI == 7 || monthI == 8 || monthI == 10 || monthI == 12) && dayI == 31) {
				check = true;
			}
			else if ( ((isLeap(((LocalDateTime.now()).getYear()))) && monthI == 2 && dayI > 29)  || (!(isLeap(((LocalDateTime.now()).getYear()))) && monthI == 2 && dayI > 28)) {
				System.out.println("Invalid Day!");
			}
			else {
				check = true;
			}
		} while(!check);
		check = false;
		
		do {
			System.out.println("Type event start hour");
			hourI = inputNum.nextInt();
			if(hourI < University.MINHOUR || hourI > University.MAXHOUR-2) {//Por minimo evento de dos horas
				System.out.println("Invalid start hour!");
			}		
		}while(hourI < University.MINHOUR || hourI > University.MAXHOUR-2);				
		
		
		do {
			System.out.println("Type event end month in number format");
			monthF = inputNum.nextInt();
			if (monthF <1 || monthF >12 || monthF < monthI) {
				System.out.println("Invalid Month!");
			}
		}while(monthI <1 || monthI >12);
		
		do {
			System.out.println("Type event end day");
			dayF = inputNum.nextInt();
			if (dayF <1 || dayF >31) {
				System.out.println("Invalid Day!");
			}
			else if(monthI == monthF && dayF < dayI) {
				System.out.println("Invalid Day!");
			}
			else if ((monthF == 1 || monthF == 3 || monthF == 5 || monthF == 7 || monthF == 8 || monthF == 10 || monthF == 12) && dayF == 31) {
				check = true;
			}
			else if ( ((isLeap(((LocalDateTime.now()).getYear()))) && monthF == 2 && dayF > 29)  || (!(isLeap(((LocalDateTime.now()).getYear()))) && monthF == 2 && dayF > 28)) {
				System.out.println("Invalid Day!");
			}
			else {
				check = true;
			}
		} while(!check);
		check = false;
		
		
		do {
			System.out.println("Type event end hour time");
			hourF = inputNum.nextInt();	
			if(hourF < University.MINHOUR || hourF > University.MAXHOUR || hourF < hourI + 2) {
				System.out.println("Invalid end hour!");
			}		
		}while(hourF < University.MINHOUR || hourF > University.MAXHOUR || hourF < hourI + 2);
						
		for(int i = 0; i < 6 &&!check ; i++) {
			check = false;
			System.out.println("Select auditorium: ");
			System.out.println(university.toString());
			selAud = inputNum.nextInt();
			
			for(int z=0 ; z < selAudArr.length && !check; z++) {
				if (selAudArr[i]==-1 ) {
					selAudArr[i] =  selAud;
					check = true;
				}
			}
			check = false;
			
			while (opt) {
				System.out.println("Do you want to select another auditorium? Type (y) or (n)");
				switch(inputStr.nextLine()) {
				case "y":
					check = false;
					opt = false;
				break;
				case "n":
					check = true;
					opt = false;
				break;
				default:
					System.out.println("Invalid Response!");
				}
			}
			opt = true;
		}	
		
		dateI = LocalDateTime.of(((LocalDateTime.now()).getYear()), monthI, dayI, hourI, 0);
		dateF = LocalDateTime.of(((LocalDateTime.now()).getYear()), monthF, dayF, hourF-1, 59);
		String str = university.addEvent(name, dateI, dateF, selAudArr, faculty,professor,estAssistants);
		
		if (str != null) {
			System.out.println(str);
			System.out.println("Start date: " + dateI + "\n" + "End date: " + dateF);
		}
		else {
			System.out.println("Could not create event!");
		}		
		university.updateStatus(this.currentDate);		
	}
	/**Name: addEvent
	 * Description: Interacts with the user to remove an Event
	 */
	public void removeEvent() {
		inputStr = new Scanner(System.in);
		String id = "";
		System.out.println("Type event id");
		id = inputStr.nextLine();
		System.out.println(university.removeEvent(id));		
	}
	/**Name: isLeap
	 * Description: check if a given year is leap.
	 * @param year int Year to check
	 * @return isLeap True or false depending if the year is leap or not.
	 */
	public boolean isLeap(int year) {
		boolean isLeap = false; 		
		if(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
			isLeap = true;
		}		
	    return isLeap;
	}
	/**Name: updateStatus
	 * Description: Interacts with the user to update the status of all University auditoriums.
	 */
	public void updateStatus() {
		inputNum = new Scanner(System.in);
		inputStr = new Scanner(System.in);
		boolean check = false;
		int month = 0;
		int day = 0;
		int hour = 0;
		
		do {
			System.out.println("Type month");
			month = inputNum.nextInt();
			if (month <1 || month >12) {
				System.out.println("Invalid Month!");
			}
		}while(month <1 || month >12);
		
		
		
		do {
			System.out.println("Type day ");
			day = inputNum.nextInt();
			if (day <1 || day >31) {
				System.out.println("Invalid Day!");
			}
			else if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day == 31) {
				check = true;
			}
			else if ( ((isLeap(((LocalDateTime.now()).getYear()))) && month == 2 && day > 29)  || (!(isLeap(((LocalDateTime.now()).getYear()))) && month == 2 && day > 28)) {
				System.out.println("Invalid Day!");
			}
			else {
				check = true;
			}
		} while(!check);
		check = false;
		
		do {
			System.out.println("Type event start hour");
			hour = inputNum.nextInt();
			if(hour < 1 || hour > 23) {
				System.out.println("Invalid start hour!");
			}		
		} while(hour < 1 || hour > 23);	
		
		LocalDateTime currentDate = LocalDateTime.of(((LocalDateTime.now()).getYear()), month, day, hour, 0);		
		this.currentDate = currentDate;
		university.updateStatus(currentDate);
		
		System.out.println("Status updated succesfully");
	}
	/**Name: toStringAuditoriums
	 * Description: Prints the university auditoriums with is Id
	 */
	public void toStringAuditoriums(){
		System.out.println(university.toStringEvents());
	}
	/**Name: reportChair
	 * Description: Interacts with the user to report a damaged chair
	 */
	public void reportChair() {
		String audId = "";
		int audIdInt = 0;
		char row = '[';
		int column = 0;
		boolean check = false;
		inputNum = new Scanner(System.in);
		inputStr = new Scanner(System.in);
		
		do {
			System.out.println("Select auditorium to report a damaged chair (Type its corresponding Id): ");
			System.out.println(university.toString());
			audIdInt = inputNum.nextInt();
			
			if (audIdInt>=0 && audIdInt <6) {
				check = true;
			}
			else {
				System.out.println("Invalid Id!");
			}
		}while(!check);
		audId = Integer.toString(audIdInt);
		
		System.out.println("Type row (letter): ");
		row = inputStr.next().charAt(0);		
		System.out.println("Type column (number): ");
		column = inputNum.nextInt();
		
		System.out.println(university.reportChair(audId, row, column));			
	}
	/**Name: setCurrentDate
	 * Description: Sets the current date and time to the given date and time.
	 * @param date LocalDateTime date to set current day to.
	 */
	public void setCurrentDate(LocalDateTime date) {
		this.currentDate = date;
	}
	/**Name: damagedChairs
	 * Description: Interacts with the user to print the percentage of damaged chairs in an auditorium.
	 */
	public void damagedChairs() {
		String audId = "";
		int audIdInt = 0;		
		boolean check = false;
		inputNum = new Scanner(System.in);
		inputStr = new Scanner(System.in);
		
		do {
			System.out.println("Select auditorium to report a damaged chair (Type its corresponding Id): ");
			System.out.println(university.toString());
			audIdInt = inputNum.nextInt();
			
			if (audIdInt>=0 && audIdInt <6) {
				check = true;
			}
			else {
				System.out.println("Invalid Id!");
			}
		}while(!check);
		
		audId = Integer.toString(audIdInt);		
		System.out.println(university.damagedChairs(audId) + "%");
	}
}
