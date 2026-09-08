package observer;

import event.TrafficSignalEvent;

public interface SignalChangeObserver {

    void update(TrafficSignalEvent signalEvent);

}
