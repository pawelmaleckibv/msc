package master.msc.services.impl;

import master.msc.model.Questionary;
import master.msc.services.api.QuestionaryService;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class QuestionaryServiceImpl extends BaseObjectServiceImpl<Questionary> implements QuestionaryService {

    @Transactional
    @Override
    public List<Questionary> findAllLimited(int count) {
//        String s = "SELECT * FROM questionaries INNER JOIN questionary_x_question as qxq on questionaries.id = qxq.questionary_id" +
//                "        INNER JOIN questions as qs ON qxq.question_id = qs.id";
//        List<Questionary> resultList = (List<Questionary>) this.getEntityManager().createNativeQuery(s, Questionary.class).getResultList();
//        resultList.stream().forEach(r -> r.getBusinessUnits().stream().forEach(x -> {
//            x.toString();
//            System.err.println(x.toString());
//        }));
//        return resultList;

        CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Questionary> criteriaQuery = criteriaBuilder.createQuery(Questionary.class);
        Root<Questionary> rootEntity = criteriaQuery.from(Questionary.class);

        criteriaQuery.select(rootEntity);
        TypedQuery<Questionary> questionaryTypedQuery = entityManager.createQuery(criteriaQuery);
        List<Questionary> resultList = questionaryTypedQuery.getResultList();
        return resultList;
    }
}
