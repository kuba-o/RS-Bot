package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class CoordsReader {

    public ArrayList<Coord> readCoords(String name) {
        ArrayList<Coord> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(name))){
            String readLine;
            while (Objects.nonNull( readLine = br.readLine())){
                String[] ar=readLine.split(",");
                list.add(new Coord(ar));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}