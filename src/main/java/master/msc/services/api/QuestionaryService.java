package master.msc.services.api;

import master.msc.model.Questionary;

import java.util.List;

public interface QuestionaryService extends BaseObjectService<Questionary> {

    List<Questionary> findAllLimited(int count);
}
