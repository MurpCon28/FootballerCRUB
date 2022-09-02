package footballer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


// the class constants below are connected to a table in the database
public class FootballerTableGateway {
    private static final String TABLE_NAME = "footballer";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_TEAM = "team";
    private static final String COLUMN_POSITION = "position";
    private static final String COLUMN_GOAL = "goal";
    private static final String COLUMN_ASSISTS = "assists";
    private static final String COLUMN_SKILLS = "skills";
    private static final String COLUMN_SALARY = "salary";

    private Connection mConnection;

    public FootballerTableGateway(Connection connection) {
        mConnection = connection;
    }

    public Footballer insertFootballer(Footballer f) throws SQLException {
       
       

        String query;                   //execute the sql query
        PreparedStatement stmt;         
        int numRowsAffected;
        int id;

        // SQL INSERT statement has place holders for the values so it can be added to the database
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_NAME + ", " +
                COLUMN_TEAM + ", " +
                COLUMN_POSITION + ", " +
                COLUMN_GOAL + ", " +
                COLUMN_ASSISTS + ", " +
                COLUMN_SKILLS + ", " +
                COLUMN_SALARY +
                ") VALUES (?, ?, ?, ?, ?, ?, ?)";

        // a PreparedStatement object that executes the query and adds the values into the query
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, f.getName());
        stmt.setString(2, f.getTeam());
        stmt.setString(3, f.getPosition());
        stmt.setInt(4, f.getGoal());
        stmt.setInt(5, f.getAssists());
        stmt.setString(6, f.getSkills());
        stmt.setDouble(7, f.getSalary());

        //  execute the query and make sure that one and only one row was inserted into the database
        numRowsAffected = stmt.executeUpdate();
       
        if (numRowsAffected == 1) {
            // if one row was inserted, retrieve the id assigned to that row and create a footballer object to return
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();

            id = keys.getInt(1);
            f.setId(id);
          
        }

        // return the footballer object with a new id,
        return f;
    }

    // calls method from the model
    public List<Footballer> getFootballers() throws SQLException {
        String query;                   
        Statement stmt;                 
        ResultSet rs;                   
        List<Footballer> footballers;   
                                        

        String name, team, position, skills;
        int id, goal, assists;
        double salary;

        Footballer f;                   

        // executes the SQL SELECT statement to get java.util.ResultSet to show results of the statement
        query = "SELECT * FROM " + TABLE_NAME;
        
        stmt = this.mConnection.createStatement();
        // rs is a ResultSet object. Has rows of data from the database. 
        rs = stmt.executeQuery(query);

 
        footballers = new ArrayList<Footballer>();
    
        // loop of result getting data from footballer datebase, footballer object with data and put into an arraylist
        while (rs.next()) {
            id = rs.getInt(COLUMN_ID);
            name = rs.getString(COLUMN_NAME);
            team = rs.getString(COLUMN_TEAM);
            position = rs.getString(COLUMN_POSITION);
            goal = rs.getInt(COLUMN_GOAL);
            assists = rs.getInt(COLUMN_ASSISTS);
            skills = rs.getString(COLUMN_SKILLS);
            salary = rs.getDouble(COLUMN_SALARY);
       
            // new footballer f using the variables from Footballer
            f = new Footballer(id, name, team, position, 
                        goal, assists, skills, salary);
            footballers.add(f);
        }

        // returns the arraylist of Footballer objects into the model. 
        return footballers;
    }
    
    public boolean editFootballer(Footballer f) throws SQLException {
       
       

        String query;    
        String query2;  //execute the sql query
        PreparedStatement stmt;         
        int numRowsAffected;
        int id;

        // SQL Update statement has place holders for the values so it can be updated to the database
        query = "UPDATE " + TABLE_NAME + " SET " + COLUMN_NAME + "=?, " + COLUMN_TEAM + "=?, " + COLUMN_POSITION + "=?, " + COLUMN_GOAL + "=?, " + COLUMN_ASSISTS + "=?, " + COLUMN_SKILLS + "=?, " + COLUMN_SALARY + "=? " + "WHERE " + COLUMN_ID + "=?";             

        // a PreparedStatement object that executes the query and adds the values into the query
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, f.getName());
        stmt.setString(2, f.getTeam());
        stmt.setString(3, f.getPosition());
        stmt.setInt(4, f.getGoal());
        stmt.setInt(5, f.getAssists());
        stmt.setString(6, f.getSkills());
        stmt.setDouble(7, f.getSalary());
        stmt.setInt(8, f.getId());
 
        //prints what was updated into the area fields of footballer, used for spotting errors that might occur
//        System.out.println("*******************" + stmt.toString());
        
        numRowsAffected = stmt.executeUpdate();
       
        // if numRowsAffected is 1 then the return is true
        if (numRowsAffected == 1) {
          return true;
          
        }

        // return false when the update has an error
        return false;
    }
    
    public boolean deleteFootballer(Footballer f)  throws SQLException {
        String query;                 
        PreparedStatement stmt;        
        int numRowsAffected;
        int id;

       
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "= ?";
        
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1, f.getId());
       
        // prints to test if the delete works
        System.out.println("\n\nTHE SQL LOOKS LIKE THIS " + stmt.toString() + "\n\n");

        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
          return true;
        }
        return false;
    }
    
    public List<Footballer> showFootballer() throws SQLException {
        String query;                   
        Statement stmt;                 
        ResultSet rs;                   
        List<Footballer> footballers;   
                                        

        String name, team, position, skills;
        int id, goal, assists;
        double salary;

        Footballer f;                   

        // executes the SQL SELECT statement to get java.util.ResultSet to show results of the statement
        query = "SELECT * FROM " + TABLE_NAME;
        
        stmt = this.mConnection.createStatement();
        // rs is a ResultSet object. Has rows of data from the database. 
        rs = stmt.executeQuery(query);

 
        footballers = new ArrayList<Footballer>();
    
        // loop of result getting data from footballer datebase, footballer object with data and put into an arraylist
        while (rs.next()) {
            id = rs.getInt(COLUMN_ID);
            name = rs.getString(COLUMN_NAME);
            team = rs.getString(COLUMN_TEAM);
            position = rs.getString(COLUMN_POSITION);
            goal = rs.getInt(COLUMN_GOAL);
            assists = rs.getInt(COLUMN_ASSISTS);
            skills = rs.getString(COLUMN_SKILLS);
            salary = rs.getDouble(COLUMN_SALARY);
       
            // new footballer f using the variables from Footballer
            f = new Footballer(id, name, team, position, 
                        goal, assists, skills, salary);
            footballers.add(f);
        }

        // returns the arraylist of Footballer objects into the model. 
        return footballers;
    }
    
    
    public List<Footballer> showTeam() throws SQLException {
        String query;                   
        Statement stmt;                 
        ResultSet rs;                   
        List<Footballer> footballers;   
                                        

        String name, team, position, skills;
        int id, goal, assists;
        double salary;

        Footballer f;                   

        // performs the SQL SELECT statement to get java.util.ResultSet to show results of the statement which is looking for every footballer that is in a certain team
         query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_TEAM + " LIKE '%%'";
        
//        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);

 
        footballers = new ArrayList<Footballer>();
    
        // loop of result getting data from footballer datebase, footballer object with data and put into an arraylist
        while (rs.next()) {
            id = rs.getInt(COLUMN_ID);
            name = rs.getString(COLUMN_NAME);
            team = rs.getString(COLUMN_TEAM);
            position = rs.getString(COLUMN_POSITION);
            goal = rs.getInt(COLUMN_GOAL);
            assists = rs.getInt(COLUMN_ASSISTS);
            skills = rs.getString(COLUMN_SKILLS);
            salary = rs.getDouble(COLUMN_SALARY);
       
            // new footballer f using the variables from Footballer
            f = new Footballer(id, name, team, position, 
                        goal, assists, skills, salary);
            footballers.add(f);
        }

        // returns the arraylist of Footballer objects into the model. 
        return footballers;
    }
  
}