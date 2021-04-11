package master.msc.testData;

import master.msc.enums.Role;
import master.msc.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class TestDataInitializer {

    @Autowired
    private DataSource cmmDataSource;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private TestOrganizationCreator testOrganizationCreator;

    @Autowired
    private TestFestoCreator testFestoCreator;


    @Autowired
    private TestBoshCreator testBoshCreator;

    @Autowired
    private TestBusinessUnitCreator testBusinessUnitCreator;

    @Autowired
    private TestUnitsImporter testUnitsImporter;

    @Autowired
    private TestCommentCreator testCommentCreator;

    @Autowired
    private TestMachineGroupCreator testMachineGroupCreator;

    @Autowired
    private TestMaturityEvaluationCreator testMaturityEvaluationCreator;

    @Autowired
    private TestMaturityLevelCreator testMaturityLevelCreator;

    @Autowired
    private TestMaturityLevelTransitionCreator testMaturityLevelTransitionCreator;

    @Autowired
    private TestPersonCreator testPersonCreator;

    @Autowired
    private TestTaskCommentChangeCreator testTaskCommentChangeCreator;

    @Autowired
    private TestTaskCreator testTaskCreator;

    @Autowired
    private TestUserEmploymentCreator testUserEmploymentCreator;

    @Autowired
    private TestQuestionCreator testQuestionCreator;

    @Autowired
    private TestQuestionAnswerCreator testQuestionAnswerCreator;

    @Autowired
    private TestAnswerCreator testAnswerCreator;

    @Autowired
    private TestImportQuestionaries testImportQuestionaries;

    @Autowired
    private TestTermDefinitionCreator testTermDefinitionCreator;

    @Autowired
    private TestImportCmmQuestionaries testImportCmmQuestionaries;

    @Autowired
    private TestUsersImporter testUsersImporter;

    public TestDataInitializer() {
        System.out.println();
    }

    @PostConstruct
    public void init() {
        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionAttribute());
        executeSQLScript("sql/languages.sql");
        executeSQLScript("sql/employments.sql");
        executeSQLScript("sql/functionality.sql");
//        executeSQLScript("static_translations.sql");

        List<Organization> organizations = new ArrayList<>();
        organizations.add(testFestoCreator.doCreateEntity());
//        organizations.add(testBoshCreator.doCreateEntity());
//
//
//        List<BusinessUnit> businessUnits = testUnitsImporter.importBusinessUnits();
//        businessUnits.addAll(testUnitsImporter.importBusinessUnits());
//
//        List<MaturityLevel> maturityLevels = testMaturityLevelCreator.createCollection(2);
//        List<TermDefinition> termDefinitions = testTermDefinitionCreator.createCollection();
//
//        assignOrganizationsToUnits(organizations, businessUnits);
//        organizations.forEach(organization -> testUsersImporter.importCompanyUsers(organization));
//
////        assignOrganizationEmployees(organizations);
////        assignOrganizationAdmins(organizations);
//        assignOrganizationWhiteLabelParam(organizations);
//        assignOrganizationMaturityLevel(organizations, maturityLevels);
//
////        generateEmploymentsInUnits(businessUnits);
////        generateMachineGroupsInUnits(businessUnits);
////        generateMaturityEvaluationInUnits(businessUnits);
//        importQuestionairesInUnits(businessUnits);
////        assignTasksForQuestions(businessUnits);
//
//        List<Comment> comments = testCommentCreator.createCollection(2);
//
//        List<MaturityLevelTransition> maturityLevelTransitions = testMaturityLevelTransitionCreator.createCollection(2);
//        List<TaskCommentChange> taskCommentChanges = testTaskCommentChangeCreator.createCollection(2);

//        copyTranslations();

        try {
            transactionManager.commit(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void assignTasksForQuestions(List<BusinessUnit> businessUnits) {
        businessUnits.forEach(businessUnit -> {
            businessUnit.getQuestionaries().forEach(questionary -> {
                questionary.getQuestions().forEach(question -> {
                    List<Task> tasks = generateTasksForQuestions(question, 3);
                    question.setTasks(tasks);
                    assignTaskForComments(tasks);
                });
            });
        });
    }

    private void assignTaskForComments(List<Task> tasks) {
        tasks.forEach(
                task -> {
                    Comment comment = testCommentCreator.createEntity();
                    comment.setTask(task);
                }
        );
    }


    private List<Task> generateTasksForQuestions(Question question, int numberOfTasks) {
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < numberOfTasks; i++) {
            Task task = testTaskCreator.doCreateEntity();

            task.setDescription(i + 1 + " Task for " + question.getContentOfTheQuestion());
            task.setExplanation(i + 1 + " Explanation for " + question.getContentOfTheQuestion());
            task.setTaskName(i + 1 + " Task name for " + question.getContentOfTheQuestion());
            task.setTaskCost(10);
            task.setQuestion(question);
            task.setTaskExecutor(testUserEmploymentCreator.createCollection(2));
            taskList.add(task);
        }
        return taskList;
    }

    private void assignOrganizationWhiteLabelParam(List<Organization> organizations) {
        organizations.forEach(organization -> {
            organization.getWhiteLabelParam().setPrimaryColor("#43454a");
            organization.getWhiteLabelParam().setSecondaryColor("#06b9c7");
        });
    }

    private void assignOrganizationMaturityLevel(List<Organization> organizations, List<MaturityLevel> maturityLevels) {
        organizations.forEach(organization -> {
            organization.setCurrentMaturityLevel(maturityLevels.get(new Random().nextInt(maturityLevels.size())));
        });
    }

    private void assignOrganizationsToUnits(List<Organization> organizations, List<BusinessUnit> businessUnits) {
//        businessUnits.forEach(businessUnit -> businessUnit.setOrganization(organizations.get(new Random().nextInt(organizations.size()))));
        int epoch = businessUnits.size()/organizations.size();
        for (int i=0; i < organizations.size(); i++){
            for (int j=0; j < epoch; j++) {
                organizations.get(i).getBusinessUnits().add(businessUnits.get((i * epoch) + j));
                businessUnits.get((i * epoch) + j).setOrganization(organizations.get(i));
            }
        }
    }

    private void assignOrganizationEmployees(List<Organization> organizations, Employment oadmin, Employment staff, Employment executor) {
        organizations.forEach(organization -> {
//            Employment oadmin = testPersonCreator.createPersonWithEmploymentId("oadmin", "18610c8a-3256-43a0-8c16-596b42a3809d");
//            Employment staff = testPersonCreator.createPersonWithEmploymentId("STAFF", "47a917f0-4784-439d-80c9-e0a6ff1ed92e");
//            Employment executor = testPersonCreator.createPersonWithEmploymentId("EXECUTOR", "3d29bffa-5c42-4e7b-aa63-9a2208011694");
            oadmin.setOrganization(organization);
            staff.setOrganization(organization);
            executor.setOrganization(organization);
            organization.getOrganizationAdmins().add(oadmin);
            organization.getOrganizationAdmins().add(staff);
            organization.getOrganizationAdmins().add(executor);
        });
    }

    private void assignOrganizationAdmins(List<Organization> organizations) {
        Person admin = testPersonCreator.createPersonWithoutEmployment();
        admin.setIsAdmin(true);
        admin.setEmail("admin");

        //Employment for organizations
        organizations.forEach(organization -> {
            Employment employment = testUserEmploymentCreator.createEntity();
            employment.setPerson(admin);
            employment.setOrganization(organization);
            organization.getOrganizationAdmins().add(employment);
        });

        //Employment for admin
        Employment adminEmployment = testUserEmploymentCreator.createEntity();
        adminEmployment.setPerson(admin);
        adminEmployment.setRole(Role.ADMINISTRATOR);
    }

    private void assignQuestionnairyToEmployment(Employment employment, int times) {
        for (int i = 0; i < times; i++) {
            Questionary questionary = testImportQuestionaries.doCreateEntity();

            UserQuestionaryAnswers userQuestionaryAnswers = new UserQuestionaryAnswers();
            userQuestionaryAnswers.setQuestionary(questionary);
            userQuestionaryAnswers.setEmployment(employment);
            employment.getUserQuestionaryAnswers().add(userQuestionaryAnswers);
            questionary.getUserQuestionaryAnswers().add(userQuestionaryAnswers);
        }

    }

    private void generateEmploymentsInUnits(List<BusinessUnit> businessUnits) {
        businessUnits.forEach(businessUnit -> {
            List<Employment> employments = testUserEmploymentCreator.createCollection(new Random().nextInt(10));
            businessUnit.setEmployments(employments);
            employments.forEach(employment -> {
                employment.getDepartments().add(businessUnit);
                employment.setOrganization(businessUnit.getOrganization());
                employment.setPerson(testPersonCreator.createPersonWithoutEmployment());
            });
        });
        assignOrganizationAdminsToBusinesUnit(businessUnits.get(0));
    }

    private void assignOrganizationAdminsToBusinesUnit(BusinessUnit businessUnit) {
        businessUnit.getEmployments().addAll(businessUnit.getOrganization().getOrganizationAdmins());
        businessUnit.getOrganization().getOrganizationAdmins().forEach(organizationAdmin -> {
            organizationAdmin.getDepartments().add(businessUnit);
        });
    }

    private void generateMachineGroupsInUnits(List<BusinessUnit> businessUnits) {
        businessUnits.forEach(businessUnit -> {
            List<MachineGroup> machineGroups = testMachineGroupCreator.createCollection(new Random().nextInt(4));
            businessUnit.setMachineGroups(machineGroups);
            machineGroups.forEach(machineGroup -> machineGroup.setBusinessUnit(businessUnit));
        });
    }

    private void generateMaturityEvaluationInUnits(List<BusinessUnit> businessUnits) {
        businessUnits.forEach(businessUnit -> {
            MaturityEvaluation maturityEvaluation = testMaturityEvaluationCreator.createEntity();
            maturityEvaluation.setBusinessUnit(businessUnit);
            businessUnit.getMaturityEvaluations().add(maturityEvaluation);
        });
    }

    private void importQuestionairesInUnits(List<BusinessUnit> businessUnits) {

        businessUnits.forEach(businessUnit -> {
            Questionary questionary = testImportCmmQuestionaries.getquestionaryByName(businessUnit.getName());
            businessUnit.getQuestionaries().add(questionary);

        });
    }

    private Answer getRandomAnswerForQuestion(Question question) {
        return question.getAnswers().get(new Random().nextInt(question.getAnswers().size()));
    }

    private void executeSQLScript(String scriptName) {
        InputStream fis = Answer.class.getClassLoader().getResourceAsStream(scriptName);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String line;
        try (Connection cmmConnection = cmmDataSource.getConnection()) {
            while ((line = br.readLine()) != null) {
                try (Statement statement = cmmConnection.createStatement()) {
                    line = line.trim();
                    if (!"".equals(line) && !line.startsWith("--")) {
                        try {
                            statement.execute(line);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


//    private void copyTranslations() {
//
//        try (Connection cmmConnection = cmmDataSource.getConnection()) {
//
//            Statement statement = cmmConnection.createStatement();
//            statement.execute("DELETE  FROM static_translations");
//
//            ResultSet result = getDataFromTranslationsDataSource();
//
//            if (result != null) {
//                String sql = "INSERT INTO static_translations (" + "id," + "version," + "name," + "status," + "uikey," + "functionality_name," + "language_name)" + "VALUES(?,?,?,?,?,?,?)";
//                PreparedStatement preparedStatement = cmmConnection.prepareStatement(sql);
//                while (result.next()) {
//                    UUID id = (UUID) result.getObject("id");
//                    int version = result.getInt("version");
//                    String name = result.getString("name");
//                    String status = result.getString("status");
//                    String uiKey = result.getString("uikey");
//                    String functionalityName = result.getString("functionality_name");
//                    String languageName = result.getString("language_name");
//
//                    preparedStatement.setObject(1, id);
//                    preparedStatement.setInt(2, version);
//                    preparedStatement.setString(3, name);
//                    preparedStatement.setString(4, status);
//                    preparedStatement.setString(5, uiKey);
//                    preparedStatement.setString(6, functionalityName);
//                    preparedStatement.setString(7, languageName);
//                    preparedStatement.execute();
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


//    private ResultSet getDataFromTranslationsDataSource() {
//        ResultSet result = null;
//        try {
//            Connection translationConnection = translationsDataSource.getConnection();
//            Statement statement = translationConnection.createStatement();
//            result = statement.executeQuery("SELECT * FROM static_translations");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
}
