package master.msc.testData;


import master.msc.model.Organization;
import master.msc.testData.TestDataCreator;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class TestOrganizationCreator extends TestDataCreator<Organization> {

    @Override
    public Organization doCreateEntity() {
        Organization organization = new Organization();
        organization.setName(drawOrganizationName());
        organization.setMission(drawOrganizationMission());
        organization.setVision(drawOrganisationVision());
        organization.setStrategy(drawOrganisationStrategy());
        organization.setBusinessLine(drawOrganisationLineOfBusiness());
        organization.setDeleted(false);
        return organization;
    }

    private String drawOrganizationName() {
        String s = System.getProperty("user.dir") + String.join(File.separator, "", "src", "main", "resources");
        List<String> organizationNames = parseLinesToList(s+ "\\organization\\organization_names.txt");
        return organizationNames.get(getRandom().nextInt(organizationNames.size()));
    }

    private String drawOrganizationMission() {
        String s = System.getProperty("user.dir") + String.join(File.separator, "", "src", "main", "resources");
        List<String> organizationMission = parseLinesToList(s+ "\\organization\\organization_missions.txt");
        return organizationMission.get(getRandom().nextInt(organizationMission.size()));
    }

    private String drawOrganisationVision() {
        String s = System.getProperty("user.dir") + String.join(File.separator, "", "src", "main", "resources");
        List<String> organizationVision = parseLinesToList(s+ "\\organization\\organization_visions.txt");
        return organizationVision.get(getRandom().nextInt(organizationVision.size()));
    }

    private String drawOrganisationStrategy() {
        String s = System.getProperty("user.dir") + String.join(File.separator, "", "src", "main", "resources");
        List<String> organizationStrategy = parseLinesToList(s+ "\\organization\\organization_stratiegies.txt");
        return organizationStrategy.get(getRandom().nextInt(organizationStrategy.size()));
    }

    private String drawOrganisationLineOfBusiness() {
        String s = System.getProperty("user.dir") + String.join(File.separator, "", "src", "main", "resources");
        List<String> organizationLineOfBusiness = parseLinesToList(s+ "\\organization\\organization_business_lines.txt");
        return organizationLineOfBusiness.get(getRandom().nextInt(organizationLineOfBusiness.size()));
    }

}

