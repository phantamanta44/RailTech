package io.github.phantamanta44.mcrail.railtech.machine.recipe.input;

public interface IMachineInput<T> {

    boolean matches(T input);

    T consume(T input);

}
