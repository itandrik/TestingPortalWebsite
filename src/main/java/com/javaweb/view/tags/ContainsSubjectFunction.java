package com.javaweb.view.tags;

import com.javaweb.model.entity.Subject;
import com.javaweb.model.entity.Test;

import java.util.List;

/**
 * @author Andrii Chernysh on 11-Feb-17. E-Mail : itcherry97@gmail.com
 */
public class ContainsSubjectFunction {
    public static boolean contains(List<Test> list, Subject o) {
        for (Test test: list) {
            if(test.getSubjectId() == o.getId()){
                return true;
            }
        }
        return false;
    }
}
