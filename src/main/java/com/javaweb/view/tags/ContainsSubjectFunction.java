package com.javaweb.view.tags;

import com.javaweb.model.entity.Subject;

import java.util.List;

/**
 * @author Andrii Chernysh on 11-Feb-17. E-Mail : itcherry97@gmail.com
 */
public class ContainsSubjectFunction {
    public static boolean contains(List<Subject> list, Subject o) {
        return list.contains(o);
    }
}
