package master.msc.testData;

import master.msc.model.Questionary;
import org.springframework.stereotype.Component;


@Component
public class TestQuestionaryCreator extends TestDataCreator<Questionary> {

    @Override
    protected Questionary doCreateEntity() {

        Questionary questionary = new Questionary();
        questionary.setName("Questionary_" + getRandom().nextInt(100));

        return questionary;
    }

}

