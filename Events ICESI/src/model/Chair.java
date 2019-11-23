package model;

public class Chair {
	public final static String EMPTY = "Empty"; 
	public final static String OCUPIED = "Ocupied";
	public final static String DAMAGED = "Damaged";
	private String status;
	
	public Chair(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		String msg = "";
		msg += "Status: " + this.status + "\n";
		return msg;
	}
}
