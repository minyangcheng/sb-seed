package com.min.seed;

import com.min.seed.core.dto.AbstractConverter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class JavaTest {

    @Test
    public void test() {
        Object b = 1;
        log.info(b.getClass().getName() + "");
    }

    @Test
    public void testDto() {
        PersonDto personDto = new PersonDto();
        personDto.name = "aaa";
        log.info("do:{}", personDto.convertToDO());

//        Person person = new Person();
//        person.name = "bbb";
//        PersonDto personDto = new PersonDto().convertFor(person);
//        log.info("dto:{}", personDto.convertToDO());
    }

    public static class PersonDto extends AbstractConverter<PersonDto, Person> {
        public String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        protected PersonDto setDTO() {
            return this;
        }

        @Override
        public String toString() {
            return "PersonDto{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static class Person {
        public String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
