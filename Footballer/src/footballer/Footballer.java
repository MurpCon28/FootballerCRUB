/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footballer;

/**
 *
 * @author N00180141
 */
public class Footballer {
    
    private int id;
    private String name;
    private String team;
    private String position;
    private int goal;
    private int assists;
    private String skills;
    private double salary;
//    private int autoId;
    
      public Footballer(String n, String t, String p, int g, int a, String sk, double sl) {
       // assigned the values in the Constructor and an auto increment for autoID;
       this.name = n;
       this.team = t;
       this.position = p;
       this.goal = g;
       this.assists = a;
       this.skills = sk;
       this.salary = sl;
//       this.autoId = autoId++;
       ;
    }
      
      public Footballer(int id, String n, String t, String p, int g, int a, String sk, double sl) {
     // assigned the values into this Constructor with the variables above  
       this.id = id;
       this.name = n;
       this.team = t;
       this.position = p;
       this.goal = g;
       this.assists = a;
       this.skills = sk;
       this.salary = sl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

//    public int getAutoId() {
//        return autoId;
//    }

//    public void setAutoId(int autoId) {
//        this.autoId = autoId;
//    }
    
     @Override
    public String toString() {
        return "Footballer{" + "id = " + id + ", name = " + name + ", team = " + team + ", position = " + position + ", goal = " + goal + ", skills = " + skills + ", salary = " + salary + '}';
    }
    
}
