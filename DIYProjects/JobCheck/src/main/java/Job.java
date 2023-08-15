import java.util.ArrayList;

public class Job {
    private String companyName;
    private String position;
    private String location;
    private int salary;
    private ArrayList<String> techStacks;
    private State state = State.UNAPPLY;

    public Job(String companyName, String position, String location, int salary, ArrayList<String> techStacks) {
        this.companyName = companyName;
        this.position = position;
        this.location = location;
        this.salary = salary;
        this.techStacks = techStacks;
    }

    public Job(String companyName, String position, String location, int salary, State state) {
        this.companyName = companyName;
        this.position = position;
        this.location = location;
        this.salary = salary;
        this.state = state;
    }

    public Job(String companyName, String location, int salary, State state) {
        this.companyName = companyName;
        this.location = location;
        this.salary = salary;
        this.state = state;
    }

    public Job(String companyName, String position, String location, int salary, ArrayList<String> techStacks, State state) {
        this.companyName = companyName;
        this.position = position;
        this.location = location;
        this.salary = salary;
        this.techStacks = techStacks;
        this.state = state;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public ArrayList<String> getTechStacks() {
        return techStacks;
    }

    public void setTechStacks(ArrayList<String> techStacks) {
        this.techStacks = techStacks;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "CompanyName: " + companyName +
                ", Position: " + position +
                ", Location: " + location +
                ", Salary: " + salary +
                ", TechStacks: " + techStacks +
                ", State: " + state +
                "\n";
    }
}
