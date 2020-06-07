package com.pukhalskyi.entity;

import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class UserTest {
    @Test
    public void Should_Pass_All_Pojo_Tests() {
        // given
        final Class<?> classUnderTest = User.class;

        // when

        // then
        assertPojoMethodsFor(classUnderTest).testing(Method.CONSTRUCTOR, Method.GETTER, Method.SETTER)
                .areWellImplemented();
    }
}