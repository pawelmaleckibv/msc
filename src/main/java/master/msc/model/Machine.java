package master.msc.model;


import javax.persistence.*;

@Entity
@Table(name = "machines")
public class Machine extends MscBaseEntity {
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private MachineGroup machineGroup;
    private String name;

    public Machine() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MachineGroup getMachineGroup() {
        return machineGroup;
    }

    public void setMachineGroup(MachineGroup machineGroup) {
        this.machineGroup = machineGroup;
    }
}
