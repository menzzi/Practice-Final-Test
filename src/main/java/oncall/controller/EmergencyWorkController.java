package oncall.controller;

import java.util.Deque;
import oncall.model.Calendar;
import oncall.model.Worker;
import oncall.service.WorkerManagement;
import oncall.validation.Validation;
import oncall.view.InputView;

public class EmergencyWorkController {
    private final InputView input;

    private final String[] dayOfWeek = new String[]{"일", "월", "화", "수", "목", "금", "토"};

    public EmergencyWorkController(InputView input) {
        this.input = input;
    }

    public void run() {
        try{
            proceed();
        }catch (IllegalArgumentException e){
            // 출력
            proceed();
        }
    }

    private void proceed() {
        String[] dateInformation = input.inputDateInformation();
        int totalDate = Calendar.getTotalDate(dateInformation[0]);
        Validation.validateDayOfWeek(findDayIndex(dateInformation[1]));
        dealWithWorker();



    }

    private void dealWithWorker(){
        boolean isInputWorkerComplete = false;
        while(!isInputWorkerComplete){
            try{
                Deque<String> weekdayWorker = WorkerManagement.convertStringToDeque(input.inputWeekdayWorker());
                Deque<String> weekendDayWorker = WorkerManagement.convertStringToDeque(input.inputWeekendWorker());
                Worker weekdayWorkers = new Worker(weekdayWorker);
                Worker weekendWorkers = new Worker(weekendDayWorker);
                Validation.validateWorkers(weekdayWorkers,weekendWorkers);
                isInputWorkerComplete = true;
            }catch(IllegalArgumentException e){
                // 에러 출력
            }
        }

    }

    private void printEmergencyWorkSchedule(String month, int totalDate, String startDay) {
        int index = findDayIndex(startDay);

        for (int i = 1; i < totalDate; i++) {

            // outputview(month,i,dayOfWeek[index],markHoliday, name)

            index %= 7;
        }
    }

    private int findDayIndex(String startDay) {
        for (int i = 0; i < dayOfWeek.length; i++) {
            if(dayOfWeek[i].equals(startDay)){
                return i;
            }
        }
        return -1;
    }
}
