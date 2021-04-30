package master.msc.testData;

import master.msc.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class TestDataInitializer {

    @Autowired
    private DataSource cmmDataSource;

    @Autowired
    private PlatformTransactionManager transactionManager;

    public TestDataInitializer() {
        System.out.println();
    }

    @PostConstruct
    public void init() {
        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionAttribute());
        executeSQLScript("sql/languages.sql");
        executeSQLScript("sql/functionality.sql");
        executeSQLScript("sql/employments.sql");
        executeSQLScript("sql/msc_questionaries.sql");
        executeSQLScript("sql/msc_maturity_levels.sql");
        executeSQLScript("sql/msc_organizations.sql");
        executeSQLScript("sql/msc_business_units.sql");
        executeSQLScript("sql/msc_questions.sql");
        executeSQLScript("sql/msc_questionary_x_business_unit.sql");
        executeSQLScript("sql/msc_answers.sql");
        executeSQLScript("sql/msc_questionary_x_question.sql");

        try {
            transactionManager.commit(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

}
