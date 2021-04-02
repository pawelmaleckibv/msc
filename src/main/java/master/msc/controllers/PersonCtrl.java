package master.msc.controllers;

import com.blueveery.core.ctrls.BaseCtrl;
import com.blueveery.core.services.BaseService;
import master.msc.model.Person;
import master.msc.services.api.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class PersonCtrl implements BaseCtrl<Person> {


    @Autowired
    private PersonService personService;


    @RequestMapping(path = "/test", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String test(){
        Person person = new Person();
        person.setFirstName("aaaa");
        person.setLastName("bbbb");
        person.setSalutation("bbbb");
        getService().merge(person);
        return "sasasasasas";
    }

    @Override
    public BaseService<Person> getService() {
        return personService;
    }
}
