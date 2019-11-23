package model;

public class Chair {
	public final static String EMPTY = "Empty"; 
	public final static String OCUPIED = "Ocupied";
	public final static String DAMAGED = "Damaged";
	private String status;
	/**Name: Chair<br>
	 * Description: Constructor for class Chair.<br>
	 * @param status chair status
	 */
	public Chair(String status) {
		this.status = status;
	}
	/**Name: getStatus<br>
	 * Description: returns a String with the chair status.<br>
	 * <b>PRE: </b> A chair object must be created chair != null.<br>
	 * @return String Chair status
	 */
	public String getStatus() {
		return status;
	}
	/**Name: setStatus<br>
	 * Description: Set the chair status to the given status.<br>
	 * <b>PRE: </b>A chair object must be created chair != null.<br>
	 * <b>POS: </b>Attribute status updated.
	 * @param status Chair status.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**Name: toString<br>
	 * Description: Returns a String with the chair status.<br>
	 * <b>PRE: </b> A chair object must be created chair != null.<br>	
	 * @return String Chair status.
	 */
	public String toString() {
		String msg = "";
		msg += "Status: " + this.status + "\n";
		return msg;
	}
}
