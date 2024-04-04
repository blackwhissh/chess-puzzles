package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.Cell;
import com.epam.rd.autotasks.chesspuzzles.ChessPiece;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.epam.rd.autotasks.chesspuzzles.Cell.cell;

@Configuration
public class RegisteringChessPieces {

    @Bean
    public static BeanFactoryPostProcessor readState(final String variant) {
        return beanFactory -> {

            final char[][] cells = expectedBoard(variant);

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (cells[i][j] != '.') {
                        beanFactory.registerSingleton(
                                "chessPiece" + i + "" + j,
                                piece(cells[i][j], cell((char) ('A' + j), 8 - i))
                        );
                    }
                }
            }
        };
    }

    private static ChessPiece piece(Character chr, Cell cell) {
        return new ChessPiece() {
            @Override
            public Cell getCell() {
                return cell;
            }

            @Override
            public char toChar() {
                return chr;
            }
        };
    }

    private static char[][] expectedBoard(String name) {
        try {
            return Files.lines(Paths.get("src", "test", "resources", "boards", name + ".txt"))
                    .map(String::toCharArray)
                    .toArray(char[][]::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
