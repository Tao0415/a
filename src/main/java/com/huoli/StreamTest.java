package com.huoli;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Classname StreamTest
 * @Description TODO
 * @Date 2018/11/20 11:21
 * @Created by taojiangbing
 */

public class StreamTest {

    public static void main(String[] args) {
        //数据准备
        List<Student> studentList = new ArrayList();
        Student student1 = Student.builder().id(1).name("李四").grade(1).classes(1).score(60).build();
        Student student2 = Student.builder().id(2).name("张三").grade(1).classes(1).score(80).build();
        Student student3 = Student.builder().id(3).name("李二").grade(1).classes(2).score(80).build();
        Student student4 = Student.builder().id(4).name("王五").grade(1).classes(2).score(59.5).build();
        Student student5 = Student.builder().id(5).name("小红").grade(2).classes(2).score(99).build();
        Student student6 = Student.builder().id(6).name("小白").grade(2).classes(1).score(88.8).build();
        Student student7 = Student.builder().id(7).name("小黑").grade(2).classes(2).score(45).build();
        Student student8 = Student.builder().id(8).name("小明").grade(2).classes(1).score(79.5).build();

        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student6);
        studentList.add(student7);
        studentList.add(student8);
        studentList.add(student3);
        studentList.add(student4);
        studentList.add(student5);
        //按属性排序
        //StreamTest.sortListById(studentList);
        //StreamTest.sortListById2(studentList);
        //按条件过滤
        //StreamTest.filterList1(studentList);
        //StreamTest.filterList2(studentList);
        //按属性去重
        //StreamTest.distinctListById(studentList);
        //StreamTest.distinctListById2(studentList);
        //提取对象中的某一元素
        //StreamTest.mapList1(studentList);
        //StreamTest.mapList2(studentList);
        //提取对象中某些属性
        //StreamTest.recombinList1(studentList);
        //StreamTest.recombinList2(studentList);
        //统计总和
        //StreamTest.sumList1(studentList);
        //StreamTest.sumList2(studentList);
        //按属性分组
        //StreamTest.groupList1(studentList);
        //StreamTest.groupList2(studentList);
        //查找list中存不存在某条记录
        //StreamTest.findAny1(studentList);
        //StreamTest.anyMatch1(studentList);

        //其他特性
        //1.Integer 数组转换成list
        Integer array [] = {1,2,3,4,5};
        List<Integer> list = Stream.of(array).collect(Collectors.toList());
        //2.获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((Integer x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }

    /**
     * lamba表达式实现list按属性去重
     *
     * @param list
     * @return
     */
    static List<Student> distinctListById(List<Student> list) {
        System.out.println("去重前：" + list);
        List<Integer> ids = new ArrayList();
        List<Student> distinctList = list.stream().filter(
                x -> {
                    boolean b = !ids.contains(x.getId());
                    ids.add(x.getId());
                    return b;
                }
        ).collect(Collectors.toList());
        System.out.println("去重后：" + distinctList);
        return distinctList;
    }

    /**
     * for循环实现list去重
     *
     * @param list
     * @return
     */
    static List<Student> distinctListById2(List<Student> list) {
        System.out.println("去重前：" + list);
        List<Student> distinctList = new ArrayList<>();
        boolean flag = false;
        for (Student oldStu : list) {
            for (Student newStu : distinctList) {
                if (oldStu.getId() == newStu.getId()) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
            if (!flag) {
                distinctList.add(oldStu);
            }
        }
        System.out.println("去重后：" + distinctList);
        return list;
    }

    /**
     * lamba表达式实现list按属性排序
     *
     * @param list
     * @return
     */
    static List<Student> sortListById(List<Student> list) {
        System.out.println("排序前：" + list);
        List<Student> sortList = list.stream().sorted((a, b) -> (a.getId() - b.getId())).collect(Collectors.toList());
        System.out.println("排序后：" + sortList);
        return sortList;
    }

    /**
     * for循环实现list排序
     *
     * @param list
     * @return
     */
    static List<Student> sortListById2(List<Student> list) {
        System.out.println("排序前：" + list);
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getId() - o2.getId();
            }
        });
        System.out.println("排序后：" + list);
        return list;
    }

    /**
     * lamba实现筛选出一年级低于60分的学生
     *
     * @param list
     * @return
     */
    static List<Student> filterList1(List<Student> list) {
        System.out.println("筛选前：" + list);
        List<Student> filterList = list.stream().filter(x -> x.getScore() < 60 && x.getGrade() == 1).collect(Collectors.toList());
        System.out.println("筛选后：" + filterList);
        return filterList;
    }

    /**
     * for循环实现筛选出一年级低于60分的学生
     *
     * @param list
     * @return
     */
    static List<Student> filterList2(List<Student> list) {
        System.out.println("筛选前：" + list);
        List<Student> filterList = new ArrayList<>();
        for (Student stu : list) {
            if (stu.getScore() < 60 && stu.getGrade() == 1) {
                filterList.add(stu);
            }
        }
        System.out.println("筛选后：" + filterList);
        return filterList;
    }

    /**
     * lamba实现属性提取
     *
     * @param list
     * @return
     */
    static List<String> mapList1(List<Student> list) {
        System.out.println("提取前：" + list);
        List<String> nameList = list.stream().map(x -> x.getName()).collect(Collectors.toList());
        System.out.println("提取后：" + nameList);
        return nameList;
    }

    /**
     * for循环实现属性提取
     *
     * @param list
     * @return
     */
    static List<String> mapList2(List<Student> list) {
        System.out.println("提取前：" + list);
        List<String> nameList = new ArrayList<>();
        for (Student stu : list) {
            nameList.add(stu.getName());
        }
        System.out.println("提取后：" + nameList);
        return nameList;
    }

    /**
     * lamba实现list重组，按姓名和id重组
     *
     * @param list
     * @return
     */
    static List<Student> recombinList1(List<Student> list) {
        System.out.println("重组前：" + list);
        List<Student> recombinList = list.stream().map(x -> Student.builder().name(x.getName()).id(x.getId()).build()).collect(Collectors.toList());
        System.out.println("重组后：" + recombinList);
        return recombinList;
    }

    /**
     * for循环实现list重组，按姓名和id重组
     *
     * @param list
     * @return
     */
    static List<Student> recombinList2(List<Student> list) {
        System.out.println("重组前：" + list);
        List<Student> recombinList = new ArrayList<>();
        for (Student stu : list) {
            Student temp = Student.builder().name(stu.getName()).id(stu.getId()).build();
            recombinList.add(temp);
        }

        System.out.println("重组后：" + recombinList);
        return recombinList;

    }

    /**
     * lamba实现按属性统计list总和
     *
     * @param list
     * @return
     */
    static double sumList1(List<Student> list) {
        double total = list.stream().mapToDouble(x -> x.getScore()).sum();
        System.out.println("分数总和:" + total);
        return total;
    }

    /**
     * for循环实现按属性统计list总和
     *
     * @param list
     * @return
     */
    static double sumList2(List<Student> list) {
        BigDecimal b1 = new BigDecimal(Double.toString(0.0));
        for (Student stu : list) {
            BigDecimal b2 = new BigDecimal(Double.toString(stu.getScore()));
            b1.add(b2);
        }
        return b1.doubleValue();
    }


    /**
     * lamba实现按属性对list进行分组
     *
     * @param list
     * @return
     */
    static Map<Integer, List<Student>> groupList1(List<Student> list) {
        Map<Integer, List<Student>> map = list.stream().collect(Collectors.groupingBy(t -> t.getGrade()));
        Map<Integer, Map<Integer, List<Student>>> groupMap = list.stream().collect(Collectors.groupingBy(t -> t.getGrade(), Collectors.groupingBy(t -> t.getClasses())));
        System.out.println("按年级分组" + map);
        System.out.println("按年级再按班级分组" + groupMap);
        return map;
    }

    /**
     * for循环实现按属性对list进行分组
     *
     * @param list
     * @return
     */
    static Map<Integer, List<Student>> groupList2(List<Student> list) {
        Map<Integer, List<Student>> map = new HashMap<>();
        for (Student stu : list) {
            List<Student> tempList = map.get(stu.getGrade());
            /*如果取不到数据,那么直接new一个空的ArrayList**/
            if (tempList == null) {
                tempList = new ArrayList<>();
                tempList.add(stu);
                map.put(stu.getGrade(), tempList);
            } else {
                /*某个sku之前已经存放过了,则直接追加数据到原来的List里**/
                tempList.add(stu);
            }
        }
        System.out.println("按年级分组" + map);
        return map;
    }

    /**
     * lamba实现list查找某条记录是否存在
     *
     * @param list
     * @return
     */
    static Boolean anyMatch1(List<Student> list) {
        Boolean b = list.stream().anyMatch(x -> x.getScore() == 80.0d);
        if (b) {
            System.out.println("存在");
        } else {
            System.out.println("不存在");
        }
        return b;
    }

    /**
     * for循环实现list查找某条记录是否存在
     *
     * @param list
     * @return
     */
    static Boolean anyMatch2(List<Student> list) {
        Boolean b = null;
        for (Student stu : list) {
            if (stu.getScore() == 80.0d) {
                b = true;
                break;
            }
        }
        if (b) {
            System.out.println("存在");
        } else {
            System.out.println("不存在");
        }
        return b;
    }

}
