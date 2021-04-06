package master.msc.testData;


import master.msc.enums.AccountStatus;
import master.msc.enums.Role;
import master.msc.model.Employment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TestUserEmploymentCreator extends TestDataCreator<Employment> {

    private static List<Role> roles = new ArrayList<>();
    private List<Employment> employmentList = new ArrayList<>();

    {
        roles.add(Role.EXECUTOR);
        roles.add(Role.STAFF);
        roles.add(Role.ORGANIZATION_ADMIN);
    }

    @Override
    public Employment doCreateEntity() {
        Role role = roles.get(getRandom().nextInt(roles.size()));
        Employment employment = new Employment(role);
        employment.setEmail(generateEmail());
        employment.setExpirationDate(generateExpirationDate());
        employment.setAccountStatus(generateAccountStatus());
        employment.setIsTutorialWatched(true);
        employment.setDeleted(false);
        return employment;
    }

    public void setUpEmploymentsList() {
        employmentList.add(getEntityManager().find(Employment.class, UUID.fromString("3d29bffa-5c42-4e7b-aa63-9a2208011694")));
        employmentList.add(getEntityManager().find(Employment.class, UUID.fromString("47a917f0-4784-439d-80c9-e0a6ff1ed92e")));
        employmentList.add(getEntityManager().find(Employment.class, UUID.fromString("18610c8a-3256-43a0-8c16-596b42a3809d")));
        employmentList.add(getEntityManager().find(Employment.class, UUID.fromString("46f63bd1-4361-4329-bc3f-4cc6875efb18")));
    }

    private String generateEmail() {
        return ("user" + getRandom().nextInt(10000) + "@mail.com");
    }

    private LocalDateTime generateExpirationDate() {
        return LocalDateTime.now().plusMonths(getRandom().nextInt(12));
    }

    private AccountStatus generateAccountStatus() {
        int statusIndex = getRandom().nextInt(1);
        return AccountStatus.values()[statusIndex];
    }
}
