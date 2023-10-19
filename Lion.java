import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lion extends Animal{
    private static int numberOfLions = 0;

    public static void  incrementNumOfLions(){
        numberOfLions++;
    }

    public static int getNumberOfLions(){
        return numberOfLions;
    }


      public static String sound = "RROOOOAAARRRRR";
    static Set<String> usedLionNames = new HashSet<>();

    static String generateUniqueName(List<String> lionNamesList) {
        Random random = new Random();
        String randomLionName = null;

        // Keep generating a random name until an unused one is found
        do {
            int randomIndex = random.nextInt(lionNamesList.size());
            randomLionName = lionNamesList.get(randomIndex);
        } while (usedLionNames.contains(randomLionName));

        // Add the used name to the set to prevent duplicates
        usedLionNames.add(randomLionName);

        return randomLionName;
    }



    public static void displayUpdatedLions(List<Animal> lions, Pattern agePattern, List<String> lionNamesList) {
        System.out.println("\nLions list: \n");

        int lionCounter = 0; // Initialize a counter

        for (int i = 0; i < lions.size(); i++) {
            Animal lion = lions.get(i); // Get the current lion from the list


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

                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
                String formattedBirthdate = sdf.format(birthdate);

                // Display the updated information for the lion
                System.out.println(lion.getAnimalID() + "; " +  lion.getAnimalName() + "; " +
                        age + " years old; birth date " + formattedBirthdate + "; " +
                        lion.getAnimalColor() + "; " +
                        lion.getAnimalGender() + "; " +
                        lion.getAnimalWeight() + "; " + "Sound: " + sound + "; " +
                        lion.getOrigin());
            }
            System.out.println();

            // Replace the existing lion in the list with the modified lion
            lions.set(i, lion);
        }
    }
}