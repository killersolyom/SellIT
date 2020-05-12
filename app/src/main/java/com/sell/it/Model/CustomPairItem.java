package com.sell.it.Model;

import android.util.Pair;

public class CustomPairItem<F, S> extends Pair {
    /**
     * Constructor for a Pair.
     *
     * @param first  the first object in the Pair
     * @param second the second object in the pair
     */
    public CustomPairItem(F first, S second) {
        super(first, second);
    }

    @Override
    public String toString() {
        return this.first.toString();
    }
}
