import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
   
public enum SoundEffect {
   WALL_BOUNCE("sfx/punch.wav"),
   PAD_BOUNCE("sfx/hitMetal.wav"),
   SCORE("sfx/score.wav");
   
   public static enum SFX {
      OFF, ON
   }
   
   public static SFX sfx = SFX.OFF;
   
   // Each sound effect has its own clip, loaded with its own sound file.
   private Clip clip;
   
   // Constructor to construct each element of the enum with its own sound file.
   SoundEffect(String soundFileName) {
      try {
         URL url = this.getClass().getClassLoader().getResource(soundFileName);
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
         clip = AudioSystem.getClip();
         clip.open(audioInputStream);
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }
   
   public void play() {
      if (sfx != SFX.OFF) {
         if (clip.isRunning())
            clip.stop();   // Stop the player if it is still running
         clip.setFramePosition(0); // rewind to the beginning
         clip.start();     // Start playing
      }
   }
   
   static void init() {
      values(); // calls the constructor for all the elements
   }
}