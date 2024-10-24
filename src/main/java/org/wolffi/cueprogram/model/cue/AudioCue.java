package org.wolffi.cueprogram.model.cue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.wolffi.cueprogram.model.enums.FadeType;

import java.io.File;


/**
 * ToDo:
 * Possibility to add Output source
 * Add color and page as property for every cue
 * Maybe a class like cue setting?
 * Fade out at pause, then increase start time by fade out time
 * Instead of fade in/out methods just use properties to enable it
 * Instead of static value for fade time set it via constructor
 * Instead of using setters add another constructor which expects all parameters or via one update method which expects
 * a AudioCue object
 * This class represents a sound. It manages all settings such as: volume, fade in/out, etc.
 * @author Wolffi
 */
public class AudioCue implements Cue {

    private static final Logger log = LogManager.getLogger();

    private Integer index;
    private String name;
    private String path;
    private MediaPlayer player;
    private Double startTime;
    private Double stopTime;
    private Double fadeInDuration;
    private Double fadeOutDuration;
    private Double volume;
    private Timeline fadeIn;
    private Timeline fadeOut;


    public AudioCue(int index, String name, String path, double volume) {
        this.index = index;
        this.name = name;
        this.path = path;
        this.player = new MediaPlayer(loadSound(this.path));
        this.startTime = 0.0d;
        this.stopTime = this.player.getMedia().getDuration().toMillis();
        this.fadeInDuration = 3_000.0d;
        this.fadeOutDuration = 3_000.0d;
        this.volume = volume;
        this.fadeIn = createFade(FadeType.FADE_IN, this.fadeInDuration);
        this.fadeOut = createFade(FadeType.FADE_OUT, this.fadeOutDuration);
        this.player.setStartTime(Duration.millis(this.startTime));
        this.player.setStopTime(Duration.millis(this.stopTime));
        this.player.setVolume(this.volume);
    }

    public AudioCue(String name, String path, double volume, double startTime, double endTime) {
        this.name = name;
        this.path = path;
        this.volume = volume;
        this.startTime = startTime;
        this.stopTime = endTime;
        this.player = new MediaPlayer(loadSound(this.path));
    }

    /**
     * Loads a sound file and returns it as media for a player.
     * @param path The path of the sound file
     * @return A media object which has loaded the provided sound file.
     */
    private Media loadSound(String path) { return new Media(new File(path).toURI().toString()); }


    /**
     * This method ensure that the file has a clean beginning if it is replayed.
     * Without a new media object you will hear a rest of it where it stopped if you click on play again.
     */
    private void stopPlayer() {
        this.player.stop();
        this.player.dispose();
        this.player = new MediaPlayer(loadSound(this.path));
        this.player.setVolume(volume);
        this.player.setStartTime(Duration.millis(this.startTime));
    }

    /**
     * Creates a fade in/out timeline for the cue depending on type and duration.
     * @param type The fade type
     * @param duration The fade duration
     * @return A timeline for fading
     */
    private Timeline createFade(FadeType type, double duration) {
        Timeline fade = new Timeline();
        Duration fadeDuration = Duration.millis(duration);
        double fadeSteps = 360.0d;
        double volumeChange = 1.0d / fadeSteps;

        for (int i = 0; i < fadeSteps; i++) {
            double volume = (type == FadeType.FADE_IN) ? (i * volumeChange) : 1.0d - (i * volumeChange);
            fade.getKeyFrames().add(
                    new KeyFrame(fadeDuration.divide(fadeSteps).multiply(i),
                            _ -> this.player.setVolume(volume))
            );
        }
        fade.setCycleCount(1);
        if (type == FadeType.FADE_OUT) {
            fade.setOnFinished(_ ->
                    this.stopPlayer()
            );
        }
        return fade;
    }

    /**
     * {@inheritDoc}
     * If fade in is enabled the cue is smoothly faded in.
     */
    @Override
    public void play() {
        this.player.play();
//        this.fadeIn.play();
        log.debug("Playing \"{}\" with index: {}", this.name, this.index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        this.stopPlayer();
        log.debug("Stopped \"{}\" with index: {}", this.name, this.index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fadeIn() {
        this.player.play();
        this.fadeIn.play();
        log.debug("Faded in \"{}\" with index: {}", this.name, this.index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fadeOut() {
        this.fadeOut.play();
        log.debug("Faded out \"{}\" with index: {}", this.name, this.index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pause() {
        this.player.setStartTime(this.player.getCurrentTime()/*.add(Duration.millis(this.fadeOutDuration))*/);
        this.player.stop();
        log.debug("Paused \"{}\" with index: {}", this.name, this.index);
    }

    public void changeSound(String path) {
        this.player.stop();
        this.path = path;
        this.player = new MediaPlayer(loadSound(this.path));
        this.player.setVolume(this.volume);
    }

    public void editFadeIn(double duration) {
        this.fadeInDuration = duration;
        this.fadeIn = createFade(FadeType.FADE_IN, this.fadeInDuration);
    }

    public void editFadeOut(double duration) {
        this.fadeOutDuration = duration;
        this.fadeOut = createFade(FadeType.FADE_OUT, this.fadeOutDuration);
    }


    public void setIndex(int index) { this.index = index; }

    public void setName(String name) { this.name = name; }

    public void setStartTime(double startTime) { this.startTime = startTime; }

    public void setSEndTime(double endTime) { this.stopTime = endTime; }

    public void setVolume(double volume) { this.volume = volume; }


    public int getIndex() { return this.index; }

    public String getName() { return this.name; }

    public String getPath() {
        return  this.path;
    }

    public Double getVolume() { return this.volume; }
}
