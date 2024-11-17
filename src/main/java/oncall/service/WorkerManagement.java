package oncall.service;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import oncall.validation.Validation;

public class WorkerManagement {
    public Deque<String> convertStringToDeque(String input){
        Deque<String> workers = new LinkedList<>();
        Validation.validateInputFormat(input);

        List<String> nickNames = Arrays.stream(input.split(",")).toList();
        for(String nickname : nickNames){
            Validation.validateNickName(nickname);
            if(!workers.contains(nickname)){
                workers.add(nickname);
            }
        }
        Validation.validateWorkerManagement(nickNames.size(),workers.size());
        return workers;
    }
}
