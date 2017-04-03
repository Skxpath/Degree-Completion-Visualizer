package com.eli.landa.cmpt213.Model;

/**
 * Created by Aria on 3/31/2017.
 * <p>
 * StudentFilter interface to abstract the different filters we will be using.
 */
public interface StudentFilter {
    boolean acceptStudent(Student student);
}
