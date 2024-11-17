package oncall.model;

import java.util.Deque;

public class Worker {
    private final Deque<String> workers;

    public Worker(Deque<String> workers){
        this.workers = workers;
    }

    public Deque<String> getWorkers() {
        return workers;
    }

    public String getWorker(){
        return workers.getFirst();
    }

    public String bringOutFirstWorker(){
        String firstWorker = workers.pop();
        workers.addLast(firstWorker);
        return firstWorker;
    }

    public String bringOutSecondWorker(){
        String firstWorker = workers.pop();
        String secondWorker = workers.pop();
        workers.addFirst(firstWorker);
        workers.addLast(secondWorker);
        return secondWorker;
    }
}
