package entity;

import enums.Signal;

public class TrafficLight {

    private final int id;
    private Signal signal;
    private int duration;

    public TrafficLight(int id) {
        this.id = id;
    }

    public void setSignal(Signal signal, int duration) {
        this.signal = signal;
        this.duration = duration;
        // call method
        // that handles further process
        signalChanged();
    }

    private void signalChanged() {

        // TODO: keep the signal for the duration set
        //  when duration is over
        //  tell event manager about your
        //  id, signal (that has just been completed)
        int durationSeconds = this.duration;
        long endTime = System.nanoTime() + durationSeconds * 1_000_000_000L;

        while (System.nanoTime() < endTime) {
            try {
                Thread.sleep(1000);
                this.duration--;
            } catch (InterruptedException e) {
                System.out.println("Error occurred while signal was open...");
            }
        }
        // once out of the loop, tell event manager that
        // duration for signal is elapsed

    }

    public SignalAndDuration getSignalAndDuration() {
        return new SignalAndDuration(this.signal, this.duration);
    }

    static class SignalAndDuration {

        private Signal signal;
        private int duration;

        SignalAndDuration(Signal signal, int duration) {
            this.signal = signal;
            this.duration = duration;
        }

        public Signal getSignal() {
            return signal;
        }

        public int getDuration() {
            return duration;
        }

    }

}
