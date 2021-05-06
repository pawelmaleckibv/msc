package master.msc.services.impl;

import master.msc.model.Questionary;
import master.msc.model.QuestionaryDto;
import master.msc.services.api.QuestionaryService;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionaryServiceImpl extends BaseObjectServiceImpl<Questionary> implements QuestionaryService {

    @Transactional
    @Override
    public List<Questionary> findAllLimited(int count) {
        CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Questionary> criteriaQuery = criteriaBuilder.createQuery(Questionary.class);
        Root<Questionary> rootEntity = criteriaQuery.from(Questionary.class);

        criteriaQuery.select(rootEntity);
        TypedQuery<Questionary> questionaryTypedQuery = entityManager.createQuery(criteriaQuery);
        List<Questionary> resultList = questionaryTypedQuery.getResultList();
        return resultList;
    }

    @Override
    public List<QuestionaryDto> findAllLimitedDTO(int count) {

        List resultList = entityManager.createNamedQuery("QuestionaryDto").getResultList();
        return resultList;
    }
}
