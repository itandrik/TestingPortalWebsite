package com.javaweb.view.tags;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrii Chernysh on 15-Feb-17. E-Mail : itcherry97@gmail.com
 */
public class ContainsIntegerFunction {
    public static boolean contains(List<Integer> list, int id){
        if(list == null){
            list = new ArrayList<>();
        }
        return list.contains(id);
    }
}
