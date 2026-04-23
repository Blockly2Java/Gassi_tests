package wrappers;

import levenshtein.AttributeWrapper;
import levenshtein.ClassWrapper;
import levenshtein.ConstructorWrapper;
import levenshtein.MethodWrapper;

public class HundWrapper<T> extends ClassWrapper<T>
{
    private final ConstructorWrapper<T> constructor;

    private final AttributeWrapper<T, Object> group;

    private final MethodWrapper<T, ?> beiFuss;
    private final MethodWrapper<T, ?> zuPositionLaufen;
    private final MethodWrapper<T, Double> getX;
    private final MethodWrapper<T, Double> getY;

    public HundWrapper()
    {
        super(
            "Hund",
            "",
            "public"
        );

        constructor = new ConstructorWrapper<>(
            this,
            new Class<?>[] {},
            "public"
        );

        group = new AttributeWrapper<>(
            this,
            "group",
            resolveClass("Group"),
            "private"
        );

        beiFuss = new MethodWrapper<>(
            this,
            "beiFuss",
            void.class,
            new Class<?>[] { resolveClass("Mensch") },
            "public"
        );

        zuPositionLaufen = new MethodWrapper<>(
            this,
            "zuPositionLaufen",
            void.class,
            new Class<?>[] { double.class, double.class },
            "public"
        );

        getX = new MethodWrapper<>(
            this,
            "getX",
            double.class,
            "public"
        );

        getY = new MethodWrapper<>(
            this,
            "getY",
            double.class,
            "public"
        );
    }

    @Override
    public Object getObj(boolean forceNew, boolean useByteBuddy) {
        return getObj(forceNew, useByteBuddy, constructor);
    }

    public Object getObj() {
        return getObj(false, true);
    }

    public ConstructorWrapper<T> constructor() {
        return constructor;
    }

    public AttributeWrapper<T, Object> group() {
        return group;
    }

    public MethodWrapper<T, ?> beiFuss() {
        return beiFuss;
    }

    public MethodWrapper<T, ?> zuPositionLaufen() {
        return zuPositionLaufen;
    }

    public MethodWrapper<T, Double> getX() {
        return getX;
    }

    public MethodWrapper<T, Double> getY() {
        return getY;
    }

    @SuppressWarnings("unchecked")
    private static <X> Class<X> resolveClass(String className) {
        try {
            return (Class<X>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Required class not found: " + className, e);
        }
    }
}