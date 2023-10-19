import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Main {

    public static void main(String[] args) {
        System.out.println("\nWelcome to my Zoo Program, where your animal will never be misplaced!!");


        // create a string list to hold the species names then load in the names from animalNames using the created function

        List<String> hyenaNamesList = new ArrayList<>(AnimalNames.getNamesForSpecies("Hyena"));
        List<String> lionNamesList = new ArrayList<>(AnimalNames.getNamesForSpecies("Lion"));
        List<String> bearNamesList = new ArrayList<>(AnimalNames.getNamesForSpecies("Bear"));
        List<String> tigerNamesList = new ArrayList<>(AnimalNames.getNamesForSpecies("Tiger"));

        // display the names in each of the species name list
        System.out.println("\n");
        System.out.println("Hyena Names: " + hyenaNamesList);
        System.out.println("Lion Names: " + lionNamesList);
        System.out.println("Bear Names: " + bearNamesList);
        System.out.println("Tiger Names: " + tigerNamesList);
        System.out.println("\n");

        // Call getAnimalCount to get the total animal count at the start of the program to show no animals have been created yet
        System.out.println("\nWe begin with " + Animal.getAnimalCount() + " animals in our zoo, where did they all go?\n\n");

        // create a list of animal objects to hold the animals created in arriving animals
        List<Animal> arrivingAnimals = ArrivingAnimals.loadArrivingAnimalsFromFile("C:\\Users\\Johnny\\JavaFiles\\arrivingAnimals.txt");

        // list out those animals into their own corresponding species array list
        List<Animal> hyenas = new ArrayList<>();
        List<Animal> lions = new ArrayList<>();
        List<Animal> bears = new ArrayList<>();
        List<Animal> tigers = new ArrayList<>();

        // used to correctly extract age
        Pattern agePattern = Pattern.compile("(\\d+) year old");

        //iterates over each Animal object in the arrivingAnimals list
        for (Animal animal : arrivingAnimals) {

            //stores each description of each animal in the list
            String animalDesc = animal.getAnimalDesc();
            // looks through the description which is the first part of line of info before the first comma
            // determines the species and adds every line of info that contains that corresponding species
            // increments each species as it is added into its corresponding list
            if (animalDesc.contains("hyena")) {
                hyenas.add(animal);
                Hyena.incrementNumOfHyenas(); // Increment the Hyena counter
            } else if (animalDesc.contains("lion")) {
                lions.add(animal);
                Lion.incrementNumOfLions(); // Increment the Lion counter
            } else if (animalDesc.contains("bear")) {
                bears.add(animal);
                Bear.incrementNumOfBears(); // Increment the Bear counter
            } else if (animalDesc.contains("tiger")) {
                tigers.add(animal);
                Tiger.incrementNumOfTigers(); // Increment the Tiger counter
            }
        }
// Display the Hyenas before modifications
        System.out.println("\nHyenas Before Modifications:\n");
        for (Animal hyena : hyenas) {
            System.out.println( hyena.getAnimalDesc() + "; " + hyena.getAnimalBirthSeason() + "; "
                    + hyena.getAnimalColor() + "; " + hyena.getAnimalWeight() + "; " + hyena.getOrigin());
        }
        System.out.println();

        // Display the Lions before modifications
        System.out.println("Lions Before Modifications:\n");
        for (Animal lion : lions) {
            System.out.println( lion.getAnimalDesc() + "; " + lion.getAnimalBirthSeason() + "; "
                    + lion.getAnimalColor() + "; " + lion.getAnimalWeight() + "; " + lion.getOrigin());
        }
        System.out.println();


        // Display the Bears before modifications
        System.out.println("Bears Before Modifications:\n");
        for (Animal bear : bears) {
            System.out.println( bear.getAnimalDesc() + "; " + bear.getAnimalBirthSeason() + "; "
                    + bear.getAnimalColor() + "; " + bear.getAnimalWeight() + "; " + bear.getOrigin());
        }
        System.out.println();


        // Display the Tigers before modifications
        System.out.println("Tigers Before Modifications:\n");
        for (Animal tiger : tigers) {
            System.out.println( tiger.getAnimalDesc() + "; " + tiger.getAnimalBirthSeason() + "; "
                    + tiger.getAnimalColor() + "; " + tiger.getAnimalWeight() + "; " + tiger.getOrigin());
        }
        System.out.println();

        //iterate through the list of hyenas, update their properties, and then replace the existing hyena in the list with the modified one

        int hyenaCounter = 0 ; // Initialize a counter, used to generate unique id

        for (int i = 0; i < hyenas.size(); i++) {
            Animal hyena = hyenas.get(i); // Get the current hyena from the list

            // You should perform your modifications to the hyena object here.
            // sets name using the genuniquename from the Hyena class
            hyena.setAnimalName(Hyena.generateUniqueName(hyenaNamesList));

            // Increment the counter for each hyena
            hyenaCounter++;

            // Set the unique ID based on the counter
            hyena.setAnimalID("Hy" + String.format("%02d", hyenaCounter));

            // correctly extract age from the description portion of info
            Matcher matcher = agePattern.matcher(hyena.getAnimalDesc());

            // calculate birthday
            if (matcher.find()) {
                String age = matcher.group(1);
                String birthSeason = hyena.getAnimalBirthSeason();
                Date birthdate;
                // allow an option for "unknown birth season"
                if (!"unknown birth season".equalsIgnoreCase(birthSeason)) {
                    birthdate = AnimalUtils.calculateBirthdate(Integer.parseInt(age), birthSeason);
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.YEAR, -Integer.parseInt(age));
                    calendar.set(Calendar.MONTH, Calendar.JANUARY);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);
                    birthdate = calendar.getTime();
                }


            }


            // Replace the existing hyena in the list with the modified hyena
            hyenas.set(i, hyena);
        }



        // REPEAT HYENA MODIFICATION STEPS FOR ALL OF THE OTHER SPECIES: LION, BEAR, & TIGER

        int lionCounter = 0; // Initialize a counter

        for (int i = 0; i < lions.size(); i++) {
            Animal lion = lions.get(i); // Get the current lion from the list

            // Set the lion's name
            lion.setAnimalName(Lion.generateUniqueName(lionNamesList));

            // Increment the counter for each lion
            lionCounter++;

            // Set the unique ID based on the counter
            lion.setAnimalID("Li" + String.format("%02d", lionCounter));

            Matcher matcher = agePattern.matcher(lion.getAnimalDesc());

            if (matcher.find()) {
                String age = matcher.group(1);
                String birthSeason = lion.getAnimalBirthSeason();
                Date birthdate;

                if (!"unknown birth season".equalsIgnoreCase(birthSeason)) {
                    birthdate = AnimalUtils.calculateBirthdate(Integer.parseInt(age), birthSeason);
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.YEAR, -Integer.parseInt(age));
                    calendar.set(Calendar.MONTH, Calendar.JANUARY);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);
                    birthdate = calendar.getTime();
                }


            }


            // Replace the existing lion in the list with the modified lion
            lions.set(i, lion);
        }


        int bearCounter = 0; // Initialize a counter

        for (int i = 0; i < bears.size(); i++) {
            Animal bear = bears.get(i); // Get the current bear from the list

            // Set the bear's name
            bear.setAnimalName(Bear.generateUniqueName(bearNamesList));

            // Increment the counter for each bear
            bearCounter++;

            // Set the unique ID based on the counter
            bear.setAnimalID("Br" + String.format("%02d", bearCounter));

            Matcher matcher = agePattern.matcher(bear.getAnimalDesc());

            if (matcher.find()) {
                String age = matcher.group(1);
                String birthSeason = bear.getAnimalBirthSeason();
                Date birthdate;

                if (!"unknown birth season".equalsIgnoreCase(birthSeason)) {
                    birthdate = AnimalUtils.calculateBirthdate(Integer.parseInt(age), birthSeason);
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.YEAR, -Integer.parseInt(age));
                    calendar.set(Calendar.MONTH, Calendar.JANUARY);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);
                    birthdate = calendar.getTime();
                }


            }


            // Replace the existing bear in the list with the modified bear
            bears.set(i, bear);
        }




        int tigerCounter = 0; // Initialize a counter

        for (int i = 0; i < tigers.size(); i++) {
            Animal tiger = tigers.get(i); // Get the current bear from the list

            // Set the bear's name
            tiger.setAnimalName(Tiger.generateUniqueName(tigerNamesList));

            // Increment the counter for each bear
            tigerCounter++;

            // Set the unique ID based on the counter
            tiger.setAnimalID("Ti" + String.format("%02d", tigerCounter));

            Matcher matcher = agePattern.matcher(tiger.getAnimalDesc());

            if (matcher.find()) {
                String age = matcher.group(1);
                String birthSeason = tiger.getAnimalBirthSeason();
                Date birthdate;

                if (!"unknown birth season".equalsIgnoreCase(birthSeason)) {
                    birthdate = AnimalUtils.calculateBirthdate(Integer.parseInt(age), birthSeason);
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.YEAR, -Integer.parseInt(age));
                    calendar.set(Calendar.MONTH, Calendar.JANUARY);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);
                    birthdate = calendar.getTime();
                }


            }

            // Replace the existing bear in the list with the modified bear
            tigers.set(i, tiger);
        }

        // display the updated list using the method in each of the corresponding species class
        Hyena.displayUpdatedHyenas(hyenas, agePattern, hyenaNamesList);
        Lion.displayUpdatedLions(lions, agePattern, lionNamesList);
        Bear.displayUpdatedBears(bears, agePattern, bearNamesList);
        Tiger.displayUpdatedTigers(tigers, agePattern, tigerNamesList);

        // display current number of each species & total animal count
        System.out.println("\nThere are now " + Hyena.getNumOfHyenas() + " Hyenas in the zoo\n");
        System.out.println("There are now " + Lion.getNumberOfLions() + " Lions in the zoo\n");
        System.out.println("There are now "+ Bear.getNumberOfBears() + " Bears in the zoo\n");
        System.out.println("There are now " + Tiger.getNumOfTiger() + " Tigers in the zoo\n");
        System.out.println("We now have a total of " + Animal.getAnimalCount() + " Animals living in our zoo!\n\n");


        // write each of the updated species list onto ZooPopulation text file using the method created in AnimalUtils class
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ZooPopulation.txt"))) {
            writer.write("Zoo Population:");
            writer.newLine();

            // Hyenas Habitat
            writer.write("\n\nHyena Habitat:\n");
            writer.newLine();

            // Write hyena data
            AnimalUtils.writeSpeciesToTextFile(writer, hyenas, agePattern, hyenaNamesList, "Hyena");

            // Lions Habitat
            writer.write("\n\nLion Habitat:\n");
            writer.newLine();

            // Write lion data
            AnimalUtils.writeSpeciesToTextFile(writer, lions, agePattern, lionNamesList, "Lion");

            // Bears Habitat
            writer.write("\n\nBear Habitat:\n");
            writer.newLine();

            // Write bear data
            AnimalUtils.writeSpeciesToTextFile(writer, bears, agePattern, bearNamesList, "Bear");

            // Tigers Habitat
            writer.write("\n\nTiger Habitat:\n");
            writer.newLine();

            // Write tiger data
            AnimalUtils.writeSpeciesToTextFile(writer, tigers, agePattern, tigerNamesList, "Tiger");
        } catch (IOException e) {
            e.printStackTrace(); // Handle or report the error
        }



    }
}



