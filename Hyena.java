import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hyena extends Animal{

    // create counter to keep track of hyena count
    // it needs to increment each time a new hyena is created
    // getter for retrieving hyena count in other parts of the program
    public static void incrementNumOfHyenas() {
        numOfHyenas++;

    }
    public static int getNumOfHyenas(){
        return numOfHyenas;
    }
    private static int numOfHyenas = 0;

public static String sound = "HAHAHAHEHEHEHE";


    // declare a static set 'usedhyenanames' to keep track of names so there are no duplicates when assigning to a hyena
    static Set<String> usedHyenaNames = new HashSet<>(); // hash set is used to store a collection of elements
    static Random random = new Random();
    // Private method to generate a unique name for a Hyena
    static String generateUniqueName(List<String> hyenaNamesList) {
        //random used to generate a random number that is used to select random name form hyenanameslist

        String randomHyenaName = null;

        // Keep generating a random name until an unused one is found, if names is present in used hyena names then it keeps trying to grab an unused name
        do {
            int randomIndex = random.nextInt(hyenaNamesList.size());
            randomHyenaName = hyenaNamesList.get(randomIndex);
        } while (usedHyenaNames.contains(randomHyenaName));

        // Add the used name to the set to prevent duplicates
        usedHyenaNames.add(randomHyenaName);

        return randomHyenaName;
    }


    // function uses three parameters
    // list of animal objects representing hyenas
    // agepattern - regular expression pattern used to extract age
    // hyenanamelist, which is the string list containing list of hyena names
    public static void displayUpdatedHyenas(List<Animal> hyenas, Pattern agePattern, List<String> hyenaNamesList) {
        System.out.println("\nHyenas List:\n");

        for (int i = 0; i < hyenas.size(); i++) { // uses the size of the hyenas as its boundaries for displaying each hyena
            Animal hyena = hyenas.get(i); // Get the current hyena from the list


            // correctly extracts age
            Matcher matcher = agePattern.matcher(hyena.getAnimalDesc());
            // calculates birthdate
            if (matcher.find()) {
                String age = matcher.group(1);
                String birthSeason = hyena.getAnimalBirthSeason();
                Date birthdate;
                hyena.setAnimalName(Hyena.generateUniqueName(hyenaNamesList));
                if (!"unknown birth season".equalsIgnoreCase(birthSeason)) {
                    birthdate = AnimalUtils.calculateBirthdate(Integer.parseInt(age), birthSeason);
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.YEAR, -Integer.parseInt(age));
                    calendar.set(Calendar.MONTH, Calendar.JANUARY);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);
                    birthdate = calendar.getTime();
                }
                // set the format for how you want the birthdate to be displayed
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
                String formattedBirthdate = sdf.format(birthdate);

                // Display the updated information for the hyena
                System.out.println(hyena.getAnimalID() + "; " + hyena.getAnimalName() + "; " +
                        age + " years old; birth date " + formattedBirthdate + "; " +
                        hyena.getAnimalColor() + "; " +
                        hyena.getAnimalGender() + "; " +
                        hyena.getAnimalWeight() + "; " + "Sound: " + sound + "; " +
                        hyena.getOrigin());


            }
            System.out.println();
        }
    }

}


