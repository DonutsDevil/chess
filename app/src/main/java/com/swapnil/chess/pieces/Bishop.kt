package com.swapnil.chess.pieces

import androidx.compose.ui.unit.IntOffset
import com.swapnil.chess.movement.getPieceAllMoves

class Bishop(
    override val color: Piece.Color,
    override var position: IntOffset,
): Piece {
    override val name = "B"

    override fun getAvailableMoves(pieces: List<Piece>): Set<IntOffset> {
        return getPieceAllMoves(pieces) {
            diagonalMoves()
        }
    }
}