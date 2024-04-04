package com.epam.rd.autotasks.chesspuzzles;

public class ChessPieceImpl implements ChessPiece {
    private final Cell cell;
    private final char symbol;

    public ChessPieceImpl(Cell cell, char symbol) {
        this.cell = cell;
        this.symbol = symbol;
    }

    @Override
    public Cell getCell() {
        return cell;
    }

    @Override
    public char toChar() {
        return symbol;
    }
}
