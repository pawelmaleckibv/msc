package master.msc.testData;


import com.thedeanda.lorem.LoremIpsum;
import master.msc.enums.Role;
import master.msc.model.Employment;
import master.msc.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Component
public class TestPersonCreator extends TestDataCreator<Person> {

    @Autowired
    TestUserEmploymentCreator testUserEmploymentCreator;

    @Autowired
    TestOrganizationCreator testOrganizationCreator;

    private List<String> salutations;

    {
        String salutations[] = {"Mr.", "Mrs.", "Miss", "Ms."};
        this.salutations = Arrays.asList(salutations);
    }


    @Override
    public Person doCreateEntity() {
        Person person = createPersonWithoutEmployment();
        person.setEmail(generateEmail());
        person.setDefaultLanguage(generateLocale());
        return person;
    }

    private String generateSalutation() {
        return this.salutations.get(getRandom().nextInt(this.salutations.size()));
    }

    private String getInitials(String firstName, String lastName) {
        return firstName.charAt(0) + "" + lastName.charAt(0) + "";
    }

    private LocalDate generateDateOfBirth() {
        return LocalDate.now()
                .minusYears(getRandom().nextInt(100))
                .minusMonths(getRandom().nextInt(12))
                .minusDays(getRandom().nextInt(31));
    }


    private Locale generateLocale() {
        return getRandom().nextBoolean() ? Locale.UK : Locale.GERMANY;
    }

    private String generateEmail() {
        return removeUmlauts("user" + getRandom().nextInt(1000) + "@private-mail.com");

    }

    private String removeUmlauts(String mail){
        String mail1 = mail.replace("ä","ae");
        String mail2 = mail1.replace("ü","ue");
        return mail2.replace("ö ","oe");
    }

    public Employment createPersonWithEmploymentId(String personName, String employmentId) {
        Person person = createPersonWithoutEmployment();
        person.setEmail(personName.toLowerCase());
        Employment employment = testUserEmploymentCreator.getEntityManager().find(Employment.class, UUID.fromString(employmentId));
        employment.setPerson(person);
        person.addEmployments(employment);
        if(person.getIsAdmin()) {
            employment.setRole(Role.ADMINISTRATOR);
        }
        return employment;
    }

    public Person createPersonWithoutEmployment() {
        Person person = new Person();
        person.setFirstName(LoremIpsum.getInstance().getFirstName());
        person.setLastName(LoremIpsum.getInstance().getLastName());
        person.setSalutation(generateSalutation());
        person.setInitials(getInitials(person.getFirstName(), person.getLastName()));
        person.setDateOfBirth(generateDateOfBirth());
        person.setDefaultLanguage(generateLocale());
        person.setEmail(generateEmail());
        person.setPassword("ml1234");
        return person;
    }


}
