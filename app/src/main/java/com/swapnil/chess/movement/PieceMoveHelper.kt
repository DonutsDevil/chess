package com.swapnil.chess.movement

import androidx.compose.ui.unit.IntOffset
import com.swapnil.chess.board.BoardXAxis
import com.swapnil.chess.board.BoardYAxis
import com.swapnil.chess.pieces.Piece


fun Piece.getMoves(
    pieces: List<Piece>,
    getTargetPosition: (Int) -> IntOffset,
    maxMovements: Int,
): Set<IntOffset> {
    val moves = mutableSetOf<IntOffset>()

    for (i in 1..maxMovements) {
        val targetPosition = getTargetPosition(i)

        if (targetPosition.x !in BoardXAxis || targetPosition.y !in BoardYAxis)
            break

        val targetPiece = pieces.find { it.position == targetPosition }

        if (targetPiece != null) {
            if (targetPiece.color != this.color)
                moves.add(targetPosition)

            break
        } else {
            moves.add(targetPosition)
        }
    }

    return moves
}