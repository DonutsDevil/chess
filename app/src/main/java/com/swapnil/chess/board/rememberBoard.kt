package com.swapnil.chess.board

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.IntOffset
import com.swapnil.chess.pieces.Bishop
import com.swapnil.chess.pieces.Piece
import com.swapnil.chess.pieces.Rook


@Composable
fun rememberBoard(): Board =
    remember {
        Board()
    }

@Immutable
class Board {
    private val _pieces = mutableStateListOf<Piece>()
    val pieces get() = _pieces.toList()

    init {
        _pieces.addAll(
            listOf(
                Rook(Piece.Color.Black, IntOffset('B'.code, 4)),
                Bishop(Piece.Color.Black, IntOffset('D'.code, 3))
            )
        )
    }

    var selectedPiece by mutableStateOf<Piece?>(null)


    var selectedPieceMoves by mutableStateOf(emptyList<IntOffset>())

    var moveIncrement by mutableIntStateOf(0)
        private set


    fun selectPiece(piece: Piece) {
        if(moveIncrement == 2) return
        selectedPiece = piece
        selectedPieceMoves = selectedPieceMoves + piece.getAvailableMoves(pieces = pieces)
        moveIncrement++
    }

    fun getPiece(x: Int, y: Int): Piece? =
        _pieces.find { it.position.x == x && it.position.y == y }

    fun isAvailableMove(x: Int, y: Int): Boolean =
        selectedPieceMoves.any { it.x == x && it.y == y }

    fun isOverlapping(x: Int, y: Int): Boolean {
        val piecesAtPosition = selectedPieceMoves.count { it.x == x && it.y == y } > 1
        return piecesAtPosition
    }


    fun reset() {
        _pieces.clear()

        val rookPosition = getRandomPosition()
        var bishopPosition = getRandomPosition()

        while (rookPosition == bishopPosition) {
            bishopPosition = getRandomPosition()
        }

        _pieces.addAll(
            listOf(
                Rook(Piece.Color.Black, rookPosition),
                Bishop(Piece.Color.Black, bishopPosition)
            )
        )
        moveIncrement = 0
        clearSelection()
    }

    private fun getRandomPosition(): IntOffset {
        val randomX = BoardXAxis.random()
        val randomY = BoardYAxis.random()
        return IntOffset(randomX, randomY)
    }

    private fun clearSelection() {
        selectedPiece = null
        selectedPieceMoves = emptyList()
    }
}



@Composable
fun Board.rememberPieceAt(x: Int, y: Int): Piece? =
    remember(x, y, moveIncrement) {
        getPiece(
            x = x,
            y = y
        )

    }

@Composable
fun Board.rememberIsAvailableMove(x: Int, y: Int): Boolean =
    remember(x, y, selectedPieceMoves) {
        isAvailableMove(
            x = x,
            y = y,
        )
    }

@Composable
fun Board.rememberIsOverLapping(x: Int, y: Int): Boolean =
    remember(x, y, selectedPieceMoves) {
        isOverlapping(x, y)
    }