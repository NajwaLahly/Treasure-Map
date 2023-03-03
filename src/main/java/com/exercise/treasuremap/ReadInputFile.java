package com.exercise.treasuremap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadInputFile {
    private Player player;
    private TreasureMap map;
    private ArrayList<Mountain> mountainList = new ArrayList<Mountain>();
    private Map<Treasure, Integer> treasureMap = new HashMap<>();


    public void readFile(String filePath) {
        File file = new File(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null)
                processLine(line);
            ArrayList<ArrayList<Tile>> mapTileList = setMapTileList();
            setMountainInTiles(mapTileList);
            setTreasuresInTiles(mapTileList);
            map.setTileList(mapTileList);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void processLine(String line) {
        if (line.charAt(0) != '#') {
            String[] splitLine = line.split(" - ");
            int posX = 0;
            int posY = 0;
            switch (splitLine[0]) {
                case "C":
                    posX = Integer.parseInt(splitLine[1]);
                    posY = Integer.parseInt(splitLine[2]);
                    this.map = new TreasureMap(posX, posY);
                    break;
                case "M":
                    posX = Integer.parseInt(splitLine[1]);
                    posY = Integer.parseInt(splitLine[2]);
                    mountainList.add(new Mountain(new Tile(posX, posY)));
                    break;
                case "T":
                    posX = Integer.parseInt(splitLine[1]);
                    posY = Integer.parseInt(splitLine[2]);
                    int nbrOfTreasuresInTile = Integer.parseInt(splitLine[3]);
                    Treasure treasure = new Treasure(new Tile(posX, posY));
                    treasureMap.put(treasure, nbrOfTreasuresInTile);
                    break;
                case "A":
                    String name = splitLine[1];
                    posX = Integer.parseInt(splitLine[2]);
                    posY = Integer.parseInt(splitLine[3]);
                    Orientation orientation = Orientation.valueOf(splitLine[4]);
                    String directionSequence = splitLine[5];
                    this.player = new Player(name, new Tile(posX, posY), orientation, directionSequence);
                    break;
            }
        }
    }

    public ArrayList<ArrayList<Tile>> setMapTileList() {
        ArrayList<ArrayList<Tile>> tileList = new ArrayList<>();
        for (int j = 0; j <= map.getHeight(); j++) {
            ArrayList<Tile> rowOfTiles = new ArrayList<>();
            for (int i = 0; i <= map.getWidth(); i++) {
                Tile tile = new Tile(i, j);
                rowOfTiles.add(tile);
            }
            tileList.add(rowOfTiles);
        }
        return  tileList;
    }

    public void setMountainInTiles(ArrayList<ArrayList<Tile>> tileList){
        for (Mountain mountain:mountainList) {
            tileList.get(mountain.getMountainTile().getPosY()).get(mountain.getMountainTile().getPosX()).setMountain(true);
        }
    }

    public void setTreasuresInTiles(ArrayList<ArrayList<Tile>> tileList){
        treasureMap.forEach((key, value) -> {
            int treasurePosX = key.getTreasureTile().getPosX();
            int treasurePosY = key.getTreasureTile().getPosY();
            Tile tileWithTreasures = tileList.get(treasurePosY).get(treasurePosX);
            tileWithTreasures.setNbrOfTreasure(tileWithTreasures.getNbrOfTreasure() + value);
        });
    }



    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public TreasureMap getMap() {
        return map;
    }

    public void setMap(TreasureMap map) {
        this.map = map;
    }

    public ArrayList<Mountain> getMountainList() {
        return mountainList;
    }

    public void setMountainList(ArrayList<Mountain> mountainList) {
        this.mountainList = mountainList;
    }

    public Map<Treasure, Integer> getTreasureMap() {
        return treasureMap;
    }

    public void setTreasureMap(Map<Treasure, Integer> treasureMap) {
        this.treasureMap = treasureMap;
    }
}
