import java.util.List;

public interface JobDAO {
    // get all the job informations
    public List<Job> getJobs();

    // inser new job
    public void insertJob(Job job);

    // update state of offer
    public void updateState(String companyName, String position, State state);

    // delete job
    public void deleteJob(String companyName, String position);
}
