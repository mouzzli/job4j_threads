package ru.job4j.io;

import java.io.*;

public class SaveFile {
    public void saveContent(String content, File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        }
    }
}