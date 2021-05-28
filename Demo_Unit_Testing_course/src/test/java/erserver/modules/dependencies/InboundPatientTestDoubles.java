package erserver.modules.dependencies;

import erserver.modules.testtypes.Patient;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class InboundPatientTestDoubles implements InboundPatientSource{

    private ArrayList<Patient> patientList;
    private EmergencyResponseService transportService;

    public InboundPatientTestDoubles() {
        this.patientList = new ArrayList<>();
        this.patientList.add(createTestPatient(11, Priority.RED, "shortness of break"));
        this.patientList.add(createTestPatient(12, Priority.YELLOW, "heart arrhythmia"));
    }

    /**
     *
     * @param transportId
     * @param priority
     * @param condition
     * @return
     */
    private Patient createTestPatient(int transportId, Priority priority, String condition) {
        Patient patient = new Patient();
        patient.setTransportId(transportId);
        patient.setPriority(priority);
        patient.setCondition(condition);
        return patient;
    }

    /**
     * Create new
     *
     * @param patient
     * @return patient
     */
    public void simulateNewInboundPatient(Patient patient) {
        patientList.add(patient);
    }

    @Override
    public List<Patient> currentInboundPatients() {
        return patientList;
    }

    @Override
    public void informOfPatientArrival(int transportId) {
        transportService.informOfArrival(transportId);
    }
}
