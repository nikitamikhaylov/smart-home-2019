package ru.sbt.mipt.oop;

public interface Action<T> {
    void execute(T object);
}