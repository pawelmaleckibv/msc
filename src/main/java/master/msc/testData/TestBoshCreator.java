package master.msc.testData;


import master.msc.model.Organization;
import master.msc.testData.TestDataCreator;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class TestBoshCreator extends TestDataCreator<Organization> {

//    @Autowired
//    AttachmentService attachmentService;

    @Override
    public Organization doCreateEntity() {
        Organization organization = new Organization();
        organization.setName("BOSCH");
        organization.setMission(readOrganizationMission(organization.getName().toLowerCase()));
        organization.setVision(readOrganisationVision(organization.getName().toLowerCase()));
        organization.setStrategy(readOrganisationStrategy(organization.getName().toLowerCase()));
//        OrganizationLogo organizationLogo = (OrganizationLogo) attachmentService.createDoc("png",
//                "boschLogo",
//                "OrganizationLogo",
//                getClass().getClassLoader().getResourceAsStream(\\/organization\\bosch/boschLogo.png"),
//                true);
//        organizationLogo.setOrganization(organization);
        return organization;
    }

    protected String readOrganizationMission(String organization) {
        String s = System.getProperty("user.dir") + String.join(File.separator, "", "src", "main", "resources");
        List<String> organizationMission = parseLinesToList(s+ "\\organization\\" + organization + "\\organization_mission.txt");
        return organizationMission.get(getRandom().nextInt(organizationMission.size()));
    }

    protected String readOrganisationVision(String organization) {
        String s = System.getProperty("user.dir") + String.join(File.separator, "", "src", "main", "resources");
        List<String> organizationVision = parseLinesToList(s+ "\\organization\\" + organization + "\\organization_vision.txt");
        return organizationVision.get(getRandom().nextInt(organizationVision.size()));
    }

    protected String readOrganisationStrategy(String organization) {
        String s = System.getProperty("user.dir") + String.join(File.separator, "", "src", "main", "resources");
        List<String> organizationStrategy = parseLinesToList(s+ "\\organization\\" + organization + "\\organization_strategy.txt");
        return organizationStrategy.get(getRandom().nextInt(organizationStrategy.size()));
    }



}
