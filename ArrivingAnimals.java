import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArrivingAnimals {

    // create a list of Animals to be loaded into Main
    public static List<Animal> loadArrivingAnimalsFromFile(String filePath ) {
        filePath = "C:\\Users\\Johnny\\JavaFiles\\arrivingAnimals.txt";

        //intitialize list to store animal objects created
        List<Animal> arrivingAnimals = new ArrayList<>();


        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                // create a parts array for the different attributes in the line of info from the file
                String[] parts = line.split(",");

                // declare the variables value from the info using the index position corresponding to the attribute
                if (parts.length == 6) {
                    String animalDesc = parts[0].trim();
                    String animalBirthSeason = parts[1].trim();
                    String animalColor = parts[2].trim();
                    String animalWeight = parts[3].trim();
                    String origin1 = parts[4].trim();
                    String origin2 = parts[5].trim();

                    // Extract age and gender using the methods in AnimalUtils class
                    int age = AnimalUtils.extractAge(animalDesc);
                    String gender = AnimalUtils.extractGender(animalDesc);

                    // Concatenate origin1 and origin2 into the origin field
                    String origin = origin1 + ", " + origin2;

                    // Create an Animal objects to be loaded into main and modified
                    Animal arrivingAnimal = new Animal();
                    arrivingAnimal.setAnimalDesc(animalDesc);
                    arrivingAnimal.setAnimalGender(gender); // Set the gender
                    arrivingAnimal.setAnimalAge(age + " years");
                    arrivingAnimal.setAnimalBirthSeason(animalBirthSeason);
                    arrivingAnimal.setAnimalColor(animalColor);
                    arrivingAnimal.setAnimalWeight(animalWeight);
                    arrivingAnimal.setOrigin(origin);

                    // Add the arriving animal to the list
                    arrivingAnimals.add(arrivingAnimal);
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return arrivingAnimals;
    }



}
