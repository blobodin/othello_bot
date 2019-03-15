package edu.caltech.cs2.project08.bots;

import edu.caltech.cs2.project08.game.Board;
import edu.caltech.cs2.project08.game.Evaluator;
import edu.caltech.cs2.project08.game.Move;

public class AlphaBetaSearcher<B extends Board> extends AbstractSearcher<B> {
    @Override
    public Move getBestMove(B board, int myTime, int opTime) {
        BestMove best = alphaBeta(this.evaluator, board, ply, new BestMove(Integer.MIN_VALUE + 1), new BestMove(Integer.MAX_VALUE));
        return best.move;
    }

    private static <B extends Board> BestMove alphaBeta(Evaluator<B> evaluator, B board, int depth, BestMove alpha, BestMove beta) {
        if (depth == 0 || board.getMoves().size()==0) {
            return new BestMove(evaluator.eval(board));
        }

        for (Move move : board.getMoves()) {
            board.makeMove(move);
            beta.negate();
            alpha.negate();
            BestMove possibleMove = new BestMove(move, -alphaBeta(evaluator, board, depth - 1, beta, alpha).score);
            beta.negate();
            alpha.negate();
            board.undoMove();

            if (possibleMove.score > alpha.score) {
                alpha = possibleMove;
            }
            if (alpha.score >= beta.score) {
                return alpha;
            }
        }
        return alpha;
    }
}