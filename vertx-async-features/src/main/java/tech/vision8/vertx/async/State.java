package tech.vision8.vertx.async;

import java.time.LocalDateTime;

/**
 * @author vision8
 */
public class State {

    private boolean healthy;

    private LocalDateTime timestamp;

    public static State healthyNow() {

        State state = new State();
        state.setHealthy(true);
        state.setTimestamp(LocalDateTime.now());
        return state;

    }

    public void copyFrom(State state) {
        this.setHealthy(state.isHealthy());
        this.setTimestamp(state.getTimestamp());
    }

    public boolean isHealthy() {
        return healthy;
    }

    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "{ healthy: " + healthy +
                ", timestamp: " + timestamp +
                " }";
    }

}
