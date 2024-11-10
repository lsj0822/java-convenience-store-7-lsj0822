package model;

import java.time.LocalDate;
import java.util.List;

public class Promotion {
    String name;
    int buy;
    int get;
    LocalDate startDate;
    LocalDate endDate;

    public Promotion(List<String> information) {
        this.name = information.get(0);
        this.buy = Integer.parseInt(information.get(1));
        this.get = Integer.parseInt(information.get(2));
        this.startDate = LocalDate.parse(information.get(3));
        this.endDate = LocalDate.parse(information.get(4));
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getBuy() {
        return buy;
    }

    public int getGet() {
        return get;
    }
}
