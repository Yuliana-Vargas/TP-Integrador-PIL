package com.pil.group4.services;

import com.pil.group4.models.LocationModel;
import com.pil.group4.models.RecyclingZoneModel;

import java.awt.*;
import java.util.ArrayList;

public class BestRoute {

    private final Point startingPoint;
    private final ArrayList<RecyclingZoneModel> recyclingZones;
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
        return Math.sqrt(Math.pow(recyclingZoneModel.getLocation().getCoordinates().getX() - closestZone.getLocation()
                .getCoordinates().getX(), 2) + Math.pow(recyclingZoneModel.getLocation().getCoordinates().getY() -
                closestZone.getLocation().getCoordinates().getY(), 2));
    }

    public String sortedRecyclingZonesString() {

        if (sortedRecyclingZones.isEmpty()) {
            return "No recycling zones found";
        }

        if (sortedRecyclingZones.size() == 1) {
            return "Only one recycling zone found";
        }

        int i = 1;
        StringBuilder result = new StringBuilder("Starting from point: (" + startingPoint.getX() + ", " +
                startingPoint.getY() + ") the best route is:\n");
        for (RecyclingZoneModel sortedRecyclingZone : sortedRecyclingZones) {
            result.append(i).append("° Zone name: ").append(sortedRecyclingZone.getName()).append(", coordinates: (")
                    .append(sortedRecyclingZone.getLocation().getCoordinates().getX()).append(", ")
                    .append(sortedRecyclingZone.getLocation().getCoordinates().getY()).append(").\n");
            i++;
        }
        return result.toString();
    }
}
