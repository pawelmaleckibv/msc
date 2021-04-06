package master.msc.testData;

import master.msc.enums.Role;
import master.msc.model.Employment;
import master.msc.model.Organization;
import master.msc.model.Person;
import master.msc.testData.ExcelImporter;
import master.msc.testData.TestDataCreator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.List;
import java.util.Locale;


@Component
public class TestUsersImporter extends TestDataCreator<Organization> {

    @Override
    protected Organization doCreateEntity() {
        return null;
    }

    public void importCompanyUsers(Organization organization) {
        importUsersFromFile(organization);
    }

    private List<Person> importUsersFromFile(Organization organization) {
        String s = System.getProperty("user.dir") + String.join(File.separator, "", "src", "main", "resources");
        String filename = s+"\\persons\\" + organization.getName().toUpperCase() + ".xlsx";
        ExcelImporter<Person> personExcelImporter = new ExcelImporter<Person>(filename) {
            @Override
            protected Person handleLine(List<String> cellsInRow) {
                Person person = returnNewPersonFromListOfCells(cellsInRow, organization);
                if (person != null && person.getFirstName() != null) {
                    return person;
                } else {
                    return null;
                }
            }

        };
        try {
            return personExcelImporter.read(1);
//            return personExcelImporter.read("Mastery Data");
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Person returnNewPersonFromListOfCells(List<String> cellsInRow, Organization organization) {
        if (cellsInRow.size() < 4 || cellsInRow.get(0).equals("Nr.")) {
            return null;
        } else if (cellsInRow.size() == 4) {
            String[] array = cellsInRow.get(1).split("\\s+");
            Person person = new Person();
            person.setFirstName(array[0]);
            person.setLastName(array[1]);
            person.setDefaultLanguage(Locale.GERMANY);
            person.setPassword("ml1234");
            Employment employment = new Employment();
            person.getEmployments().add(employment);

            String mail = removeUmlauts(cellsInRow.get(3));

            mail = Normalizer.normalize(mail, Normalizer.Form.NFD);
            mail = mail.replaceAll("\\p{M}", "");
            person.setEmail(mail);
            employment.setPerson(person);

            assignRole(person, cellsInRow.get(2));
            return person;

        } else {
            String[] array = cellsInRow.get(1).split("\\s+");
            Person person = new Person();
            person.setFirstName(array[0]);
            person.setLastName(array[1]);
            person.setDefaultLanguage(Locale.GERMANY);
            person.setPassword("ml1234");
            Employment employment = new Employment();
            person.getEmployments().add(employment);
            String mail = removeUmlauts(cellsInRow.get(4));

            mail = Normalizer.normalize(mail, Normalizer.Form.NFD);
            mail = mail.replaceAll("\\p{M}", "");
            person.setEmail(mail);
            employment.setPerson(person);
            employment.setOrganization(organization);
            assignRole(person, cellsInRow.get(2));
            assignEmploymentToBusinessUnit(organization, cellsInRow.get(3), employment);
            return person;
        }
    }

    private String removeUmlauts(String mail) {
        String mail1 = mail.replace("ä", "ae");
        String mail2 = mail1.replace("ü", "ue");
        return mail2.replace("ö ", "oe");
    }

    private void assignEmploymentToBusinessUnit(Organization organization, String unitName, Employment employment) {
        switch (unitName) {
            case "IT":
                organization.getBusinessUnits().forEach(businessUnit -> {
                    if (businessUnit.getName().contains("IT")) {
                        businessUnit.getEmployments().add(employment);
                    }
                });
                break;
            case "HR":
                organization.getBusinessUnits().forEach(businessUnit -> {
                    if (businessUnit.getName().contains("HR")) {
                        businessUnit.getEmployments().add(employment);
                    }
                });
                break;
            case "Produktion":
                organization.getBusinessUnits().forEach(businessUnit -> {
                    if (businessUnit.getName().contains("Produktion (Fertigung und Montage)")) {
                        businessUnit.getEmployments().add(employment);
                    }
                });
                break;
            case "Produktionsplanung":
                organization.getBusinessUnits().forEach(businessUnit -> {
                    if (businessUnit.getName().contains("Produktionsplanung (langfristig)")) {
                        businessUnit.getEmployments().add(employment);
                    }
                });
                break;
            case "Produktionssteuerung":
                organization.getBusinessUnits().forEach(businessUnit -> {
                    if (businessUnit.getName().contains("Produktionssteuerung (kurzfristig)")) {
                        businessUnit.getEmployments().add(employment);
                    }
                });
                break;
            case "Logistik":
                organization.getBusinessUnits().forEach(businessUnit -> {
                    if (businessUnit.getName().contains("Logistik")) {
                        businessUnit.getEmployments().add(employment);
                    }
                });
                break;
            default:
                throw new IllegalArgumentException("Invalid business unit name: " + unitName);
        }

    }

    private void assignRole(Person person, String germanRoleName) {
        if (germanRoleName.equals("Administrator")) {
            person.setIsAdmin(true);
            getEntityManager().persist(person);
            person.getEmployments().get(0).setRole(Role.ADMINISTRATOR);

        }
        if (germanRoleName.equals("Organisationsadministrator")) {
            person.getEmployments().get(0).setRole(Role.ORGANIZATION_ADMIN);
        }
        if (germanRoleName.equals("Mitarbeiter")) {
            person.getEmployments().get(0).setRole(Role.STAFF);
        }
        if (germanRoleName.equals("Ausführer")) {
            person.getEmployments().get(0).setRole(Role.EXECUTOR);
        }
    }

}
