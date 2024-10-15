package org.wolffi.cueprogram.model.cue;

import javafx.scene.media.Media;

import java.io.File;

/**
 * ToDo: Add fade out/in time to cue class
 * Add list index to cue class
 * Instead of using setters add another constructor which expects all parameters or via one update method which expects
 * a AudioCue object
 */
public class AudioCue implements Cue {

    private String name;
    private String path;
    private Double startTime;
    private Double endTime;
    private Double volume;
    private Media sound;

    public AudioCue(String name, String path, double volume) {
        this.name = name;
        this.path = path;
        this.volume = volume;
        loadSound(this.path);
    }

    public AudioCue(String name, String path, double volume, double startTime, double endTime) {
        this.name = name;
        this.path = path;
        this.volume = volume;
        this.startTime = startTime;
        this.endTime = endTime;
        loadSound(this.path);
    }

    private void loadSound(String path) { this.sound = new Media(new File(path).toURI().toString()); }

    public void setName(String name) { this.name = name; }

    public void setPath(String path) {
        this.path = path;
        loadSound(path);
    }

    @Override
    public void setStart(double startTime) { this.startTime = startTime; }

    @Override
    public void setStop(double endTime) { this.endTime = endTime; }

    public String getName() { return this.name; }

    public String getPath() {
        return  this.path;
    }

    public Double getVolume() { return this.volume; }

    public Media getSound() {
        return this.sound;
    }

}
