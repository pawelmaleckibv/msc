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
import java.util.stream.Stream;

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
        String query =
                "SELECT quest.id as auestioanryId,quest.name as questionaryName,\n" +
                        "       bu.id as bUnitId,bu.name as buName,\n" +
                        "       q.id as questionid,q.name as questionName, a.answerContent\n" +
                        "FROM questionaries quest\n" +
                        "         LEFT JOIN questionary_x_business_unit qxbu on quest.id = qxbu.questionary_id\n" +
                        "         JOIN business_units bu on bu.id = qxbu.business_unit_id\n" +
                        "         JOIN questionary_x_question qxq on quest.id = qxq.questionary_id\n" +
                        "         JOIN questions q on qxq.question_id = q.id\n" +
                        "         LEFT JOIN answers a on q.id = a.question_id";

        List<Tuple> resultList = entityManager.createNativeQuery(query, Tuple.class).getResultList();


        final ArrayList<QuestionaryDto> result = new ArrayList<>();
        final Iterator<Tuple> it = resultList.iterator();
        Set<String> mappedQuestionId = new HashSet<>();
        if (it.hasNext()) {
            Tuple next = it.next();
            String qId = ((String) next.get(0));
            System.out.println("qId : " + qId);
            while (next != null) {
                QuestionaryDto questionaryDto = new QuestionaryDto();
                questionaryDto.questionaryName = ((String) next.get(1));
                System.out.println("questionaryName : " + questionaryDto.questionaryName);

                next = mapBusinessUnits(it, next, 2, questionaryDto, mappedQuestionId);
                while (it.hasNext() && qId.equals(((String) next.get(0)))) {
                    next = mapBusinessUnits(it, next, 2, questionaryDto, mappedQuestionId);
                }

                result.add(questionaryDto);
                next = (!it.hasNext()) ? null : next;
                qId = (!it.hasNext()) ? null : ((String) next.get(0));
                if (it.hasNext()) {
                    mappedQuestionId = new HashSet<>();
                }
            }
        }
        return result;
    }

    private Tuple mapBusinessUnits(Iterator<Tuple> it, Tuple row, int buIdx, QuestionaryDto questionaryDto, Set<String> mappedQuestionId) {
        String buId = (String) row.get(buIdx);
        questionaryDto.businessUnitName.add((String) row.get(buIdx + 1));

        Tuple next = row;
        System.out.println("mapBusinessUnits: " + next);
//        Stream.of(next.toArray()).forEach(x -> System.out.print(" "+x.toString()));
        System.out.println();


        do {
            final Map<Tuple, QuestionDto> stringQuestionDtoMap = mapQuestions(it, next, 4, mappedQuestionId);
            QuestionDto next1 = stringQuestionDtoMap.values().iterator().next();
            if (next1 != null) questionaryDto.questionDto.add(next1);
            next = stringQuestionDtoMap.keySet().iterator().next();
            System.out.println();
        } while (it.hasNext() && buId.equals((String) next.get(buIdx)));
        System.out.println("END mapBusinessUnits: " + next);

        return next;
    }

    private Map<Tuple, QuestionDto> mapQuestions(Iterator<Tuple> it, Tuple row, int qIdx, Set<String> mappedQuestionId) {
        String questionId = (String) row.get(qIdx);
        if (mappedQuestionId.contains(questionId)) {

            Tuple nextRow = null;
            while (it.hasNext() && questionId.equals((String) (nextRow = it.next()).get(qIdx))) {
            }
            final HashMap<Tuple, QuestionDto> lastRowOnQuestions = new HashMap<>();
            lastRowOnQuestions.put(nextRow, null);

            return lastRowOnQuestions;
        }
        mappedQuestionId.add(questionId);
        System.out.println(String.format("questionId=%s", questionId));
        final ArrayList<String> answers = new ArrayList<>();
        if (row != null) {
            answers.add((String) row.get(qIdx + 2));
        }
        Tuple nextRow = null;
        while (it.hasNext() && questionId.equals((String) (nextRow = it.next()).get(qIdx))) {
            answers.add((String) nextRow.get(qIdx+2));
        }
        final QuestionDto questionDto = new QuestionDto();
        questionDto.questionName = ((String) row.get(qIdx + 1));
        questionDto.answers = answers;
        final HashMap<Tuple, QuestionDto> lastRowOnQuestions = new HashMap<>();
        lastRowOnQuestions.put(nextRow, questionDto);
//        Stream.of(nextRow.toArray()).forEach(x -> System.out.print(" "+x.toString()));
//        System.out.println("Question Answers: "+lastRowOnQuestions);

        return lastRowOnQuestions;
    }
}
