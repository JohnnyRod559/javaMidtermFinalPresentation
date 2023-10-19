import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnimalUtils extends Animal {



    public static Date calculateBirthdate(int age, String birthSeason) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.US);

        // Get today's date:
        Date today = calendar.getTime();

        // Calculate the birth year based on the current date and age
        calendar.add(Calendar.YEAR, -age);
        Date birthdate = calendar.getTime();

        // Calculate the birth month and day based on the birth season
        if (birthSeason.contains("spring")) {
            // Set the birthdate to March 31st of the birth year
            calendar.set(Calendar.MONTH, Calendar.MARCH);
            calendar.set(Calendar.DAY_OF_MONTH, 19);
        } else if (birthSeason.contains("fall")) {
            // Set the birthdate to September 30th of the birth year
            calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
            calendar.set(Calendar.DAY_OF_MONTH, 23);
        } else if (birthSeason.contains("winter")) {
            // Set the birthdate to September 30th of the birth year
            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
            calendar.set(Calendar.DAY_OF_MONTH, 21);

        } else if (birthSeason.contains("summer")) {
            // Set the birthdate to September 30th of the birth year
            calendar.set(Calendar.MONTH, Calendar.JUNE);
            calendar.set(Calendar.DAY_OF_MONTH, 20);
            // You can add more cases for other seasons if needed
        }


        // Set the final birthdate with the calculated month and day
        birthdate = calendar.getTime();

        return birthdate;
    }

    // create function to write species to text file give correct parameters
    public static void writeSpeciesToTextFile(BufferedWriter writer, List<Animal> speciesList, Pattern agePattern, List<String> speciesNamesList, String speciesName) {
        int speciesCounter = 0; // Initialize a counter
        // goes through the specified species list
        for (int i = 0; i < speciesList.size(); i++) {
            Animal species = speciesList.get(i);

            // Set the species' name
            species.setAnimalName(speciesNamesList.get(i));

            // Increment the counter and set the unique ID
            speciesCounter++;
            species.setAnimalID(speciesName.substring(0, 2) + String.format("%02d", speciesCounter));

            Matcher matcher = agePattern.matcher(species.getAnimalDesc());

            if (matcher.find()) {
                String age = matcher.group(1);
                String birthSeason = species.getAnimalBirthSeason();
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

                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
                String formattedBirthdate = sdf.format(birthdate);

                try {
                    writer.write(species.getAnimalID() + "; " + species.getAnimalName() + "; " +
                            age + " years old; birth date " + formattedBirthdate + "; " +
                            species.getAnimalColor() + "; " +
                            species.getAnimalGender() + "; " +
                            species.getAnimalWeight() + " pounds; " +
                            "from " + species.getOrigin());
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace(); // Handle or report the error
                }
            }
        }
    }
    public static int extractAge(String animalDesc) {
        String[] wordsInDesc = animalDesc.split(" ");
        for (int i = 0; i < wordsInDesc.length; i++) {
            if (wordsInDesc[i].equalsIgnoreCase("years") && i > 0) {
                try {
                    return Integer.parseInt(wordsInDesc[i - 1]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0; // Default to 0 if age is not found
    }

    public static String extractGender(String animalDesc) {
        String[] wordsInDesc = animalDesc.split(" ");
        for (String word : wordsInDesc) {
            if ("male".equalsIgnoreCase(word) || "female".equalsIgnoreCase(word)) {
                return word.toLowerCase();
            }
        }
        return "unknown"; // Default to "unknown" if gender is not found
    }


}

