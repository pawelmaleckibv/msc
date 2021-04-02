package master.msc.model;


import master.msc.enums.Role;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "functionalities")
public class Functionality extends MscBaseEntity {

    private String name;

    public Functionality() {
    }

    public Functionality(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transient
    public static List<Functionality> functionalitiesForRole(Role role, boolean isAdmin) {
        List<Functionality> functionalityList = new ArrayList<>();
        switch (role) {
            case EXECUTOR:
                functionalityList.addAll(executorFunctionalities());
                break;
            case STAFF:
                functionalityList.addAll(staffFunctionalities());
                break;
            case ORGANIZATION_ADMIN:
                functionalityList.addAll(organisationAdminFunctionalities());
                break;
        }

        if(functionalityList.size() == 0 && isAdmin) {
            functionalityList = adminFunctionalities();
        }
        return functionalityList;
    }

    private static List<Functionality> executorFunctionalities() {
        return createFunctionalityList(
                FunctionalityConstants.MY_ORGANIZATIONS,
                FunctionalityConstants.MY_ORGANIZATION,
                FunctionalityConstants.PROFILE,
                FunctionalityConstants.TASKS,
                FunctionalityConstants.TERM_DEFINITION);
    }

    private static List<Functionality> staffFunctionalities() {
        return createFunctionalityList(
                FunctionalityConstants.MY_ORGANIZATIONS,
                FunctionalityConstants.MY_ORGANIZATION,
                FunctionalityConstants.PROFILE,
                FunctionalityConstants.QUESTIONNAIRES,
                FunctionalityConstants.TERM_DEFINITION);

    }

    private static List<Functionality> organisationAdminFunctionalities() {
        return createFunctionalityList(
                FunctionalityConstants.MY_ORGANIZATIONS,
                FunctionalityConstants.MY_ORGANIZATION,
                FunctionalityConstants.PROFILE,
                FunctionalityConstants.QUESTIONNAIRES_ADMINISTRATION,
                FunctionalityConstants.EMPLOYMENT_MANAGEMENT,
                FunctionalityConstants.CURRENT_EVALUATION,
                FunctionalityConstants.TERM_DEFINITION);
    }

    private static List<Functionality> adminFunctionalities() {
        return createFunctionalityList(
                FunctionalityConstants.MY_ORGANIZATIONS,
                FunctionalityConstants.PROFILE,
                FunctionalityConstants.ORGANIZATIONS_ADMINISTRATION,
                FunctionalityConstants.EMPLOYMENT_MANAGEMENT,
                FunctionalityConstants.PERSON_MANAGEMENT,
                FunctionalityConstants.CONFIGURATION_VARIABLES,
                FunctionalityConstants.TERM_DEFINITION);
    }

    private static List<Functionality> createFunctionalityList(String mandatory, String... optionals) {
        List<Functionality> functionalities = new ArrayList<>();
        functionalities.add(new Functionality(mandatory));
        for (String optional : optionals) {
            functionalities.add(new Functionality(optional));
        }
        return functionalities;
    }
}
