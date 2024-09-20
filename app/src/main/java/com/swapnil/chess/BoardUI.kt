package com.swapnil.chess

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.swapnil.chess.board.Board
import com.swapnil.chess.board.BoardXAxis
import com.swapnil.chess.board.BoardYAxis
import com.swapnil.chess.board.rememberIsAvailableMove
import com.swapnil.chess.board.rememberIsOverLapping
import com.swapnil.chess.board.rememberPieceAt

@Composable
fun BoardUi(
    board: Board,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .aspectRatio(1f)
            .fillMaxSize()
            .border(
                width = 8.dp,
                color = Color.White
            )
            .padding(8.dp)
    ) {
        BoardYAxis
            .forEach { y ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    BoardXAxis
                        .forEach { x ->
                            val piece = board.rememberPieceAt(x, y)

                            val isAvailableMove = board.rememberIsAvailableMove(x, y)

                            val isOverlapping = board.rememberIsOverLapping(x, y)


                            Log.d("SWAPNIL", "BoardUi: isOverllaping: $isOverlapping, x: $x, y: $y")


                            BoardCell(
                                x = x,
                                y = y,
                                piece = piece,
                                board = board,
                                isAvailableMove = isAvailableMove,
                                isOverLapping = isOverlapping,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            )
                        }
                }
            }


        Button(onClick = { board.reset() }) {
            Text(text = "Rest")
        }
    }
}