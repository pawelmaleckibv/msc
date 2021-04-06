package master.msc.testData;

import com.blueveery.core.model.BaseEntity;
import master.msc.model.BusinessUnit;
import master.msc.testData.TestBusinessUnitCreator;
import master.msc.testData.TestDataCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestUnitsImporter extends TestDataCreator {

    @Autowired
    TestBusinessUnitCreator testBusinessUnitCreator;

    public List<BusinessUnit> importBusinessUnits() {
        List<BusinessUnit> businessUnits = new ArrayList<>();
        for(String name : readBusinessUnitNames()){
            BusinessUnit businessUnit = new BusinessUnit();
            businessUnit.setName(name);
            businessUnits.add(businessUnit);
        }
        return businessUnits;
    }

    @Override
    protected BaseEntity doCreateEntity() {
        return null;
    }

    protected List<String> readBusinessUnitNames() {
        String s = System.getProperty("user.dir") + String.join(File.separator, "", "src", "main", "resources");
        return parseLinesToList(s+"\\business_unit\\business_unit_name.txt");
    }

}
