package edu.caltech.cs2.project08.bots;

import edu.caltech.cs2.project08.game.Board;
import edu.caltech.cs2.project08.game.Evaluator;
import edu.caltech.cs2.project08.game.Move;

public class MinimaxSearcher<B extends Board> extends AbstractSearcher<B> {
    @Override
    public Move getBestMove(B board, int myTime, int opTime) {
        BestMove best = minimax(this.evaluator, board, ply);
        return best.move;
    }

    private static <B extends Board> BestMove minimax(Evaluator<B> evaluator, B board, int depth) {
        if (depth == 0 || board.getMoves().size()==0) {
            return new BestMove(evaluator.eval(board));
        }

        double bestValue = Double.NEGATIVE_INFINITY;
        BestMove currBest = new BestMove(0);
        for (Move move : board.getMoves()) {
            board.makeMove(move);
            BestMove possibleMove = new BestMove(move, -minimax(evaluator, board, depth - 1).score);
            board.undoMove();
            if (possibleMove.score > bestValue) {
                currBest = possibleMove;
                bestValue = possibleMove.score;
            }
        }
        return currBest;
    }
}