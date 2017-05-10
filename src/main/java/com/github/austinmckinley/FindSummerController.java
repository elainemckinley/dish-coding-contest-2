package com.github.austinmckinley;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@RestController
public class FindSummerController {
    @RequestMapping(value = "/findSummer", method = RequestMethod.PUT)
    public ResponseEntity<SummerResponse> findSummer(@RequestBody SummerRequest summerRequest) {
        List<String> strings = doLogic(summerRequest);
        if (strings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new SummerResponse(strings), HttpStatus.OK);
        }
    }

    public List<String> doLogic(SummerRequest summerRequest) {
        List<Integer> legalValues = getLegalValues(summerRequest);

        List<List<Integer>> allPermutations = getAllPermutations(legalValues);
        return allPermutations.stream()
                .filter(x -> addsUp(x, summerRequest.getTotal()))
                .sorted((y, z) -> Integer.compare(z.size(), y.size()))
                .map(w -> stringify(w))
                .distinct()
                .collect(toList());
    }

    public List<List<Integer>> getAllPermutations(List<Integer> values) {
        if (values.isEmpty()) return emptyList();

        List<List<Integer>> accumulator = new ArrayList<>();
        accumulator.add(values);
        for (int i = 0; i < values.size(); i++) {
            List<Integer> deep = deepCopy(values);
            deep.remove(i);
            List<List<Integer>> foo = getAllPermutations(deep);
            accumulator.addAll(foo);
        }

        return accumulator;
    }

    public List<Integer> deepCopy(List<Integer> input) {
        List<Integer> output = new ArrayList<>();
        output.addAll(input);
        return output;
    }

    public List<Integer> getLegalValues(SummerRequest summerRequest) {
        List<Integer> finalList = new ArrayList<>();
        for (int i = summerRequest.getMin(); i <= summerRequest.getMax(); i++) {
            finalList.add(i);
        }
        return finalList;
    }

    public boolean addsUp(List<Integer> input, Integer expectedSum) {
        if (input.stream().reduce(0, (a, b) -> a + b) == expectedSum) {
            return true;
        }
        return false;
    }

    public String stringify(List<Integer> input) {
        return input.stream().map(x -> Integer.toString(x)).collect(joining("+"));
    }
}
