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
}
