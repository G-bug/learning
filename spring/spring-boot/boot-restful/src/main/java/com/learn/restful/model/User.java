/**
 * @author G-bug 2019/9/29
 */
package com.learn.restful.model;

/**
 * @auth Administrator
 */
public class User {

    private Long id;
    private String name;
    private Integer age;

    public User() {

    }

    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}
