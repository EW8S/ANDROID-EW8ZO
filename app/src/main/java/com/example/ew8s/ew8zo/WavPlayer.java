package com.example.ew8s.ew8zo;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Aliaksandr Kisel (EW8S) on 27.12.2017.
 * This class implements system low-level playback of wav files
 * File must be MONO, 8 BIT and can have a different bitrate
 */

public class WavPlayer {

    AudioTrack at;
    //------------------------------------------------------------------------------------------
    //Playing a file from a stream
    public boolean play(InputStream is) {

        int i = 0;
        byte[] music = null;    //Buffer for playing a file
        byte[] meta = new byte[44];

        try {
            is.read(meta,0,44); //get meta data from file

            int bitRate = ((int) meta[25]<<8)+meta[24]; //Determine the sample rate

            //Determine the minimum buffer size for the AudioTrack component
            int minBufferSize = AudioTrack.getMinBufferSize(bitRate, AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_8BIT);

            // Initialize AudioTrack
            at = new AudioTrack(AudioManager.STREAM_MUSIC, bitRate,
                    AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_8BIT,
                    minBufferSize, AudioTrack.MODE_STREAM);

            //Initialize the buffer for reading
            music = new byte[512];
            //Start playback
            at.play();
            //Load into AudioTrack the data from the stream until the stream finishes
            while((i = is.read(music)) != -1)
                at.write(music, 0, i);

            at.stop();      //Stops playback
            at.release();   //Releasing resources AudioTrack
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
