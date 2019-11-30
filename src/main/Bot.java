package main;

import java.util.ArrayList;

public class Bot{
    public static void main(String[] args) {

        String name = "src/main/resources/coords.txt";
        ArrayList<Coord> pointList;
        ArrayList<Coord> detour = new ArrayList<Coord>();
        ArrayList<Coord> trajectory = new ArrayList<Coord>();
        RobotWrapper robot = new RobotWrapper();
        CoordsReader coordsReader = new CoordsReader();
        GeometricTools geometricTools = new GeometricTools();


        pointList = coordsReader.readCoords(name);
        while(true){
            robot.moveToStart(pointList.get(0), geometricTools);
            for (int k = 0; k<pointList.size()-1; k++){
                robot.pressTheButton(pointList.get(k).mouseButton);
                geometricTools.calculateRandomPointsBetween(pointList.get(k), pointList.get(k+1),detour);
                geometricTools.calculateSlopes(pointList.get(k), pointList.get(k+1));
                trajectory.clear();
                trajectory.add(new Coord(detour.get(0).x, detour.get(0).y));
                for (int i =1; i<detour.size()-1; i++){
                    geometricTools.getShitDone(detour.get(i), detour.get(detour.size()-1), detour.get(i+1), trajectory);
                }
                trajectory.add(new Coord(detour.get(detour.size()-1).x, detour.get(detour.size()-1).y));

                robot.delay(pointList.get(k).delayTime);

                for (int i=0; i<trajectory.size()-1 ;i++){
                    robot.smoothMove(trajectory.get(i), trajectory.get(i+1), geometricTools);
                }
                robot.move(trajectory.get(trajectory.size()-1));
            }
            robot.pressTheButton(pointList.get(pointList.size()-1).mouseButton);
            robot.delay(pointList.get(pointList.size()-1).delayTime);
        }
    }
}