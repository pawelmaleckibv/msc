package master.msc.controllers;

import com.blueveery.core.ctrls.BaseCtrl;
import com.blueveery.core.services.BaseService;
import com.blueveery.scopes.JsonScope;
import master.msc.model.*;
import master.msc.services.api.QuestionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import master.msc.utils.Logger;

import java.util.List;

@RestController
@RequestMapping("/api/")
@JsonScope(positive = true, scope = {BusinessUnit.class, Question.class, Answer.class})
public class QuestionaryCtrl implements BaseCtrl<Questionary> {

    @Autowired
    private QuestionaryService questionaryService;

    @RequestMapping(path = "papa", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Questionary> test1(@RequestParam("rowNumbers") int rowNumbers) {

        long databaseQueryStart = System.currentTimeMillis();

        List<Questionary> allLimited = questionaryService.findAllLimited(rowNumbers);
        allLimited.forEach(all -> {
            all.getBusinessUnits().forEach(bu -> {
                bu.getName();
            });
            all.getQuestions().forEach(question -> {
                question.getName();
                question.getAnswers().forEach(answer -> answer.getAnswerContent());
            });
        });
        long databaseQueryEnd = System.currentTimeMillis();
        long databaseQueryTime = databaseQueryEnd - databaseQueryStart;
        Logger.getLogger("DataBase").logTimes("dataBaseQuery", Questionary.class.toString(), databaseQueryStart, databaseQueryEnd, databaseQueryTime );

        long transferStart = System.currentTimeMillis();
        Logger.getLogger("Transfer").singleLog(Questionary.class.toString(), transferStart );

        return allLimited;
    }

    @RequestMapping(path = "papaDTO", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<QuestionaryDto> test2(@RequestParam("rowNumbers") int rowNumbers) {
        long databaseQueryStartDto = System.currentTimeMillis();

        List<QuestionaryDto> allLimited = questionaryService.findAllLimitedDTO(rowNumbers);

        long databaseQueryEndDto = System.currentTimeMillis();
        long databaseQueryTimeDto = databaseQueryEndDto - databaseQueryStartDto;
        Logger.getLogger("DataBasetDto").logTimes("dataBaseQuerytDto", QuestionaryDto.class.toString(), databaseQueryStartDto, databaseQueryEndDto, databaseQueryTimeDto);

        long transferStartDto = System.currentTimeMillis();
        Logger.getLogger("TransferDto").singleLog(QuestionaryDto.class.toString(), transferStartDto);

        return allLimited;
    }

    @Override
    public BaseService<Questionary> getService() {
        return questionaryService;
    }
}
