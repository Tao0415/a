package com.huoli;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Classname com.huoli.Student
 * @Description TODO
 * @Date 2018/11/20 11:21
 * @Created by taojiangbing
 */
@Data
@Builder(toBuilder = true)
public class Student implements Serializable {

	private int id;

	private String name;

	private int grade;

	private int classes;

	private double score;

}
