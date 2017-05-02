package io.github.phantamanta44.mcrail.railtech.common.recipe;

public class RecipeType<I, O> {

    private final Class<?> inputType;

    private final Class<?> outputType;

    public RecipeType(Class<?> inputType, Class<?> outputType) {
        this.inputType = inputType;
        this.outputType = outputType;
    }

    public Class<?> inputType() {
        return inputType;
    }

    public Class<?> outputType() {
        return outputType;
    }

}
