package testChinaRailway;

public class LinesStation {
    private final String station;
    private final int distance;

    public LinesStation(String station, int distance){
        this.station = station;
        this.distance = distance;
    }
    public String getStation() {
        return station;
    }

    public int getDistance() {
        return distance;
    }

}
