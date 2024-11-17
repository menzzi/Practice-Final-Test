package oncall.controller;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import oncall.model.Calendar;
import oncall.model.Worker;
import oncall.service.CalendarManagement;
import oncall.service.WorkerManagement;
import oncall.validation.Validation;
import oncall.view.InputView;
import oncall.view.OutputView;

public class EmergencyWorkController {
    private final InputView input;
    private final OutputView output;

    private final String[] dayOfWeek = new String[]{"일", "월", "화", "수", "목", "금", "토"};

    public EmergencyWorkController(InputView input,OutputView output) {
        this.input = input;
        this.output = output;
    }

    public void run() {
        try{
            proceed();
        }catch (IllegalArgumentException e){
            output.printErrorMessage(e.getMessage());
            proceed();
        }
    }

    private void proceed() {
        boolean isInputComplete = false;
        while(!isInputComplete) {
            String[] dateInformation = input.inputDateInformation();
            Calendar month = Calendar.getCalendar(dateInformation[0]);
            String startDay = dateInformation[1];
            Validation.validateDayOfWeek(findDayIndex(startDay));
            dealWithWorker(month,startDay);
            isInputComplete = true;
        }
    }

    private void dealWithWorker(Calendar month, String startDay){
        boolean isInputWorkerComplete = false;
        while(!isInputWorkerComplete){
            try{
                Deque<String> weekdayWorker = WorkerManagement.convertStringToDeque(input.inputWeekdayWorker());
                Deque<String> weekendDayWorker = WorkerManagement.convertStringToDeque(input.inputWeekendWorker());
                Worker weekdayWorkers = new Worker(weekdayWorker);
                Worker weekendWorkers = new Worker(weekendDayWorker);
                Validation.validateWorkers(weekdayWorkers,weekendWorkers);
                adjustEmergencyWorkSchedule(weekdayWorkers,weekendWorkers,month,startDay);
                isInputWorkerComplete = true;
            }catch(IllegalArgumentException e){
                output.printErrorMessage(e.getMessage());
            }
        }
    }

    private void adjustEmergencyWorkSchedule(Worker weekdayWorkers, Worker weekendWorkers, Calendar month, String startDay) {
        int index = findDayIndex(startDay);
        List<String> confirmedWorker = new ArrayList<>();

        for (int date = 1; date <= month.getTotalDate(); date++) {
            String mark = CalendarManagement.markHoliday(dayOfWeek[index],date, month.getLegalHoliday());
            boolean isWeekday = isWeekday(mark,index);
            String workerName = appointWorker(confirmedWorker,weekdayWorkers,weekendWorkers,isWeekday);
            confirmedWorker.add(workerName);
            output.printEmergencySchedule(month.getMonth(), date, mark, workerName);
            index ++;
            index %= 7;
        }
    }

    private String appointWorker(List<String> confirmedWorker, Worker weekdayWorkers, Worker weekendWorkers, boolean isWeekday){
        if(isWeekday){
            String workerName = weekdayWorkers.getWorker();
            if(isContinuousWork(confirmedWorker, workerName)){
                return weekdayWorkers.bringOutSecondWorker();
            }
            return weekdayWorkers.bringOutFirstWorker();
        }
        String workerName = weekendWorkers.getWorker();
        if(isContinuousWork(confirmedWorker, workerName)){
            return weekendWorkers.bringOutSecondWorker();
        }
        return weekendWorkers.bringOutFirstWorker();
    }

    private boolean isContinuousWork(List<String> confirmedWorker, String workerName){
        if(!confirmedWorker.isEmpty()){
            if(confirmedWorker.get(confirmedWorker.size()-1).equals(workerName)){
                return true;
            }
        }
        return false;
    }

    private boolean isWeekday(String mark, int index){
        if(index == 0 || index == 6 || mark.length() > 1){
            return false;
        }
        return true;
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
