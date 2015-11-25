package cz.muni.fi.pa165.project.service.util;

import java.util.Objects;

/**
 * Defines transition from one state to another.
 *
 * @param <T> generic type of transition
 * 
 * @author Marek
 */
public class Transition<T> {
    /**
     * Represents old state of transition.
     */
    private final T oldState;
    
    /**
     * Represents new state of transition.
     */
    private final T newState;

    /**
     * Creates transition between two states.
     * 
     * @param oldState old state of transition
     * @param newState new state of transition
     */
    public Transition(T oldState, T newState) {
        this.oldState = oldState;
        this.newState = newState;
    }

    /**
     * Getter for {@link #oldState}.
     * 
     * @return {@link #oldState}
     */
    public T getOldState() {
        return oldState;
    }

    /**
     * Getter for {@link #newState}.
     * 
     * @return {@link #newState}
     */
    public T getNewState() {
        return newState;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + oldState.hashCode();
        hash = 37 * hash + newState.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Transition)) {
            return false;
        }
        
        final Transition<?> other = (Transition<?>) obj;
        
        return this.getOldState().equals(other.getOldState()) && 
                this.getNewState().equals(other.getNewState());
    }

    @Override
    public String toString() {
        return "Transition{" + 
                "oldState=" + oldState + 
                ", newState=" + newState + 
                '}';
    }
}
