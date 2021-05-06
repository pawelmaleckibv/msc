package master.msc.services.api;

import master.msc.model.Questionary;
import master.msc.model.QuestionaryDto;

import java.util.List;

public interface QuestionaryService extends BaseObjectService<Questionary> {

    List<Questionary> findAllLimited(int count);
    List<QuestionaryDto> findAllLimitedDTO(int count);
}
