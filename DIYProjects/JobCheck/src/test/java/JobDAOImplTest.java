import org.junit.Test;

import java.util.ArrayList;

public class JobDAOImplTest {
    @Test
    public void getAllJobTest() {
        JobDAOImpl job = new JobDAOImpl();
        System.out.println(job.getJobs());
    }

    @Test
    public void insertJobTest() {
        JobDAOImpl job = new JobDAOImpl();
        ArrayList<String> techs = new ArrayList<>();
        techs.add("JAVA");
        techs.add("SQL");
        techs.add("Spring Boot");

        job.insertJob(new Job("CDC", "Junior JAVA Developer", "Berlin", 50000, techs));
    }

    @Test
    public void connectionTest() {
        JobManagerSQL jobManagerSQL = new JobManagerSQL();
        jobManagerSQL.connectDB();
    }

    @Test
    public void updateStateTest() {
        JobDAOImpl jobDAOImpl = new JobDAOImpl();
        //jobDAOImpl.updateState("cimt AG", "Junior Cloud Data Engineer", State.APPLY);
        jobDAOImpl.updateState("Check24", "Junior PHP Fullstack Developer", State.REJECT);
    }

    @Test
    public void deleteJobTest() {
        JobDAOImpl jobDAOImpl = new JobDAOImpl();
        jobDAOImpl.deleteJob("cimt AG", "Junior Cloud Data Engineer");
    }
}
