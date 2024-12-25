package org.distributed.consensus.model;

import java.util.HashSet;
import java.util.Set;

public class BookingPolicies {
    private static Set<Long> bannedLocations = new HashSet<>(); // Banned location IDs
    private static Set<Long> lockedLocations = new HashSet<>(); // Locked location IDs
    private static Set<Long> shutDownLocations = new HashSet<>(); // Shut down location IDs

    static {
        // Example initialization of banned, locked, and shut-down locations
        bannedLocations.add(101L);
        lockedLocations.add(102L);
        shutDownLocations.add(103L);
    }

    public static Boolean canBook(Booking booking) {
        // here are a couple of expensive checks  like:
        // checking if the location was already banned from the platform
        // (e.g.: as a consequence of bad feedback).
        // checking if authorities locked this location
        // (e.g.: risk of life and health)
        // checking if the location was already shut down
        // ...
        if (bannedLocations.contains(booking.getRoomId())) {
            System.out.println("Booking denied: Location is banned.");
            return false;
        }

        // Check if authorities locked this location
        if (lockedLocations.contains(booking.getRoomId())) {
            System.out.println("Booking denied: Location is locked by authorities.");
            return false;
        }

        // Check if the location was already shut down
        if (shutDownLocations.contains(booking.getRoomId())) {
            System.out.println("Booking denied: Location is shut down.");
            return false;
        }

        // Additional expensive checks can be added here

        // If all checks pass, the booking is allowed
        return true;
    }

    // Methods to dynamically update the sets (for admin use or dynamic policies)
    public static void banLocation(Long roomId) {
        bannedLocations.add(roomId);
    }

    public static void lockLocation(Long roomId) {
        lockedLocations.add(roomId);
    }

    public static void shutDownLocation(Long roomId) {
        shutDownLocations.add(roomId);
    }

    public static void removeBan(Long roomId) {
        bannedLocations.remove(roomId);
    }

    public static void unlockLocation(Long roomId) {
        lockedLocations.remove(roomId);
    }

    public static void reopenLocation(Long roomId) {
        shutDownLocations.remove(roomId);
    }
}
