package erserver.modules.dependencies;

import java.util.ArrayList;

public class AlertTransmitterTestDouble implements AlertTransmitter {

    private ArrayList<String> alertsReceived;
    private ArrayList<String> alertsReceivedRequiringAck;

    /**
     * Constructor of class AlertTransmitterTestDouble
     */
    public AlertTransmitterTestDouble() {
        this.alertsReceived = new ArrayList<>();
        this.alertsReceivedRequiringAck = new ArrayList<>();
    }

    @Override
    public void transmit(String targetDevice, String pageText) {
        this.alertsReceived.add(targetDevice+ ": " + pageText);
    }

    @Override
    public void transmitRequiringAcknowledgement(String targetDevice, String pageText) {
        this.alertsReceivedRequiringAck.add(targetDevice+ ": " + pageText);
    }

    /**
     * Override method for getting data
     *
     * @return alertsReceived
     */
    public ArrayList<String> getAlertsReceived() {
        return alertsReceived;
    }

    /**
     * Override method for getting data
     *
     * @return alertsReceivedRequiringAck
     */
    public ArrayList<String> getAlertsReceivedRequiringAck() {
        return alertsReceivedRequiringAck;
    }
}
