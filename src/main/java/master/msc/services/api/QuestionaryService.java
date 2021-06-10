package master.msc.services.api;

import master.msc.model.Questionary;
import master.msc.model.QuestionaryDto;

import java.util.List;
import java.util.UUID;

public interface QuestionaryService extends BaseObjectService<Questionary> {

    List<Questionary> findById(UUID id);
    List<QuestionaryDto> findByIdDTO(UUID id);
}
