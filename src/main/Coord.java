package main;

public class Coord {
    String name;
    double x;
    double y;
    int delayTime;
    String mouseButton;

    public Coord(){}

    public Coord(double _x, double _y){
        this.x = _x;
        this.y = _y;
    }

    public Coord(String[] ar){
        this.name = ar[0];
        this.x = Integer.parseInt(ar[1]);
        this.y = Integer.parseInt(ar[2]);;
        this.delayTime = Integer.parseInt(ar[3]);;
        this.mouseButton = ar[4];;
    }
}