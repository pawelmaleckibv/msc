package master.msc.testData;


import master.msc.model.Machine;
import master.msc.model.MachineGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class TestMachineGroupCreator extends TestDataCreator<MachineGroup> {

    private List<String> machineGroupName;

    {
        String machineGroupNamesTab[] = {"Table drills", "CNC Lathes", "Milling machines", "Welders", "Grinders"};
        this.machineGroupName = Arrays.asList(machineGroupNamesTab);

    }

    @Autowired
    private TestMachineCreator testMachineCreator;

    @Override
    public MachineGroup doCreateEntity() {
        MachineGroup machineGroup = new MachineGroup();

        machineGroup.setName(generateMachineGroupName());

        List<Machine> machineList = testMachineCreator.createCollection(2);
        machineGroup.setMachines(machineList);

        return machineGroup;
    }

    private String generateMachineGroupName() {
        return this.machineGroupName.get(getRandom().nextInt(this.machineGroupName.size()));
    }

}
