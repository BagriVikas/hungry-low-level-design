package event;

import enums.TrafficSignalEventType;

public class TrafficSignalEvent {

    private final int trafficLightId;
    private final TrafficSignalEventType eventType;

    public TrafficSignalEvent(int trafficLightId, TrafficSignalEventType eventType) {
        this.trafficLightId = trafficLightId;
        this.eventType = eventType;
    }

    public int getTrafficLightId() {
        return trafficLightId;
    }

    public TrafficSignalEventType getEventType() {
        return eventType;
    }

}
