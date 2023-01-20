package com.pil.group4.services;

import com.pil.group4.models.LocationModel;
import com.pil.group4.models.RecyclingZoneModel;

import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        RecyclingZoneModel zone1 = new RecyclingZoneModel();
        RecyclingZoneModel zone2 = new RecyclingZoneModel();
        RecyclingZoneModel zone3 = new RecyclingZoneModel();

        LocationModel location1 = new LocationModel();
        LocationModel location2 = new LocationModel();
        LocationModel location3 = new LocationModel();

        location1.setCoordinates(new Point(2, 2));
        location2.setCoordinates(new Point(1, 1));
        location3.setCoordinates(new Point(3, 3));


        zone1.setLocation(location1);
        zone2.setLocation(location2);
        zone3.setLocation(location3);

        ArrayList<RecyclingZoneModel> recZones = new ArrayList<>();
        recZones.add(zone1);
        recZones.add(zone2);
        recZones.add(zone3);

        BestRoute route = new BestRoute(new Point(0, 0), recZones);
        route.setShortestRoute();
        System.out.println(route.sortedRecyclingZonesString());


    }

    public static class BestRoute {

        private final Point startingPoint;
        private ArrayList<RecyclingZoneModel> recyclingZones;
        private final ArrayList<RecyclingZoneModel> sortedRecyclingZones = new ArrayList<>();

        public BestRoute(Point startingPoint, ArrayList<RecyclingZoneModel> recyclingZones) {
            this.startingPoint = startingPoint;
            this.recyclingZones = recyclingZones;
        }

        public ArrayList<RecyclingZoneModel> getSortedRecyclingZones() {
            return sortedRecyclingZones;
        }

        public void setShortestRoute() {
            recyclingZones.removeIf(recyclingZone -> recyclingZone.getLocation() == null);
            if (recyclingZones.isEmpty()) {
                return;
            }

            RecyclingZoneModel startZone = new RecyclingZoneModel();
            startZone.setLocation(new LocationModel());
            startZone.getLocation().setCoordinates(startingPoint);

            sortedRecyclingZones.add(startZone);

            while (!recyclingZones.isEmpty()) {
                RecyclingZoneModel closestZone = getClosestZone(sortedRecyclingZones.get(sortedRecyclingZones.size() - 1));
                sortedRecyclingZones.add(closestZone);
                recyclingZones.remove(closestZone);
            }

            sortedRecyclingZones.remove(0);

        }

        private RecyclingZoneModel getClosestZone(RecyclingZoneModel recyclingZoneModel) {
            RecyclingZoneModel closestZone = recyclingZones.get(0);
            double closestDistance = getDistance(recyclingZoneModel, closestZone);
            for (RecyclingZoneModel zone : recyclingZones) {
                double distance = getDistance(recyclingZoneModel, zone);
                if (distance < closestDistance) {
                    closestZone = zone;
                    closestDistance = distance;
                }
            }
            return closestZone;
        }

        private double getDistance(RecyclingZoneModel recyclingZoneModel, RecyclingZoneModel closestZone) {
            return Math.sqrt(Math.pow(recyclingZoneModel.getLocation().getCoordinates().getX() - closestZone.getLocation().getCoordinates().getX(), 2) +
                    Math.pow(recyclingZoneModel.getLocation().getCoordinates().getY() - closestZone.getLocation().getCoordinates().getY(), 2));
        }

        public String sortedRecyclingZonesString(){

            if (sortedRecyclingZones.isEmpty()){
                return "No recycling zones found";
            }

            if (sortedRecyclingZones.size() == 1){
                return "Only one recycling zone found";
            }

            StringBuilder result = new StringBuilder("Starting from point: (" + startingPoint.getX() + ", " + startingPoint.getY() + ") the best route is:\n");
            for (RecyclingZoneModel sortedRecyclingZone : sortedRecyclingZones) {
                result.append("Zone name: ").append(sortedRecyclingZone.getName()).append(": (").append(sortedRecyclingZone.getLocation().getCoordinates().getX()).append(", ").append(sortedRecyclingZone.getLocation().getCoordinates().getY()).append(")\n");
            }
            return result.toString();
        }
    }
}
