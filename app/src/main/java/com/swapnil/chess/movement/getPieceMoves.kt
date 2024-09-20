package com.swapnil.chess.movement

import androidx.compose.ui.unit.IntOffset
import com.swapnil.chess.pieces.Piece

fun Piece.getPieceAllMoves(
    pieces: List<Piece>,
    block: PieceMovesBuilder.() -> Unit
): Set<IntOffset> {
    val builder = PieceMovesBuilder(
        piece = this,
        pieces = pieces
    )
    builder.block()
    return builder.build()
}

class PieceMovesBuilder(
    private val piece: Piece,
    private val pieces: List<Piece>,
) {
    private val moves = mutableSetOf<IntOffset>()

    fun straightMoves(
        maxMovements: Int = 7,
    ) {
        StraightMovement.entries.forEach { movement ->
            straightMoves(
                movement = movement,
                maxMovements = maxMovements,
            )
        }
    }

    private fun straightMoves(
        movement: StraightMovement,
        maxMovements: Int = 7,
    ) {
        moves.addAll(
            piece.getStraightMovement(
                allPiece = pieces,
                movement = movement,
                maxMoves = maxMovements,
            )
        )
    }

    fun diagonalMoves(
        maxMovements: Int = 7,
    ) {
        DialognalMovement.entries.forEach { movement ->
            diagonalMoves(
                movement = movement,
                maxMovements = maxMovements,
            )
        }
    }

    private fun diagonalMoves(
        movement: DialognalMovement,
        maxMovements: Int = 7,
    ) {
        moves.addAll(
            piece.getDiagonalMovement(
                allPiece = pieces,
                movement = movement,
                maxMoves = maxMovements,
            )
        )
    }

    fun build(): Set<IntOffset> = moves.toSet()
}