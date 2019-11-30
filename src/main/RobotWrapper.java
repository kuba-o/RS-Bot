package main;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RobotWrapper {
    private final Robot robot;
    Coord actual = new Coord();
    public RobotWrapper() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public void delay(int del){
        robot.delay(del);
    }

    public void move(Coord end){
        robot.mouseMove((int)end.x, (int)end.y);
        robot.delay(2);
    }

    public void smoothMove(Coord start, Coord end, GeometricTools geometricTools){
        actual.x = start.x;
        actual.y = start.y;

        int startX = (int)start.x;
        int startY = (int)start.y;
        int endX = (int)end.x;
        int endY = (int)end.y;
        int actualX=(int)start.x;
        int actualY;
        for (int i=0; i<Math.abs(start.x - end.x); i++){
            if (startX>endX){
                actualX--;
            }
            else{
                actualX++;
            }
            actualY = (int)geometricTools.countY(start, end, actualX);
            move(new Coord(actualX, actualY));
        }
    }

    public void pressTheButton(String c){
        switch (c){
            case "L": {
                mousePress(InputEvent.BUTTON1_DOWN_MASK);
                break;
            }

            case "R": {
                mousePress(InputEvent.BUTTON3_DOWN_MASK);
                break;
            }

            case "1":{
                pressKey(KeyEvent.VK_1);
                break;
            }

            case "2":{
                pressKey(KeyEvent.VK_2);
                break;
            }

            default:{
                System.out.println("Ten znak nie został zmapowany: " + c);
            }
        }
    }

    private void pressKey(int vk1) {
        robot.delay(150);
        robot.keyPress(vk1);
        robot.delay(100);
        robot.keyRelease(vk1);
    }

    private void mousePress(int vk1){
        robot.delay(150);
        robot.mousePress(vk1);
        robot.delay(100);
        robot.mouseRelease(vk1);
    }

    public void pressLeft(){
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(150);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(100);
    }

    public void pressRight(){
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        robot.delay(150);
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
        robot.delay(100);
    }

    public void moveToStart(Coord start, GeometricTools geometricTools){
        // move(start);
        smoothMove(new Coord(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y), start, geometricTools);
        pressLeft();
    }
}