package master.msc.services.impl;

import master.msc.model.Person;
import master.msc.services.api.PersonService;
import org.springframework.stereotype.Service;


@Service
public class PersonServiceImpl extends BaseObjectServiceImpl<Person>  implements PersonService {
}
