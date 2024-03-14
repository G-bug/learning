package com.learn.batch.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 领域对象
 * 可结合 spring-boot-starter-validation
 *
 *
 * @author g-bug
 * @date 2020/9/29 上午9:40
 */
@Data
@EqualsAndHashCode
public class Person {

    private String name;

    @NotNull
    private Integer age;

}
