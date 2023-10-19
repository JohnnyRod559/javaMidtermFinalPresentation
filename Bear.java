
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bear extends Animal{
    private static int numberOfBears = 0;
    public static void incrementNumOfBears(){
        numberOfBears++;
    }

    public static int getNumberOfBears(){
        return numberOfBears;
    }

    public static String sound = "GGRRRRRRRR";

    static Set<String> usedBearNames = new HashSet<>();
    static String generateUniqueName(List<String> bearNamesList) {
        Random random = new Random();
        String randomBearName = null;

        // Keep generating a random name until an unused one is found
        do {
            int randomIndex = random.nextInt(bearNamesList.size());
            randomBearName = bearNamesList.get(randomIndex);
        } while (usedBearNames.contains(randomBearName));

        // Add the used name to the set to prevent duplicates
        usedBearNames.add(randomBearName);

        return randomBearName;
    }


    public static void displayUpdatedBears(List<Animal> bears, Pattern agePattern, List<String> bearNamesList) {
        System.out.println("\nBears list: \n");

        int bearCounter = 0; // Initialize a counter

        for (int i = 0; i < bears.size(); i++) {
            Animal bear = bears.get(i); // Get the current bear from the list



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

                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
                String formattedBirthdate = sdf.format(birthdate);

                // Display the updated information for the bear
                System.out.println(bear.getAnimalID() + "; " +  bear.getAnimalName() + "; " +
                        age + " years old; birth date " + formattedBirthdate + "; " +
                        bear.getAnimalColor() + "; " +
                        bear.getAnimalGender() + "; " +
                        bear.getAnimalWeight() + "; " + "Sound: " + sound + "; " +
                        bear.getOrigin());
            }
            System.out.println();

            // Replace the existing bear in the list with the modified bear
            bears.set(i, bear);
        }
    }

}