package model;

import java.time.LocalDate;

public class Promotion {
    String name;
    int buy;
    int get;
    LocalDate startDate;
    LocalDate endDate;

    public Promotion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
