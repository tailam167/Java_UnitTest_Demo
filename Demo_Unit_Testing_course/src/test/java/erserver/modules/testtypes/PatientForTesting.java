package erserver.modules.testtypes;

import java.time.LocalDate;

public class PatientForTesting extends Patient {

    private LocalDate localDate;

    public PatientForTesting() {
        this.localDate = LocalDate.now();
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public LocalDate getSystemCurrentDate() {
        return localDate;
    }
}
