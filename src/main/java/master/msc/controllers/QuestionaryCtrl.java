package master.msc.controllers;

import com.blueveery.core.ctrls.BaseCtrl;
import com.blueveery.core.services.BaseService;
import master.msc.model.Questionary;
import master.msc.services.api.QuestionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class QuestionaryCtrl implements BaseCtrl<Questionary> {

    @Autowired
    private QuestionaryService questionaryService;

    @RequestMapping(path = "papa", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Questionary> test1(@RequestParam("rowNumbers") int rowNumbers){
        return questionaryService.findAllLimited(rowNumbers);
    }

    @Override
    public BaseService<Questionary> getService() {
        return questionaryService;
    }
}
