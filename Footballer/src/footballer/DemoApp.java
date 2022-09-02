/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footballer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author N00180141
 */
public class DemoApp {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner keyboard = new Scanner(System.in);

        Model model = Model.getInstance();

        Footballer f;

        int opt;
        do {
            System.out.println("********* FOOTBALLER MAIN MENU ********");
            System.out.println("1. Create a new Footballer");
            System.out.println("2. View all Footballers");
            System.out.println("3. Edit a Footballers by ID");
            System.out.println("4. Delete a Footballers by ID");
            System.out.println("5. Search for Footballer by ID");
            System.out.println("6. Search for Team");
            System.out.println("7. Exit Program");
            System.out.println();

            System.out.print("Enter option: ");
            String line = keyboard.nextLine();
            opt = Integer.parseInt(line);

            switch (opt) {
                //when the user choose option 1 they can create and input values into a list.
                case 1: {

                    f = readFootballer(keyboard);

                    //Method addFootballer() is called in model. 
                    model.addFootballer(f);
                    break;
                }
                // when the user choses option 2 the footballers are displayed in an array list from the model. 
                case 2: {

                    // get the footballers from the model and prints footballers.
                    printFootballers(model.getFootballers());
                    break;
                }
                // when the user choses option 3 the user can enter the footballer id to edit the footballer details. 
                case 3: {
                    
                    //Method updateFootballer() is located in model and is called to perform. 
                    updateFootballer(keyboard, model);
                    break;
                }
                
                // when the user choses option 4 the user can enter the footballer id to delete the footballer details from the database
                case 4: {
                    deleteFootballer(keyboard, model);
                    break;
                }

                // when the user choses option 5 the user can enter the footballer id to view the one footballer details
                case 5: {
                    searchFootballer(keyboard, model.showFootballers());
                    break;
                }

                // when the user choses option 6 the user can enter a team and view all the footballers that are apart of the searched team
                case 6: {
                    List<Footballer> footballersByTeam = searchTeam(keyboard, model.showFootballers());
                    printFootballers(footballersByTeam);

                    break;
                }

            }
        } while (opt != 7);
        System.out.println("Goodbye");
    }

    //creates a footballer and is added to the database
    private static Footballer readFootballer(Scanner keyboard) {
        String name, team, position, skills;
        int goal, assists;
        double salary;

        System.out.print("Enter name : ");
        name = keyboard.nextLine();

        System.out.print("Enter team : ");
        team = keyboard.nextLine();

        System.out.print("Enter position : ");
        position = keyboard.nextLine();

        System.out.print("Enter goal : ");
        goal = keyboard.nextInt();
        keyboard.nextLine();

        System.out.print("Enter assists : ");
        assists = keyboard.nextInt();
        keyboard.nextLine();

        System.out.print("Enter salary : ");
        salary = keyboard.nextDouble();
        keyboard.nextLine();

        System.out.print("Enter skill : ");
        skills = keyboard.nextLine();

        //Footballer object f passes in the variables name, team, position, goal, assists, skills and salary
        Footballer f = new Footballer(name, team, position, goal, assists, skills, salary);

        //footballer object f calls method
        return f;

    }

    //shows all info about all footballers in the database
    private static void printFootballers(List<Footballer> footballers) {

        for (Footballer ft : footballers) {
            System.out.println(ft.toString());
        }
    }

    //Updates the footballer from the database
    private static void updateFootballer(Scanner keyboard, Model model) {
     
        System.out.print("Enter a footballer ID to update their details : ");
        int id = keyboard.nextInt();
        keyboard.nextLine();

        //If the footballer ID exists this the updating code appears
        Footballer f = model.findFootballerById(id);
        if (f != null) {
            System.out.println("\n Footballer found, updating it! ");
            System.out.print("Update name : ");
            f.setName(keyboard.nextLine());

            System.out.print("Update team : ");
            f.setTeam(keyboard.nextLine());

            System.out.print("Update position : ");
            f.setPosition(keyboard.nextLine());

            System.out.print("Update goal : ");
            f.setGoal(Integer.parseInt(keyboard.nextLine()));

            System.out.print("Update assists : ");
            f.setAssists(Integer.parseInt(keyboard.nextLine()));

            System.out.print("Update salary : ");
            f.setSalary(Double.parseDouble(keyboard.nextLine()));

            System.out.print("Update skill : ");
            f.setSkills(keyboard.nextLine());
           
            //if the update is susessful it prints out the next line
            if (model.updateFootballer(f)) {
                System.out.println("\nFootballer updated");
            } else {
                //else if it didnt update correctly it prints the next
                System.out.println("\nFootballer was not updated");
            }
        } else {
            //if the footballer ID doesnt exsist this line of code is printed
            System.out.println("\nFootballer was not found");
        }
     
    }

    //Deletes the footballer from the database
    private static void deleteFootballer(Scanner keyboard, Model model) {

        // User is assked for footballer id to be deleted
        System.out.print("Enter the ID of the footballer to delete: ");
        int id = Integer.parseInt(keyboard.nextLine());
        Footballer f;

        f = model.findFootballerById(id);
        if (f != null) {

            if (model.removeFootballer(f)) {
                System.out.println("\nFootballer deleted");
            } else {

                System.out.println("\nFootballer exists but was not deleted");
            }
        } else {
            System.out.println("\nFootballer was not found");
        }
    }

    //searches the database for info about a single footballer
    private static void searchFootballer(Scanner keyboard, List<Footballer> footballers) {

        // User is assked for footballer id they liked to be printed
        System.out.print("Enter the ID of the footballer you'd like to view: ");
        int id = Integer.parseInt(keyboard.nextLine());
        Footballer f;

        for (Footballer ft : footballers) {
            //if statement to print all the footballer info if their id is entered
            if (ft.getId() == id) {
                System.out.println(ft.toString());
            }
        }
    }

    //searches the database for info about footballers in a team
    private static List<Footballer> searchTeam(Scanner keyboard, List<Footballer> footballers) {

        // User is assked for the team they liked to know what footballers play for them
        List<Footballer> footballersByTeam = new ArrayList();
        System.out.print("Enter the team name you'd like to view: ");
        String team = keyboard.nextLine();
        for (Footballer ft : footballers) {
            //if statement to print all the footballer info if they are apart of the team
            if (ft.getTeam().equalsIgnoreCase(team)) {
//                System.out.println("Footballer found!");
                footballersByTeam.add(ft);
            } 
        }
        //if the footballer team is not the same as the one inputted this is printed 
        if (footballersByTeam.isEmpty()) {
            System.out.println("\nNo footballers in this team!");
        }
        return footballersByTeam;
    }

}
