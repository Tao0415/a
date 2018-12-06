package com.huoli;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Classname EffectTest
 * @Description TODO
 * @Date 2018/11/20 17:27
 * @Created by taojiangbing
 */
public class EffectTest {
    public static void main(String[] args) {
        Random random = new Random();
        random.nextInt(3);
        ArrayList<Student> studentList = new ArrayList();
        for (int i = 0; i < 1000000; i++) {
            Student stu = Student.builder().id(random.nextInt(3)).name(i + " x").grade(random.nextInt(6)).score(random.nextInt(100)).classes(random.nextInt(10)).build();
            studentList.add(stu);
        }
        ArrayList<Student> studentList2 = SerializationUtils.clone(studentList);

        long distinctListByIdstart = System.currentTimeMillis();
        StreamTest.sortListById(studentList);
        System.out.println("lamba花费" + (System.currentTimeMillis() - distinctListByIdstart));


        long distinctListById2start = System.currentTimeMillis();
        StreamTest.sortListById2(studentList2);
        System.out.println("for花费" + (System.currentTimeMillis() - distinctListById2start));
    }
}
