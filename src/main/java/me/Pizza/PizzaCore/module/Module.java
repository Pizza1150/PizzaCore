package me.Pizza.PizzaCore.module;

public interface Module {

    void load();

    void unload();

    default void reload() {
        unload();
        load();
    }
}