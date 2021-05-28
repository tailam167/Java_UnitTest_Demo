package erserver.modules.testtypes;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DosingCalculatorTest {

    private DosingCalculator dosingCalculator;
    private PatientForTesting patient;

    @Before
    public void setUp() {
        dosingCalculator = new DosingCalculator();
        patient = new PatientForTesting();
    }

    @Test
    public void returnsCorrectDosesForNeonateByDate() {
//      patient.setBirthDate(LocalDate.now().minusDays(28));
        patient.setBirthDate(LocalDate.of(2016,2,28));
        patient.setLocalDate(LocalDate.of(2016, 3, 28));
        String singleDose = dosingCalculator.getRecommendedSingleDose(patient, "Tylenol Oral Suspension");
        assertEquals("0", singleDose);
    }

    @Test
    public void returnsCorrectDosesForInfant() {
        patient.setBirthDate(LocalDate.now().minusDays(40));
        String singleDose = dosingCalculator.getRecommendedSingleDose(patient, "Tylenol Oral Suspension");
        assertEquals("2.5 ml", singleDose);
    }

    @Test
    public void returnsCorrectDosesForChild() {
        patient.setBirthDate(LocalDate.now().minusYears(3));
        String singleDose = dosingCalculator.getRecommendedSingleDose(patient, "Tylenol Oral Suspension");
        assertEquals("5 ml", singleDose);
    }

    @Test
    public void returnsCorrectDosesForNeonateAmox() {
        patient.setBirthDate(LocalDate.now().minusDays(20));
        String singleDose = dosingCalculator.getRecommendedSingleDose(patient, "Amoxicillin Oral Suspension");
        assertEquals("15 mg/kg", singleDose);
    }

    @Test(expected = RuntimeException.class)
    public void returnsExceptionForAdults() {
        patient.setBirthDate(LocalDate.now().minusYears(16));
        dosingCalculator.getRecommendedSingleDose(patient, "Amoxicillin Oral Suspension");
    }

    @Test(expected = RuntimeException.class)
    public void returnsNullForUnrecognizedMedication() {
        patient.setBirthDate(LocalDate.now().minusYears(16));
        dosingCalculator.getRecommendedSingleDose(patient, "No Such Med");
    }
}