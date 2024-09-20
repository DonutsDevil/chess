package com.swapnil.chess.pieces

import androidx.compose.ui.unit.IntOffset
import com.swapnil.chess.movement.getPieceAllMoves

class Rook(
    override val color: Piece.Color,
    override var position: IntOffset,
): Piece {
    override val name = "R"

    override fun getAvailableMoves(pieces: List<Piece>): Set<IntOffset> {
        return getPieceAllMoves(pieces) {
            straightMoves()
        }
    }


    companion object {
        const val Type = 'R'
    }

}