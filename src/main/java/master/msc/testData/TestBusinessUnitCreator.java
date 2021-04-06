package master.msc.testData;

import master.msc.model.BusinessUnit;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;


@Component
public class TestBusinessUnitCreator extends TestDataCreator<BusinessUnit> {

    @Override
    @Transactional
    public BusinessUnit doCreateEntity() {

        BusinessUnit businessUnit = new BusinessUnit();

        businessUnit.setName(drawBusinessUnitName());

        return businessUnit;
    }

    private String drawBusinessUnitName() {
        String s = System.getProperty("user.dir") + String.join(File.separator, "", "src", "main", "resources");
        List<String> businessUnitNames = parseLinesToList(s+"\\business_unit\\business_unit_name.txt");
        return businessUnitNames.get(getRandom().nextInt(businessUnitNames.size()));
    }

}




