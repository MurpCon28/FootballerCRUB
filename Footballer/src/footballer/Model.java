/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footballer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author N00180141
 */
public class Model {

    private static Model instance = null;

    public static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    private List<Footballer> footballers;

    private FootballerTableGateway gateway;

    private Model() {

        try {
            //Connects to the database and pass this connection to the tablegateway so it can communicate with the database
            Connection conn = DBConnection.getInstance();
            this.gateway = new FootballerTableGateway(conn);

            this.footballers = gateway.getFootballers();
//            this.footballers = new ArrayList();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    // returns the array list of footballers to the program.
//    public List<Footballer> getFootballer() {
//        return new ArrayList<>(this.footballers);
//    }
    //the database adds a footballer to the database. 
    public void addFootballer(Footballer f) {
        try {

            // call a method that is in the FootballerTableGateway to put the footballer object into the database
            Footballer pWithID = this.gateway.insertFootballer(f);

            //  Add the footballer object into the arraylist
        } catch (SQLException ex) {
//            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error couldnt create a footballer in the database");
        }
    }

    //the database gets all the data about the footballer an prints them out
    public List<Footballer> getFootballers() {
        try {

            this.footballers = this.gateway.getFootballers();
        } catch (SQLException ex) {
//            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error couldnt get footballer info");
        }

        return footballers;
    }

    //updates the database to add new info to a footballer in the database. 
    public boolean updateFootballer(Footballer f) {

        boolean updated = false;
        try {

            // updates a method that is in the FootballerTableGateway to update the footballer object into the database
            updated = this.gateway.editFootballer(f);

            if (updated) {
                return updated;
            }
        } catch (SQLException ex) {
//            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL Exception");
        }

        return updated;
    }

    //searches the database by all the footballers id's
    Footballer findFootballerById(int id) {
        Footballer f = null;
        int i = 0;
        boolean found = false;
        while (i < this.footballers.size() && !found) {
            f = this.footballers.get(i);
            if (f.getId() == id) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            f = null;
        }
        return f;
    }

    //removes footballers from the databsae by their id's
    boolean removeFootballer(Footballer f) {
        boolean deleted = false;
        try {
            deleted = this.gateway.deleteFootballer(f);

            if (deleted) {
                this.footballers.remove(f);
            }
        } catch (SQLException ex) {
//            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error couldnt delete footballer from database");
        }

        // return even if the delete is true or false;
        return deleted;
    }

    //shows all the info for a single footballer by thier id
    public List<Footballer> showFootballers() {
        try {

            this.footballers = this.gateway.showFootballer();
        } catch (SQLException ex) {
//            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error couldnt get footballer info");
        }

        return footballers;
    }

    //shows all the footballers in a team that is searhced by their name, which is a string
    public List<Footballer> showTeam() {
        try {
            this.footballers = this.gateway.showTeam();
        } catch (SQLException ex) {
//            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error couldnt get team info");
        }

        return footballers;
    }
}
