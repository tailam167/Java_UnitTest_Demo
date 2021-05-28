package erserver.modules.dependencies;

import java.util.ArrayList;
import java.util.List;

public class BedProviderDouble implements BedProvider {

    private ArrayList<Bed> bedReturn;

    public BedProviderDouble() {
        bedReturn = new ArrayList<>();
    }

    @Override
    public List<Bed> getAllBeds() {
        return bedReturn;
    }
}
