package taskManager;

public class Task {
	private int id;
	private String title;
	private String description;
	private boolean completed;
	public Task(int id,String title,String description ) {
		this.id = id;
		this.title = title;
		this.description = description;
 	}
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed ) {
		this.completed = completed;
	}
	 @Override
	  public String toString() {
	        String status = completed ? "Terminee" : "En cours";

	        return "ID: " + id
	                + " | Titre: " + title
	                + " | Description: " + description
	                + " | Statut: " + status;
	  }
}
