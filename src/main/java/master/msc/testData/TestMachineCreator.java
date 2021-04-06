package master.msc.testData;

import master.msc.model.Machine;
import org.springframework.stereotype.Component;


@Component
public class TestMachineCreator extends TestDataCreator<Machine> {

    @Override
    public Machine doCreateEntity() {

        Machine machine = new Machine();
        machine.setName("Machine_"+ getRandom().nextInt(10));
        return machine;
    }

}
