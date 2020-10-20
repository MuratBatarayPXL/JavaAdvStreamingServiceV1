package be.pxl.ja.streamingservice.model;


import be.pxl.ja.streamingservice.exception.InvalidDateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Profile implements Comparable<Profile> {

    private String name;
    private LocalDate dateOfBirth;
    private String avatar;
    private ArrayList<Content> myList;
    private ArrayList<Content> currentlyWatching;
    private ArrayList<Content>recentlyWatched;

    public Profile(String name) {
        currentlyWatching = new ArrayList<>();
        recentlyWatched = new ArrayList<>();
        myList = new ArrayList<>();
        this.name = name;
        this.avatar = "profile1";
    }
    public Profile(String name, String avatar){
        currentlyWatching = new ArrayList<>();
        recentlyWatched = new ArrayList<>();
        myList = new ArrayList<>();
        this.name = name;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth.isAfter(LocalDate.now())) {
            throw new InvalidDateException(dateOfBirth, "date of birth", "No date of birth in future allowed.");
        }
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public int compareTo(Profile other) {
        return name.compareTo(other.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Profile profile = (Profile) o;

        return getName() != null ? getName().equals(profile.getName()) : profile.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    public int getAge() {
        if (dateOfBirth == null) {
            return 0;
        }
        return (int) ChronoUnit.YEARS.between(dateOfBirth, LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + "\'," +
                "age=" + getAge() +
                '}';
    }


    public boolean allowedToWatch(Content content) {
        return content.getMaturityRating().getMinimumAge() <= getAge();
    }

    public void startWatching(Content content){
        currentlyWatching.add(content);
    }
    public void finishWatching(Content content){
        currentlyWatching.remove(content);
        recentlyWatched.add(content);
    }

    public String getAvatar() {
        return avatar;
    }

    public List<Content> getRecentlyWatched() {
        return recentlyWatched;
    }

    public List<Content> getCurrentlyWatching() {
        return currentlyWatching;
    }

    public List<Content> getMyList() {
        return myList;
    }
    public void addToMyList(Content content){
        myList.add(content);
    }
    public boolean isInMyList(Content content){
        return myList.contains(content);
    }
    public void removeFromMyList(Content content){
        myList.remove(content);
    }
}
