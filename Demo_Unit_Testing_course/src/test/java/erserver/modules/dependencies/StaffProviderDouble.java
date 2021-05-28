package erserver.modules.dependencies;

import java.util.ArrayList;
import java.util.List;

public class StaffProviderDouble implements StaffProvider {

    private ArrayList<Staff> staffReturn;

    public StaffProviderDouble() {
        staffReturn = new ArrayList<>();
    }

    public void addStaffMemberReturn(Staff staff){
        this.staffReturn.add(staff);
    }

    @Override
    public List<Staff> getShiftStaff() {
        return staffReturn;
    }
}
