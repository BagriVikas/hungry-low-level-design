import enums.Signal;

import java.util.ArrayList;
import java.util.List;

public class SignalEventManger {

    private static SignalEventManger signalEventManger;
    private List<SignalEventObserver> signalEventObservers;

    private SignalEventManger() {
        this.signalEventObservers = new ArrayList<>();
    }

    public synchronized static SignalEventManger getInstance() {

        if (null == signalEventManger) {
            signalEventManger = new SignalEventManger();
        }
        return signalEventManger;

    }

    public static void manageSignalEvent(int trafficLightId, Signal signal) {

        if (Signal.RED.equals(signal)) {

        }

    }

    public void addSignalEventObserver(Si)

}
