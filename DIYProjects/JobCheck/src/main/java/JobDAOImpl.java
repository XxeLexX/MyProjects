import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAOImpl implements JobDAO{
    private final String url = "jdbc:mysql://localhost:3306/OfferCheck";
    private final String username = "root";
    private final String password = "password";

    @Override
    public List<Job> getJobs(){
        try (Connection connection = DriverManager.getConnection(url, username, password)){
            String sql = "SELECT Job.CompanyName, Job.Position, Job.Location, Job.Salary, Job.State, TechStacks.Stacks FROM Job INNER JOIN TechStacks ON Job.CompanyName = TechStacks.CompanyName AND Job.Position = TechStacks.Position";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            List<Job> res = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            Job lastJob = null;

            while (resultSet.next()){
                String companyName = resultSet.getString(1);
                String position = resultSet.getString(2);
                String tech = resultSet.getString(6);
                String location = resultSet.getString(3);
                int salary = resultSet.getInt(4);
                State state = State.valueOf(resultSet.getString(5));
                Job job = new Job(companyName, position, location, salary, new ArrayList<>(), state);
                job.getTechStacks().add(tech);

                // only update lastJob in the first loop
                if (lastJob == null) {
                    lastJob = job;
                    continue;
                }
                // if not the same job, add lastJob to res
                // else, only update the TechStacks of lastJob
                if (!lastJob.getCompanyName().equals(companyName) || !lastJob.getPosition().equals(position)) {
                    res.add(lastJob);
                    lastJob = job;
                } else {
                    lastJob.getTechStacks().add(tech);
                }
            }
            // add lastJob from the latest loop to res
            res.add(lastJob);

            preparedStatement.close();
            return res;
        } catch (Exception e) {
            throw new IllegalStateException("Error while get jobs in database!", e);
        }
    }

    @Override
    public void insertJob(Job job) {
        try (Connection connection = DriverManager.getConnection(url, username, password)){
            // Add job informations except tech stacks
            String sql1 = "INSERT INTO Job(CompanyName, Position, Location, Salary, State) VALUE (?,?,?,?,?)";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps1.setString(1, job.getCompanyName());
            ps1.setString(2, job.getPosition());
            ps1.setString(3, job.getLocation());
            ps1.setInt(4, job.getSalary());
            ps1.setString(5, job.getState().toString());
            ps1.executeUpdate();
            ps1.close();

            // Add techStacks of Job
            String sql2 = "INSERT INTO TechStacks(CompanyName, Position, Stacks) VALUE (?,?,?)";
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            for (String techStack : job.getTechStacks()) {
                ps2.setString(1, job.getCompanyName());
                ps2.setString(2, job.getPosition());
                ps2.setString(3, techStack);
                ps2.executeUpdate();
            }
            ps2.close();
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }
    }

    @Override
    public void updateState(String companyName, String position, State state) {
        try (Connection connection = DriverManager.getConnection(url, username, password)){
            String sql = "UPDATE Job SET State = ? WHERE CompanyName = ? AND Position = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, state.toString());
            ps.setString(2, companyName);
            ps.setString(3, position);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }
    }

    @Override
    public void deleteJob(String companyName, String position) {
        try (Connection connection = DriverManager.getConnection(url, username, password)){
            String sql = "DELETE FROM Job WHERE CompanyName = ? AND Position = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, companyName);
            ps.setString(2, position);
            ps.executeUpdate();
            ps.close();

            String sql2 = "DELETE FROM TechStacks WHERE CompanyName = ? AND Position = ?";
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps2.setString(1, companyName);
            ps2.setString(2, position);
            ps2.executeUpdate();
            ps2.close();
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }
    }
}
