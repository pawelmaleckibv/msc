package master.msc.testData;

import master.msc.model.TermDefinition;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TestTermDefinitionCreator extends TestDataCreator<TermDefinition> {
    private UUID id = UUID.fromString("90854a61-67f3-4374-b6ff-781842ce2f5e");

    @Override
    protected TermDefinition doCreateEntity() {
        return null;
    }

    public List<TermDefinition> createCollection() {
        String s = System.getProperty("user.dir") + String.join(File.separator, "", "src", "main", "resources");
        String file = s+"\\term_definitions\\term_definitions.txt";
        List<String> termDefinitions = parseLinesToList(file);
        List<TermDefinition> entities = new ArrayList<>();

        for (int i = 0; i < termDefinitions.size(); i++) {
            TermDefinition termDefinition = new TermDefinition();
            termDefinition.setTerm(termDefinitions.get(i));
            i++;
            termDefinition.setDefinition(termDefinitions.get(i));
            termDefinition.setLanguageID(id);
            entities.add(termDefinition);
        }
        return persistCollection(entities);
    }
}
