package oncall.validation;

public class Validation {
    private static final String INVALIDATE_INPUT = "유효하지 않은 입력 값입니다. 다시 입력해 주세요.";

    public static void validateInputFormat(String input){
        String regex = "^[0-9\\-가-힣,]*$";
        if (input.contains(regex)) {
            throw new IllegalArgumentException(INVALIDATE_INPUT);
        }
    }

    public static void validateMonth(String input){
        String month = input.split(",")[0];
        try{
            int number = Integer.parseInt(month);
            if(number < 1 || number > 12){
                throw new IllegalArgumentException(INVALIDATE_INPUT);
            }
        }catch (NumberFormatException e){
            throw new IllegalArgumentException(INVALIDATE_INPUT);
        }
    }

    public static void validateNickName(String nickName){
        if(nickName.length()>5){
            throw new IllegalArgumentException(INVALIDATE_INPUT);
        }
    }
    public static void validateWorkerManagement(int originalCount, int dequeSize){
        validateDuplicateNickName(originalCount, dequeSize);
        validateWorkerNumber(dequeSize);
    }

    public static void validateDuplicateNickName(int originalCount, int dequeSize){
        if(originalCount != dequeSize){
            throw new IllegalArgumentException(INVALIDATE_INPUT);
        }
    }

    public static void validateWorkerNumber(int dequeSize){
        if(dequeSize < 5 || dequeSize > 35){
            throw new IllegalArgumentException(INVALIDATE_INPUT);
        }
    }
}
