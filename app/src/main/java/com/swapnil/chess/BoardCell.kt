package com.swapnil.chess

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.swapnil.chess.board.Board
import com.swapnil.chess.pieces.Piece


@Composable
fun BoardCell(
    x: Int,
    y: Int,
    piece: Piece?,
    board: Board,
    isAvailableMove: Boolean = false,
    isOverLapping: Boolean = false,
    modifier: Modifier = Modifier
) {
    val backgroundColor =
        when {
            piece != null && piece == board.selectedPiece ->
                Color.Cyan

            (x + y) % 2 == 0 ->
                Color.Black

            else ->
                Color.Gray
        }


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = backgroundColor
            )
    ) {

        piece?.let {
            Text(
                text = it.name,
                color = Color.Red,
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {
                        board.selectPiece(it)
                    }
                    .fillMaxSize()
                    .padding(8.dp)
            )
        }

        if (isAvailableMove)
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(
                        color = if(isOverLapping) Color.Magenta else Color.Yellow)
                    .fillMaxSize()
            )
    }
}