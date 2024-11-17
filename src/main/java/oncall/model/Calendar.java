package oncall.model;

public enum Calendar {
    JANUARY("1",31,new int[]{1}),
    FEBRUARY("2",28,null),
    MARCH("3",31,new int[]{1}),
    APRIL("4",30,null),
    MAY("5",31,new int[]{5}),
    JUNE("6",30,new int[]{6}),
    JULY("7",31,null),
    AUGUST("8",31,new int[]{15}),
    SEPTEMBER("9",30,null),
    OCTOBER("10",31, new int[]{3, 9}),
    NOVEMBER("11",30,null),
    DECEMBER("12",31,new int[]{25}),
    DEFAULT("",0,null);

    private final String month;
    private final int totalDate;
    private final int[] legalHoliday;


    Calendar(String month, int totalDate, int[] legalHoliday){
        this.month = month;
        this.totalDate = totalDate;
        this.legalHoliday = legalHoliday;
    }

    public int getTotalDate(String inputMonth){
        for(Calendar calendar:Calendar.values()){
            if(inputMonth.equals(calendar.month)){
                return calendar.totalDate;
            }
        }
        return DEFAULT.totalDate;
    }

    public int[] getLegalHoliday() {
        return legalHoliday;
    }
}
