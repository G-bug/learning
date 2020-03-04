package com.test.stream;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreatingStreams {

    public static <T> void show(String title, Stream<T> stream) {

        final int SIZE = 10;
        List<T> firstElements = stream.limit(SIZE + 1).collect(Collectors.toList());

        System.out.print(title + ": ");

        for (int i = 0; i < firstElements.size(); i++) {
            if (i > 0) System.out.print(", ");

            if (i < SIZE) System.out.print(firstElements.get(i));

            else System.out.print("...");
        }

        System.out.println();
    }


    public static void main(String[] args) throws Exception {
        Path path = Paths.get(System.getProperty("user.dir") + "\\directory\\test.txt");
        String contens = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

        Stream<String> words = Stream.of(contens.split("\\PL+"));
        show("words", words);

        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        show("song", song);

        // 空流
        Stream<String> silence = Stream.empty();
        show("silence", silence);
        // 无限流
        Stream<String> echos = Stream.generate(() -> "echo");
        show("echos", echos);
        // 无限序列
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, BigInteger.ONE::add);

        show("integers", integers);
        // 无限流
        Stream<Double> doubles = Stream.generate(Math::random);
        show("doubles", doubles);

        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contens);
        show("wordsAnotherWay", wordsAnotherWay);

        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            show("lines", lines);
        }
    }

}
