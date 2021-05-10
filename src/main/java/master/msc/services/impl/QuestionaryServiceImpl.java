package master.msc.services.impl;

import master.msc.model.QuestionDto;
import master.msc.model.Questionary;
import master.msc.model.QuestionaryDto;
import master.msc.services.api.QuestionaryService;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.*;

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
        String query = "SELECT q.id as q_id, q.name as q_name , bu.id as bu_id, bu.name as bu_name, q2.id as q2_id, q2.name as q2_name, a.answerContent\n" +
                "FROM questionaries q\n" +
                "         JOIN questionary_x_business_unit qxbu on q.id = qxbu.questionary_id\n" +
                "         JOIN business_units bu on bu.id = qxbu.business_unit_id\n" +
                "         JOIN questionary_x_question qxq on q.id = qxq.questionary_id\n" +
                "         JOIN questions q2 on qxq.question_id = q2.id\n" +
                "         JOIN answers a on qxq.question_id = a.question_id";

        List<Tuple> resultList = entityManager.createNativeQuery(query, Tuple.class).getResultList();


        final ArrayList<QuestionaryDto> result = new ArrayList<>();
        final Iterator<Tuple> it = resultList.iterator();
        if (it.hasNext()) {
            Tuple next = it.next();
            String qId = ((String) next.get(0));
            while (next != null) {
                QuestionaryDto questionaryDto = new QuestionaryDto();
                questionaryDto.questionaryName = ((String) next.get(1));
                Tuple tuple = mapBusinessUnits(it, next, 2, questionaryDto);
                while (it.hasNext() && qId.equals(((String) tuple.get(0)))) {
                    tuple = mapBusinessUnits(it, next, 2, questionaryDto);
                }

                result.add(questionaryDto);
                next = (!it.hasNext()) ? null : tuple;
            }
        }
        return result;
    }

    private Tuple mapBusinessUnits(Iterator<Tuple> it, Tuple row, int buIdx, QuestionaryDto questionaryDto) {
        String buId = (String) row.get(buIdx);
        questionaryDto.businessUnitName.add((String) row.get(buIdx + 1));

        Tuple next = row;
        do {
            final Map<Tuple, QuestionDto> stringQuestionDtoMap = mapQuestions(it, next, 4);
            questionaryDto.questionDto.add(stringQuestionDtoMap.values().iterator().next());
            next = stringQuestionDtoMap.keySet().iterator().next();
        } while (it.hasNext() && buId.equals((String) next.get(buIdx)));
        return next;
    }

    private Map<Tuple, QuestionDto> mapQuestions(Iterator<Tuple> it, Tuple row, int qIdx) {
        String questionId = (String) row.get(qIdx);
        final ArrayList<String> answers = new ArrayList<>();
        Tuple nextRow = null;
        while (it.hasNext() && questionId.equals((String) (nextRow = it.next()).get(qIdx))) {
            answers.add((String) nextRow.get(qIdx));
        }
        final QuestionDto questionDto = new QuestionDto();
        questionDto.questionName = ((String) row.get(qIdx + 1));
        questionDto.answers = answers;
        final HashMap<Tuple, QuestionDto> lastRowOnQuestions = new HashMap<>();
        lastRowOnQuestions.put(nextRow, questionDto);
        return lastRowOnQuestions;
    }
}
