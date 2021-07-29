package de.frankfurtuniversity.protocol.of.entities.actionpayload;

public abstract class ActionPayload {
    abstract public String toString();
    abstract public byte[] toHeader();
}
