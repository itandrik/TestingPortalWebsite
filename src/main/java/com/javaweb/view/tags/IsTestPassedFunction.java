package com.javaweb.view.tags;

import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.services.PersonTestHistoryService;
import com.javaweb.model.services.impl.PersonTestHistoryServiceImpl;

/**
 * @author Andrii Chernysh on 03-Feb-17. E-Mail : itcherry97@gmail.com
 */
public class IsTestPassedFunction {
    public static boolean isTestPassed(Test test, Person person){
        PersonTestHistoryService personTestHistoryService =
                PersonTestHistoryServiceImpl.getInstance();

        return personTestHistoryService.isTestPassedByPerson(test,person);
    }
}
