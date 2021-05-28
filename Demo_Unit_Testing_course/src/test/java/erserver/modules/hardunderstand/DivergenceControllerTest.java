package erserver.modules.hardunderstand;

import erserver.modules.dependencies.Priority;
import erserver.modules.testtypes.Patient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DivergenceControllerTest {

    private DivergenceController divergenceController;
    private ArrayList<Patient> inPatientArrayList;

    @Before
    public void setUp() throws Exception {
        divergenceController = new DivergenceController();
        inPatientArrayList = new ArrayList<>();
    }

    @Test
    public void testGreenPatientsNotRequiringBedFiltered() {
        Patient nonEmerPatient = createPatient(Priority.GREEN, "non-emergency situation, patient is ambulatory");
        inPatientArrayList.add(nonEmerPatient);
        inPatientArrayList.add(createPatient(Priority.RED, "non-emergency situation, patient is ambulatory"));
        inPatientArrayList.add(createPatient(Priority.RED, "non-emergency situation, patient is ambulatory"));
        inPatientArrayList.add(createPatient(Priority.RED, "ambulatory bleeding"));
        inPatientArrayList.add(createPatient(Priority.RED, "non-emergency situation, patient is unable to walk"));
        List<Patient> filterPatientList = divergenceController.patientsAffectingDivergence(inPatientArrayList);
        assertEquals(4, filterPatientList.size());
        assertFalse(filterPatientList.contains(nonEmerPatient));
    }

    private Patient createPatient(Priority priority, String condition){
        Patient patient = new Patient();
        patient.setPriority(priority);
        patient.setCondition(condition);
        return patient;
    }
}