package dev.plecerf.rps.game.strategy;

import com.noleme.commons.string.Strings;
import dev.plecerf.rps.game.Action;
import dev.plecerf.rps.game.outcome.Outcome;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static dev.plecerf.rps.game.Action.*;

/**
 * @author Pierre Lecerf (plecerf@lumiomedical.com)
 * Created on 2020/12/09
 */
public class MarkovChainStrategy implements GameStrategy
{
    private final GameStrategy fallback = new RandomStrategy();
    private final Map<String, State> matrix;
    private final double decay;
    private Action currentCPUPlayedAction;
    private Action lastCPUPlayedAction;
    private Action lastPlayerPlayedAction;

    private static final Action[] possibleActions = {ROCK, PAPER, SCISSOR};

    public MarkovChainStrategy()
    {
        this(0.8f);
    }

    public MarkovChainStrategy(double decay)
    {
        this.decay = decay;
        this.matrix = createMatrix();
    }

    @Override
    public Action play()
    {
        Action action;

        if (this.lastCPUPlayedAction == null)
            action = this.fallback.play();
        else {
            String pair = getKey(this.lastCPUPlayedAction, this.lastPlayerPlayedAction);

            action = this.matrix.get(pair).stats.entrySet().stream()
                .sorted(Comparator.comparingDouble(e -> e.getValue().probability))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("The MarkovChain strategy matrix is in an illegal state (it is most likely empty)"))
            ;
        }

        this.currentCPUPlayedAction = action;

        return action;
    }

    @Override
    public void acknowledgeLastPlay(Action lastPlay, Outcome outcome)
    {
        if (this.lastPlayerPlayedAction != null)
        {
            /* Memory decay */
            this.matrix.values().forEach(state -> {
                state.stats.values().forEach(stat -> stat.observationCount = stat.observationCount * this.decay);
            });

            String pair = getKey(this.lastCPUPlayedAction, this.lastPlayerPlayedAction);

            this.matrix.get(pair).stats.get(lastPlay).observationCount += 1f;

            /* Weight update */
            double totalObservations = 0;
            for (Statistics stats : this.matrix.get(pair).stats.values())
                totalObservations += stats.observationCount;

            for (Statistics stats : this.matrix.get(pair).stats.values())
                stats.probability = stats.observationCount / totalObservations;
        }

        this.lastCPUPlayedAction = this.currentCPUPlayedAction;
        this.lastPlayerPlayedAction = lastPlay;
    }

    /**
     *
     * @return
     */
    private static Map<String, State> createMatrix()
    {
        Map<String, State> matrix = new HashMap<>();

        for (Action actionA : possibleActions)
        {
            for (Action actionB : possibleActions)
                matrix.put(getKey(actionA, actionB), new State());
        }

        return matrix;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    private static String getKey(Action a, Action b)
    {
        return a.name()+"-"+b.name();
    }

    private static class State
    {
        final Map<Action, Statistics> stats;

        State()
        {
            this.stats = Stream.of(possibleActions).collect(Collectors.toMap(
                action -> action,
                action -> new Statistics()
            ));
        }
    }

    private static class Statistics
    {
        double probability = 1f/3f;
        double observationCount = 0f;
    }
}
