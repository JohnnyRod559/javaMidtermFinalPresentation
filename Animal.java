import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Animal {

    // protected means that all classes that derive from Animal class can access the information, if the class isn't derived from Animal you have to use setters and getters
    protected String animalName;
    protected String animalDesc;
    protected String animalAge; // optional
    private String animalBirthSeason;
    protected String animalGender;
    protected String animalWeight;
    protected String animalColor;
    protected String origin;
    protected String animalID;





    // create variable to keep track of animal count
    // increment each time an animal is created
    // getter to retrieve total animals
    private static int animalCount = 0;
    public Animal(){
        animalCount++;

    }
    public static int getAnimalCount() {
        return animalCount;
    }



// create setters and getters for the attributes

    public void setAnimalDesc(String animalDesc) {
        this.animalDesc = animalDesc;
    }

    public void setAnimalAge(String animalAge) {
        this.animalAge = animalAge;
    }

    public void setAnimalGender(String animalGender) {
        this.animalGender = animalGender;
    }

    public void setAnimalBirthSeason(String animalBirthSeason) {
        this.animalBirthSeason = animalBirthSeason;
    }

    public void setAnimalColor(String animalColor) {
        this.animalColor = animalColor;
    }
    public void setAnimalWeight(String animalWeight) {
        this.animalWeight = animalWeight;
    }

    public void setOrigin(String origin1) {
        this.origin = origin1;
    }

    public void setAnimalID(String animalID) {
        this.animalID = animalID;
    }

    public String getAnimalID() {
        return animalID;
    }

    public String getAnimalDesc() {
        return animalDesc;
    }

    public String getAnimalGender() {
        return animalGender;
    }

    public String getAnimalBirthSeason() {
        return animalBirthSeason;
    }

    public String getAnimalColor() {
        return animalColor;
    }

    public String getAnimalWeight() {
        return animalWeight;
    }

    public String getOrigin() {
        return origin;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getAnimalName() {
        return animalName;
    }







}
