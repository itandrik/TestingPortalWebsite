package com.javaweb.view.tags;

import com.javaweb.model.entity.Answer;

import java.util.List;

/**
 * @author Andrii Chernysh on 02-Feb-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class ContainsAnswerFunction {
    public static boolean contains(List<Answer> list, Answer o) {
        return list.contains(o);
    }
}
