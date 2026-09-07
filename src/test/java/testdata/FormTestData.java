package testdata;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class FormTestData {
    private final Faker faker = new Faker();
    Random random = new Random();

    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String invalidUserEmail = faker.lorem().word();
    public String correctUserEmail = faker.internet().emailAddress();
    public String[] genders = {"Male", "Female", "Other"};
    public String gender = setRandomGender();
    public String userNumber = faker.phoneNumber().subscriberNumber(10);
    public String day;
    public String month;
    public String year;
    public String currentAddress = faker.address().fullAddress();
    public String[] subjects = {"Physics",
                                "Chemistry",
                                "Commerce",
                                "Economics",
                                "English",
                                "Arts",
                                "Maths",
                                "Computer Science",
                                "Social studies",
                                "History",
                                "Accounting",
                                "Hindi",
                                "Civics"};
    public String selectedSubject = setRandomSubject();
    public String[] hobbies = {"Sports", "Reading", "Music"};
    public String selectedHobby = setRandomHobbies();
    public String picture = "picture.jpg";
    public String state;
    public String city;

    public FormTestData() {
        Date birthDate = faker.date().birthday();
        day = new SimpleDateFormat("dd").format(birthDate);
        month = new SimpleDateFormat("MMMM", Locale.ENGLISH).format(birthDate);
        year = new SimpleDateFormat("yyyy").format(birthDate);
    }

    public String setRandomGender() {
        int index = random.nextInt(genders.length);
        return genders[index];
    }

    public String setRandomSubject() {
        int index = random.nextInt(subjects.length);
        return subjects[index];
    }

    public String setRandomHobbies() {
        int index = random.nextInt(hobbies.length);
        return hobbies[index];
    }

    private static final Map<String, String[]> statesAndCities = Map.of(
            "NCR", new String[]{"Delhi", "Gurgaon", "Noida"},
            "Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"},
            "Haryana", new String[]{"Karnal", "Panipat"},
            "Rajasthan", new String[]{"Jaipur", "Jaiselmer"}
    );

    public String setRandomState() {
        state = faker.options().option(statesAndCities.keySet().toArray(new String[0]));
        return state;
    }

    public String setRandomCity() {
        city = faker.options().option(statesAndCities.get(state));
        return city;
    }

    public static String firstNameLocator = "firstName";
    public static String lastNameLocator = "lastName";
    public static String userNumberLocator = "userNumber";
    public static String userEmailLocator = "userEmail";
}
