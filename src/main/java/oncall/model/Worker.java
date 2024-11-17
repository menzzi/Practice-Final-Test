package oncall.model;

import java.util.Queue;

public class Worker {
    private final Queue<String> workers;

    public Worker(Queue<String> workers){
        this.workers = workers;
    }

    public Queue<String> getWorkers() {
        return workers;
    }
}
