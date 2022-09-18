package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    private synchronized String content(Predicate<Character> filter) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder content = new StringBuilder();
            int data;
            while (((data = reader.read()) != -1)) {
                if (filter.test((char) data)) {
                    content.append((char) data);
                }
            }

            return content.toString();
        }
    }

    public String getContent() throws IOException {
        return content(character -> true);
    }

    public String getContentWithoutUnicode() throws IOException {
        return content(character -> character < 0x80);
    }
}