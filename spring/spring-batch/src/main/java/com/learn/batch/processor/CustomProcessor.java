package com.learn.batch.processor;

import com.learn.batch.entity.Person;
import com.learn.batch.exception.MyJobException;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * 数据处理实现
 * BeanValidatingItemProcessor bean 核验处理
 *
 * @author g-bug
 * @date 2020/9/29 上午9:46
 */
@Component
public class CustomProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(Person person) throws MyJobException {
        System.out.println("processor ......." + person.getName());

        if (person.getAge() == null) {
            throw new MyJobException("age exception");
        }

        // 返回null 则表示忽略
        return new Person() {{
            setName(person.getName().toUpperCase());
            setAge((person.getAge() == null ? 0 : person.getAge()) + 100);
        }};
    }
}