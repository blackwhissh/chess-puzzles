package com.epam.rd.autotasks.chesspuzzles;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.function.Function.identity;

public class ChessBoardImpl implements ChessBoard {

    private final Map<Cell, ChessPiece> pieces;

    public ChessBoardImpl(final Collection<ChessPiece> pieces) {
        checkArgument(pieces.stream().map(ChessPiece::getCell).distinct().count() == pieces.size());
        this.pieces = pieces.stream().collect(Collectors.toMap(
            ChessPiece::getCell,
            identity()
        ));
    }

    @Override
    public String state() {
        Table<Character, Integer, Character> cells = TreeBasedTable.create();
        for (int number = 1; number <= 8; number++) {
            for (char letter = 'A'; letter <= 'H'; letter++) {
                cells.put(letter, number, '.');
            }
        }

        pieces.values().forEach(piece -> cells.put(piece.getCell().letter, piece.getCell().number, piece.toChar()));

        StringBuilder builder = new StringBuilder();
        for (int i = 8; i > 0; i--) {
            cells.column(i).values().forEach(builder::append);
            builder.append("\n");
        }
        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }
}
