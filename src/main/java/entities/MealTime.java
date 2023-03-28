package entities;

public enum MealTime {

    BREAKFAST, SECOND_BREAKFAST, LUNCH, BRUNCH, SNACK, DINNER, MIDNIGHT_SNACK;

    public MealTime stringToEnum(String mealTimeString){
        return switch (mealTimeString.toUpperCase()){
            case "BREAKFAST" -> BREAKFAST;
            case "SECOND_BREAKFAST" -> SECOND_BREAKFAST;
            case "LUNCH" -> LUNCH;
            case "BRUNCH" -> BRUNCH;
            case "SNACK" -> SNACK;
            case "DINNER" -> DINNER;
            case "MIDNIGHT_SNACK" -> MIDNIGHT_SNACK;
            default->throw new IllegalArgumentException("Unexpected value: " + mealTimeString);
        };
    }
}
