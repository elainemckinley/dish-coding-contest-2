package com.github.austinmckinley;

import java.util.List;

public class SummerResponse {
    private List<String> solutions;

    public SummerResponse(List<String> solutions) {
        this.solutions = solutions;
    }

    public List<String> getSolutions() {
        return solutions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SummerResponse that = (SummerResponse) o;

        return !(solutions != null ? !solutions.equals(that.solutions) : that.solutions != null);

    }

    @Override
    public int hashCode() {
        return solutions != null ? solutions.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SummerResponse{" +
                "solutions=" + solutions +
                '}';
    }
}
