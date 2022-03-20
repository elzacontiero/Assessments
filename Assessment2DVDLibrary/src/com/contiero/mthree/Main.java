package com.contiero.mthree;

import java.io.*;
import java.util.Scanner;

public class Main { // the main class has one responsibility: the entry point of the application and in this case a main loop that will
    // present the menu and wait for user's input.

    Scanner scanner = new Scanner(System.in);// System in  is a special object that presents all the characters the users type.
    // the program can read what the user is typing.

    DVDLibraryInterface dvdLibrary = new DVDLibrary();

    void printMenuOptions() {
        System.out.println("DVD MANAGEMENT OPTIONS");
        System.out.println("1. Add Dvd to the collection");
        System.out.println("2. Remove a Dvd from the collection");
        System.out.println("3. Edited the information of a Dvd");
        System.out.println("4. List the Dvds in the collection");
        System.out.println("5. Display the information of a Dvd");
        System.out.println("6. Search for a Dvd by title");
        System.out.println("7. Load Dvd library from file");
        System.out.println("8. Save Dvd library to file");
        System.out.println("9. Exit program");
    }

    void addDvdToCollection() {
        System.out.println("Option 1: Add dvd to the collection.");
        DVD dvd = new DVD();
        // I'm using 'print' instead of 'println' because the user will enter his input just after the :
        System.out.print("Title: ");
        dvd.setTitle(scanner.nextLine());
        System.out.print("Release date: ");
        dvd.setReleaseDate(scanner.nextLine());
        System.out.print("MPAA rating: ");
        dvd.setMpaaRating(scanner.nextLine());
        System.out.print("Director's name: ");
        dvd.setDirectorName(scanner.nextLine());
        System.out.print("Studio: ");
        dvd.setStudio(scanner.nextLine());
        System.out.print("User notes: ");
        dvd.setNote(scanner.nextLine());
        // Here we have a dvd object with all the properties set. Next is to save into DVDLibrary.
        dvdLibrary.addDvd(dvd);
        // inform the user we have successfully added to the library.
        System.out.println("DVD inserted into library");
    }

    void removeDvd() {
        System.out.println("2. Remove a Dvd from the collection");
        System.out.print("Please enter the DVD index to be removed: ");
        int index = Integer.parseInt(scanner.nextLine());
        // I pass the index of the dvd to be removed down to dvdLibrary which will handle that request.
        dvdLibrary.removeDvd(index);
        System.out.println("Removed DVD at " + index);
    }

    void editDvd() {
        System.out.println("3. Edited the information of a Dvd");
        System.out.print("Index of DVD to edit: ");
        int index = Integer.parseInt(scanner.nextLine());
        DVD dvd = dvdLibrary.getDvd(index);
        // Now present the user with a sub-menu to select the field to be edited.
        System.out.println("Enter the field to edit: \n" +
                "1. Title\n" +
                "2. Release date\n" +
                "3. MPAA rating\n" +
                "4. Director's name\n" +
                "5. Studio\n" +
                "6. User rating\n");
        int option = Integer.parseInt(scanner.nextLine());
        switch (option) {
            case 1:
                System.out.print("Title: ");
                dvd.setTitle(scanner.nextLine());
                break;
            case 2:
                System.out.print("Release date: ");
                dvd.setReleaseDate(scanner.nextLine());
                break;
            case 3:
                System.out.print("MPPA rating: ");
                dvd.setMpaaRating(scanner.nextLine());
                break;
            case 4:
                System.out.print("Director's name: ");
                dvd.setDirectorName(scanner.nextLine());
                break;
            case 5:
                System.out.print("Studio: ");
                dvd.setStudio(scanner.nextLine());
                break;
            case 6:
                System.out.print("User note:");
                dvd.setNote(scanner.nextLine());
                break;
            default:
                System.out.println("Invalid option");
        }

        System.out.println("DVD: " + dvd);
    }

    void listDvds() {
        System.out.println("4. List the Dvds in the collection");

        // Loop over all the dvds from the library
        for (int i = 0; i < dvdLibrary.librarySize(); i++) {
            // and for each iteration prints the index alongside with the title.
            System.out.println("Index " + i + " - " + dvdLibrary.getDvd(i).getTitle());
        }
        // one last newline to separate the list from what's coming next in the console.
        System.out.println();
    }

    void displayDvd() {
        System.out.println("5. Display the information of a Dvd");
        System.out.print("Please enter the DVD index to be displayed: ");
        int index = Integer.parseInt(scanner.nextLine());
        DVD dvd = dvdLibrary.getDvd(index);
        System.out.println("Title: " + dvd.getTitle() + "\nRelease date: " + dvd.getReleaseDate() +
                "\nMPPA Rating: " + dvd.getMpaaRating() + "\nDirector: " + dvd.getDirectorName() +
                "\nStudio: " + dvd.getStudio() + "\nNote: " + dvd.getNote());
    }


    void searchByTitle() {
        System.out.println("6. Search for a Dvd by title");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        // Delegate to the library the task to search by title.
        DVD dvd = dvdLibrary.searchByTitle(title);
        if (dvd != null) {
            System.out.println(dvd);
        } else {
            System.out.println("Couldn't find title in library.");
        }
    }

    void loadFromFile() {
        System.out.println("7. Load Dvd library from file");
        System.out.print("File to load: ");
        String fileName = scanner.nextLine();
        try {
            FileReader fReader = new FileReader(fileName);
            BufferedReader bReader = new BufferedReader(fReader);
            dvdLibrary.trashAllDvds();
            String line = bReader.readLine();
            while(line != null) {
                String[] fields = line.split(",");
                DVD dvd = new DVD();
                // One,12121999,18,Terry,HBO,Yuck
                dvd.setTitle(fields[0]);
                dvd.setReleaseDate(fields[1]);
                dvd.setMpaaRating(fields[2]);
                dvd.setDirectorName(fields[3]);
                dvd.setStudio(fields[4]);
                dvd.setNote(fields[5]);
                dvdLibrary.addDvd(dvd);
                line = bReader.readLine();
            }
            System.out.println("Loaded " + dvdLibrary.librarySize() + " DVDs from file");
            bReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " doesn't exist.");
        } catch (IOException e) {
            System.out.println("Failed reading file.");
        }
    }

    void saveToFile() {
        System.out.println("8. Save Dvd library to file");
        System.out.print("File to save the library: ");
        String fileName = scanner.nextLine();
        try {
            FileWriter fWriter = new FileWriter(fileName);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            for (int i=0; i<dvdLibrary.librarySize(); i++) {
                DVD dvd = dvdLibrary.getDvd(i);
                String line = dvd.getTitle() + "," + dvd.getReleaseDate() + "," + dvd.getMpaaRating() + ","
                        + dvd.getDirectorName() + "," + dvd.getStudio() + "," + dvd.getNote() + '\n';
                bWriter.write(line);
            }
            bWriter.flush();
            bWriter.close();
            System.out.println("Saved dvds to file.");
        }
        catch (IOException e) {
            System.out.println("Failed writing to file");
        }
    }

    public void run() {


        // While presentMainMenu is true the program will keep presenting options to the user and will only
        // exit when requested on option 9.

        boolean presentMainMenu = true;
        while (presentMainMenu) {

            // Simply prints out the menu options to the user.
            printMenuOptions();

            /* we need to use nextLine here because the user will type enter,so we remove "enter" line from the buffer.
            Variable a captures what the user entered, minus the newline at the end.
             */
            String a = scanner.nextLine(); // we have a string here, but we need a number so:
            int userOption = Integer.parseInt(a); // I convert this variable a into an integer.

            // Also, by converting the option back into an int we can use it in a switch statement for the menu.
            // We cannot do that with a string.

            // According to what the user chooses the switch controls the flow and decide what to do next.
            switch (userOption) {
                case 1:
                    addDvdToCollection();
                    break;
                case 2:
                    removeDvd();
                    break;
                case 3:
                    editDvd();
                    break;
                case 4:
                    listDvds();
                    break;
                case 5:
                    displayDvd();
                    break;
                case 6:
                    searchByTitle();
                    break;
                case 7:
                    loadFromFile();
                    break;
                case 8:
                    saveToFile();
                    break;
                case 9:
                    System.out.println("9. Exit program");
                    presentMainMenu = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // entry point of DVD application
    public static void main(String[] args) {
        // I created a Main object to redirect the control to run method so that I don't have to make all the objects static.
        Main mainApp = new Main();
        mainApp.run();
    }

}
