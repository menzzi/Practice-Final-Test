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
        return workers.peek();
    }

    public String bringOutFirstWorker(){
        String firstWorker = workers.getFirst();
        workers.addLast(firstWorker);
        return firstWorker;
    }

    public String bringOutSecondWorker(){
        String firstWorker = workers.getFirst();
        String secondWorker = workers.getFirst();
        workers.addFirst(firstWorker);
        workers.addLast(secondWorker);
        return secondWorker;
    }
}
