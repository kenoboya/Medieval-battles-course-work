package codeGame;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class Sound
{
    private static MediaPlayer soundtrack;
    public static void playSound(String pathName)
    {
        Media sound = new Media(new File(pathName).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
    public static void playSoundTrack()
    {
        Media sound = new Media
                (new File("D:\\project\\game\\src\\main\\java\\codeGame\\sound\\soundtrackOriginSound.mp3")
                        .toURI().toString());
        soundtrack = new MediaPlayer(sound);
        soundtrack.setAutoPlay(true);
        soundtrack.setCycleCount(MediaPlayer.INDEFINITE);
        soundtrack.setOnEndOfMedia(() -> soundtrack.seek(Duration.ZERO));
    }
    public static void stopSoundTrack() {soundtrack.stop();}
    public static void playAgainSoundTrack(){soundtrack.play();}

}
