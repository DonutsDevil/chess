package com.swapnil.chess.movement

import androidx.compose.ui.unit.IntOffset
import com.swapnil.chess.pieces.Piece

enum class DialognalMovement {
    UpRight,
    UpLeft,
    DownRight,
    DownLeft
}


fun Piece.getDiagonalMovement(
    movement: DialognalMovement,
    allPiece: List<Piece>,
    maxMoves: Int
): Set<IntOffset> {
    return getMoves(
        pieces = allPiece,
        maxMovements = maxMoves,
        getTargetPosition = { nextMove ->
            when (movement) {
                DialognalMovement.UpLeft ->
                    IntOffset(
                        x = position.x - nextMove,
                        y = position.y + nextMove
                    )

                DialognalMovement.UpRight ->
                    IntOffset(
                        x = position.x + nextMove,
                        y = position.y + nextMove
                    )

                DialognalMovement.DownLeft ->
                    IntOffset(
                        x = position.x - nextMove,
                        y = position.y - nextMove,
                    )

                DialognalMovement.DownRight ->
                    IntOffset(
                        x = position.x + nextMove,
                        y = position.y - nextMove,
                    )
            }
        }
    )
}