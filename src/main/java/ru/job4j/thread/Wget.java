package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private final String directory;

    public Wget(String url, int speed, String directory) {
        this.url = url;
        this.speed = speed;
        this.directory = directory;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(directory)) {
            int bytesCount = 0;
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long startTime = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                bytesCount += bytesRead;
                if (bytesCount >= speed) {
                    long period = startTime - System.currentTimeMillis();
                    if (period < 1000) {
                        try {
                            Thread.sleep(1000 - period);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    startTime = System.currentTimeMillis();
                    bytesCount = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 3) {
            throw new IllegalStateException();
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String directory = args[2];
        Thread wget = new Thread(new Wget(url, speed, directory));
        wget.start();
        wget.join();
    }
}