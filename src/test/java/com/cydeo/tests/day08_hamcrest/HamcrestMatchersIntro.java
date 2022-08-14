package com.cydeo.tests.day08_hamcrest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

import java.util.*;


public class HamcrestMatchersIntro {
    @Test
    public void numbersTest() {
        //MatcherAssert.assertThat( 1 + 3, Matchers.is(4));
        assertThat(1 + 3, is(4));
        assertThat(5 + 5, equalTo(10));
        assertThat(10 + 5, is(equalTo(15)));

        assertThat( 100 + 4, is(greaterThan(99)));
        //assertTrue(100 + 4 > 99); JUnit
        int num = 10 + 2;
        assertThat(num , is(not(10)));
        assertThat(num, is(not(equalTo(9))));
    }

    @Test
    public void stringsTest() {
        String word = "wooden spoon";
        assertThat(word, is("wooden spoon"));
        assertThat(word, equalTo("wooden spoon"));

        //startsWith/endsWith
        assertThat(word, startsWith("wood"));
        assertThat(word, startsWithIgnoringCase("WOOD"));
        assertThat("endsWith 'n'", word, endsWith("n"));

        //contains
        assertThat(word, containsString("den"));
        assertThat(word, containsStringIgnoringCase("SPOON"));

        //empty string
        String str = " ";
        assertThat(str, is(blankString()));
        assertThat(str.replace(" ",""), is(emptyOrNullString()));
        assertThat(str.trim(), is(emptyOrNullString()));
    }

    @Test
    public void listsTest() {
        //List<Integer> nums = List.of(5, 20, 1, 54); //from java 9
        List<Integer> nums = Arrays.asList(5, 20, 1, 54, 0);
        List<String> words = Arrays.asList("java", "selenium", "git", "maven", "api");

        //size
        assertThat(nums, hasSize(5));
        assertThat(words, hasSize(5));

        //contains
        assertThat(nums, hasItem(5));
        assertThat(words, hasItems("git", "java"));
        assertThat(nums, containsInAnyOrder(54, 1, 5, 20, 0));

        //every item
        assertThat(nums, everyItem(greaterThanOrEqualTo(0)));
        assertThat(words, everyItem(not(blankString())));


    }
}