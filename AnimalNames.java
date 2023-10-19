import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimalNames {

    //data structure used to store key-value pairs where each key is associated with a list of values.
    // associates names to species
    private static Map<String, List<String>> speciesNames = new HashMap<>();

    static {
        loadNamesFromFile();
    }

    public static void loadNamesFromFile() {
        String filePath ="C:\\Users\\Johnny\\JavaFiles\\animalNames.txt" ; // Update with the actual file path

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // current cpecies is the key and current names are the value
            String currentSpecies = null;
            List<String> currentNames = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                line = line.trim();
                // finds "Names:" in file and uses it to determine new species line
                if (line.endsWith(" Names:")) {
                    // if current species is not null put it as key in map data structure
                    if (currentSpecies != null) {
                        speciesNames.put(currentSpecies, currentNames);
                    }

                    currentSpecies = line.replace(" Names:", "").trim();
                    // array list to hold names
                    currentNames = new ArrayList<>();
                    // if current species is found and line with names is found then add names to current names.
                } else if (currentSpecies != null && !line.isEmpty()) {
                    currentNames.add(line);
                }
            }

            // check to see if there are anymore species if not then the file is done
            if (currentSpecies != null) {
                speciesNames.put(currentSpecies, currentNames);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getNamesForSpecies(String species) {
        return speciesNames.get(species);
    }
}