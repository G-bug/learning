package com.learn.batch.processor;

import com.learn.batch.entity.Person;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author g-bug
 * @date 2020/9/29 上午9:46
 */
@Component
public class PersonProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(Person person) throws Exception {
        return new Person() {{
            setName(person.getName().toUpperCase());
            setAge(person.getAge() + 100);
        }};
    }
}