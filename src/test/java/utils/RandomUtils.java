package utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
    public static int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, (max + 1));
    }

    public static String getRandomDay(){
        return String.format("%s%s", getRandomNumber(0, 2), getRandomNumber(1, 8));
    }

    public static String getRandomMonth(){
        String[] months = {
                "January", "February",
                "March", "April", "May",
                "June", "July", "August",
                "September", "October", "November",
                "December"
        };
        return getRandomArrayItem(months);
    }

    public static String getRandomYear(){
        return String.valueOf(getRandomNumber(1960, 2007));
    }

    public static String getRandomPhoneNumber(){
        return String.format("%s%s", getRandomNumber(10000, 99990), getRandomNumber(10000, 99990));
    }

    public static String getRandomGender(){
        String[] genders = {"Male", "Female", "Other"};
        return getRandomArrayItem(genders);
    }

    public static String getRandomHobby(){
        String[] hobbies = {"Sports", "Reading", "Music"};
        return getRandomArrayItem(hobbies);
    }

    public static String getRandomSubject(){
        String[] subjects = {"Biology", "Maths", "English"};
        return getRandomArrayItem(subjects);
    }
    public static String getRandomState(){
        String[] states = {"NCR", "Haryana", "Rajasthan", "Uttar Pradesh"};
        return getRandomArrayItem(states);
    }

    public static String getRandomArrayItem(String[] array){
        int index = getRandomNumber(0, array.length - 1);
        return array[index];
    }

    public static String getCityByState(String state){
        String[] citiesOfNCR = {"Delhi", "Gurgaon", "Noida"};
        String[] citiesOfPradesh = {"Agra", "Lucknow", "Merrut"};
        String[] citiesOfHaryana = {"Karnal", "Panipat"};
        String[] citiesOfRajasthan = {"Jaipur", "Jaiselmer"};

        return switch (state) {
            case "NCR" -> getRandomArrayItem(citiesOfNCR);
            case "Uttar Pradesh" -> getRandomArrayItem(citiesOfPradesh);
            case "Haryana" -> getRandomArrayItem(citiesOfHaryana);
            case "Rajasthan" -> getRandomArrayItem(citiesOfRajasthan);
            default -> null;
        };
    }
}
