package com.others;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: someone
 * Date:2017/12/11
 * Time: 8:51
 * To change this template use File | Settings | File Templates.
 */
public class Test
{
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(2);
        list.add("1");
        list.add("2");
        String[] array = new String[list.size()];
        array = list.toArray(array);

        for (Object o : array){
            System.out.println(o);
        }
    }
}
