package erserver.modules.dependencies;

import erserver.modules.testtypes.Patient;
import org.junit.Assert;
import org.junit.Test;

public class AlertScannerTest {

    /**
     * Test scan alert when having patient in red priority status
     *
     * @throws Exception
     */
    @Test
    public void scanAlertsForPriorityRedPatients() throws Exception{
        InboundPatientTestDoubles inboundDoubles = new InboundPatientTestDoubles();
        inboundDoubles.simulateNewInboundPatient(createTestPatient(11, Priority.RED, "stroke"));
        inboundDoubles.simulateNewInboundPatient(createTestPatient(12, Priority.YELLOW, "mid stroke"));
        AlertTransmitterTestDouble transmitterDouble = new AlertTransmitterTestDouble();
        AlertScanner scanner = new AlertScanner(inboundDoubles, transmitterDouble);
        scanner.scan();
        Assert.assertEquals(1, transmitterDouble.getAlertsReceivedRequiringAck().size());
        Assert.assertEquals("111-111-1111: New inbound critical patient: 11",
                transmitterDouble.getAlertsReceivedRequiringAck().get(0));
    }

    /**
     * Test scan alert when having patient in yellow priority status
     *
     * @throws Exception
     */
    @Test
    public void scanAlertsForYellowHeartArrhythmiaPatients() throws Exception{
        InboundPatientTestDoubles inboundDoubles = new InboundPatientTestDoubles();
        inboundDoubles.simulateNewInboundPatient(createTestPatient(11, Priority.GREEN, "shortness of break"));
        inboundDoubles.simulateNewInboundPatient(createTestPatient(12, Priority.YELLOW, "heart arrhythmia"));
        AlertTransmitterTestDouble transmitterDouble = new AlertTransmitterTestDouble();
        AlertScanner scanner = new AlertScanner(inboundDoubles, transmitterDouble);
        scanner.scan();
        Assert.assertEquals(1, transmitterDouble.getAlertsReceived().size());
        Assert.assertEquals("111-111-1111: New inbound critical patient: 12",
                transmitterDouble.getAlertsReceived().get(0));
    }

    /**
     * Create new patient
     *
     * @param transportId
     * @param priority
     * @param condition
     * @return patient
     */
    private Patient createTestPatient(int transportId, Priority priority, String condition) {
        Patient patient = new Patient();
        patient.setTransportId(transportId);
        patient.setPriority(priority);
        patient.setCondition(condition);
        return patient;
    }

    /**
     * Overriding Method
     */
    @Test
    public void scan() {

    }
}