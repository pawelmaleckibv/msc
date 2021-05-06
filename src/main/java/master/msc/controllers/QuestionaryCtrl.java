package master.msc.controllers;

import com.blueveery.core.ctrls.BaseCtrl;
import com.blueveery.core.services.BaseService;
import com.blueveery.scopes.JsonScope;
import master.msc.model.*;
import master.msc.services.api.QuestionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@JsonScope(positive = true, scope = {BusinessUnit.class, Question.class, Answer.class})
public class QuestionaryCtrl implements BaseCtrl<Questionary> {

    @Autowired
    private QuestionaryService questionaryService;

    @RequestMapping(path = "papa", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Questionary> test1(@RequestParam("rowNumbers") int rowNumbers){
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
        return allLimited;
    }

    @RequestMapping(path = "papaDTO", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<QuestionaryDto> test2(@RequestParam("rowNumbers") int rowNumbers){
        List<QuestionaryDto> allLimited = questionaryService.findAllLimitedDTO(rowNumbers);

        return allLimited;
    }

    @Override
    public BaseService<Questionary> getService() {
        return questionaryService;
    }
}
