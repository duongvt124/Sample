package lgv.automation.model;

import java.util.List;

public class Driver {
    private String name;
    private String homeBase;
    private String destination_1;
    private String destination_2;
    private int prioritize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomeBase() {
        return homeBase;
    }

    public void setHomeBase(String homeBase) {
        this.homeBase = homeBase;
    }

    public String getDestination_1() {
        return destination_1;
    }

    public void setDestination_1(String destination_1) {
        this.destination_1 = destination_1;
    }

    public String getDestination_2() {
        return destination_2;
    }

    public void setDestination_2(String destination_2) {
        this.destination_2 = destination_2;
    }

    public int getPrioritize() {
        return prioritize;
    }

    public void setPrioritize(int prioritize) {
        this.prioritize = prioritize;
    }
}
