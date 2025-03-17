public class TaskModel {
    private String category;
    private String number;
    private String deadline;

    public TaskModel(String category, String number, String deadline) {
        this.category = category;
        this.number = number;
        this.deadline = deadline;
    }

    public String getCategory() { return category; }
    public String getNumber() { return number; }
    public String getDeadline() { return deadline; }
}

