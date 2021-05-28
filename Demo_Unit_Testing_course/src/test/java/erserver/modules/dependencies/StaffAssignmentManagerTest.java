package erserver.modules.dependencies;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class StaffAssignmentManagerTest {

    @Test
    public void physicianAndResidentsReturnForPhysiciansOnDuty(){
        StaffProviderDouble staffRepoDouble = new StaffProviderDouble();
        staffRepoDouble.addStaffMemberReturn(new Staff(1, "John Doctor", StaffRole.DOCTOR));
        staffRepoDouble.addStaffMemberReturn(new Staff(2, "Frank Resident", StaffRole.RESIDENT));
        StaffAssignmentManager manager = new StaffAssignmentManager(staffRepoDouble, new BedProviderDouble());
        List<Staff> physiciansOnDuty = manager.getPhysiciansOnDuty();
        assertNotNull(physiciansOnDuty);
        assertEquals(2, physiciansOnDuty.size());
        assertEquals(1, physiciansOnDuty.get(0).getStaffId());
        assertEquals(2, physiciansOnDuty.get(1).getStaffId());
    }

    @Test
    public void testWithMockito(){
        ArrayList<Staff> staff = new ArrayList<>();
        ArrayList<Bed> beds = new ArrayList<>();
        staff.add(new Staff(1, "John Doctor", StaffRole.DOCTOR));
        staff.add(new Staff(2, "Frank Resident", StaffRole.RESIDENT));
        StaffProvider staffProviderMock = Mockito.mock(StaffProvider.class);
        BedProvider bedProviderMock = Mockito.mock(BedProvider.class);
        Mockito.when(staffProviderMock.getShiftStaff()).thenReturn(staff);
        Mockito.when(bedProviderMock.getAllBeds()).thenReturn(beds);
        StaffAssignmentManager manager = new StaffAssignmentManager(staffProviderMock, bedProviderMock);
        List<Staff> physiciansOnDuty = manager.getPhysiciansOnDuty();
        assertNotNull(physiciansOnDuty);
        assertEquals(2, physiciansOnDuty.size());
        assertEquals(1, physiciansOnDuty.get(0).getStaffId());
        assertEquals(2, physiciansOnDuty.get(1).getStaffId());
    }
}