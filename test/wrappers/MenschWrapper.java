package wrappers;

import levenshtein.AttributeWrapper;
import levenshtein.ClassWrapper;
import levenshtein.ConstructorWrapper;
import levenshtein.MethodWrapper;

public class MenschWrapper<T> extends ClassWrapper<T>
{
    private final ConstructorWrapper<T> constructor;

    private final AttributeWrapper<T, Object> grafikGruppe;
    private final AttributeWrapper<T, Object> meinHund;

    private final MethodWrapper<T, ?> gassiGehen;
    private final MethodWrapper<T, ?> hundRufen;
    private final MethodWrapper<T, Double> getX;
    private final MethodWrapper<T, Double> getY;

    public MenschWrapper()
    {
        super(
            "Mensch",
            "",
            "public"
        );

        constructor = new ConstructorWrapper<>(
            this,
            new Class<?>[] { resolveClass("Hund") },
            "public"
        );

        grafikGruppe = new AttributeWrapper<>(
            this,
            "grafikGruppe",
            resolveClass("Group"),
            "private"
        );

        meinHund = new AttributeWrapper<>(
            this,
            "meinHund",
            resolveClass("Hund"),
            "private"
        );


        gassiGehen = new MethodWrapper<>(
            this,
            "gassiGehen",
            void.class,
            "public"
        );

        hundRufen = new MethodWrapper<>(
            this,
            "hundRufen",
            void.class,
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
        return getObj(forceNew, useByteBuddy, constructor, newHundInstance());
    }

    public Object getObj() {
        return getObj(false, true);
    }

    public ConstructorWrapper<T> constructor() {
        return constructor;
    }

    public AttributeWrapper<T, Object> grafikGruppe() {
        return grafikGruppe;
    }

    public AttributeWrapper<T, Object> meinHund() {
        return meinHund;
    }

    public MethodWrapper<T, Integer> mathRandomInt() {
        return mathRandomInt;
    }

    public MethodWrapper<T, ?> gassiGehen() {
        return gassiGehen;
    }

    public MethodWrapper<T, ?> hundRufen() {
        return hundRufen;
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

    private static Object newHundInstance() {
        try {
            return resolveClass("Hund").getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Unable to instantiate Hund for Mensch constructor", e);
        }
    }
}