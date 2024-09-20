package com.swapnil.chess.movement

import androidx.compose.ui.unit.IntOffset
import com.swapnil.chess.pieces.Piece

enum class StraightMovement {
    Up,
    Down,
    Left,
    Right
}

// giving us all the StraightMovement
fun Piece.getStraightMovement(
    movement: StraightMovement,
    allPiece: List<Piece>,
    maxMoves: Int
): Set<IntOffset> {
    return getMoves(
        pieces = allPiece,
        maxMovements = maxMoves,
        getTargetPosition = { nextMove ->
            when (movement) {
                StraightMovement.Up ->
                    IntOffset(
                        x = position.x,
                        y = position.y + nextMove
                    )

                StraightMovement.Down ->
                    IntOffset(
                        x = position.x,
                        y = position.y - nextMove
                    )

                StraightMovement.Left ->
                    IntOffset(
                        x = position.x - nextMove,
                        y = position.y
                    )

                StraightMovement.Right ->
                    IntOffset(
                        x = position.x + nextMove,
                        y = position.y
                    )
            }
        }
    )
}