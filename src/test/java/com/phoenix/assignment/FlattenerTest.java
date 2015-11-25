package com.phoenix.assignment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class FlattenerTest {

    private Flattener flattener;

    @Before
    public void setup() {
        flattener = new Flattener();
    }

    @Test
    public void testConvertToFlatArray() {

        Assert.assertNull(flattener.convertToFlatArray(null));

        Assert.assertArrayEquals(
                "empty array",
                new Integer[]{},
                toArray(flattener.convertToFlatArray(new Object[]{}))
        );

        Assert.assertArrayEquals(
                "flat Array",
                new Integer[]{1, 2, 3, 4},
                toArray(flattener.convertToFlatArray(new Object[]{1, 2, 3, 4}))
        );

        Assert.assertArrayEquals(
                "flatten a two level nested array",
                new Integer[]{1, 2, 3, 4, 5},
                toArray(flattener.convertToFlatArray(new Object[]{1, new Object[]{2, 3}, 4, 5}))
        );

        Assert.assertArrayEquals(
                "flatten a three level nested array",
                new Integer[]{1, 2, 3, 4, 5, 6},
                toArray(flattener.convertToFlatArray(new Object[]{1, 2, new Object[]{3, new Object[]{4, 5}}, 6}))
        );

    }

    private Integer[] toArray(List<Integer> list) {
        return list.toArray(new Integer[list.size()]);
    }

}
