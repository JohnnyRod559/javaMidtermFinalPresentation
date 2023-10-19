import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tiger extends Animal{

    private static int numbOfTigers = 0;

    public static void incrementNumOfTigers(){
        numbOfTigers++;
    }
    public static int getNumOfTiger() {
        return numbOfTigers;
    }

   public static String sound = "Meeeeeooooowwww";

    static Set<String> usedTigerNames = new HashSet<>();
    static String generateUniqueName(List<String> tigerNamesList) {
        Random random = new Random();
        String randomTigerName = null;

        // Keep generating a random name until an unused one is found
        do {
            int randomIndex = random.nextInt(tigerNamesList.size());
            randomTigerName = tigerNamesList.get(randomIndex);
        } while (usedTigerNames.contains(randomTigerName));

        // Add the used name to the set to prevent duplicates
        usedTigerNames.add(randomTigerName);

        return randomTigerName;
    }



    public static void displayUpdatedTigers(List<Animal> tigers, Pattern agePattern, List<String> tigerNamesList) {
        System.out.println("\nTigers list: \n");

        int tigerCounter = 0; // Initialize a counter

        for (int i = 0; i < tigers.size(); i++) {
            Animal tiger = tigers.get(i); // Get the current tiger from the list



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

                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
                String formattedBirthdate = sdf.format(birthdate);

                // Display the updated information for the tiger
                System.out.println(tiger.getAnimalID() + "; " + tiger.getAnimalName() + "; " +
                        age + " years old; birth date " + formattedBirthdate + "; " +
                        tiger.getAnimalColor() + "; " +
                        tiger.getAnimalGender() + "; " +
                        tiger.getAnimalWeight() + "; " + "Sound: " + sound + "; " +
                        tiger.getOrigin());
            }
            System.out.println();

            // Replace the existing tiger in the list with the modified tiger
            tigers.set(i, tiger);
        }
    }
}