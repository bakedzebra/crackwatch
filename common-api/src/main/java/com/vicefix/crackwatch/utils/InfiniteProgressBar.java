package com.vicefix.crackwatch.utils;

public class InfiniteProgressBar {
    private final String title;
    private final String measure;

    private int steps;

    public InfiniteProgressBar(String title, String measure) {
        this.title = title;
        this.measure = measure;
        this.steps = 0;
        System.out.print(String.format("%s | %s | %s \r", title, "█".repeat(steps), measure));
    }

    public void update(int step) {
        if (step <= 0) {
            throw new RuntimeException();
        }

        this.steps += step;

        System.out.print(String.format("%s | %s | %s \r", title, "█".repeat(steps), measure));
    }
}