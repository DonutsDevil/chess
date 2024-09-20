package com.swapnil.chess.pieces

import androidx.compose.ui.unit.IntOffset

interface Piece {
    var position: IntOffset
    val name: String
    val color: Color

    enum class Color {
        Black,
        White
    }

    fun getAvailableMoves(pieces: List<Piece>): Set<IntOffset>
}