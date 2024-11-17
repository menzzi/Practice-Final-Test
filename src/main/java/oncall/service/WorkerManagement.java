package oncall.service;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class WorkerManagement {
    public Deque<String> convertStringToQueue(String input){
        Deque<String> workers = new LinkedList<>();
        List<String> nickNames = Arrays.stream(input.split(",")).toList();
    }
}
