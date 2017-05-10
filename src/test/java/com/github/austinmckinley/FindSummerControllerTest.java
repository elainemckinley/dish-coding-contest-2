package com.github.austinmckinley;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FindSummerControllerTest {

    @Test
    public void bar() {
        FindSummerController controller = new FindSummerController();
        System.out.println(controller.doLogic(new SummerRequest(10, 1, 5)));
    }

    @Test
    public void baz() {
        FindSummerController controller = new FindSummerController();
        List<List<Integer>> allPermutations = controller.getAllPermutations(asList(2, 3, 4, 5));
        System.out.println(allPermutations);
    }

    @Test
    public void foo() {
        FindSummerController controller = new FindSummerController();
        ResponseEntity<List<String>> summer = controller.findSummer(new SummerRequest(56, 1, 10));
        System.out.println(summer.getStatusCode());
        System.out.println(summer.getBody());
    }
}