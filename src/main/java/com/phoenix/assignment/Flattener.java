package com.phoenix.assignment;

import java.util.ArrayList;
import java.util.List;

public class Flattener {


    public List<Integer> convertToFlatArray(Object[] nestedElement) {

        if (nestedElement == null)
            return null;

        final List<Integer> flattenedElements = new ArrayList<>();

        for (final Object element : nestedElement) {
            if (element instanceof Integer) {
                flattenedElements.add((Integer) element);
            } else {
                flattenedElements.addAll(convertToFlatArray((Object[]) element));
            }
        }
        return flattenedElements;
    }

}
