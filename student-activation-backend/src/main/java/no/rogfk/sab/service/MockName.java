package no.rogfk.sab.service;

import javax.naming.InvalidNameException;
import javax.naming.Name;
import java.util.Enumeration;

class MockName implements Name {

    private final String name;

    MockName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Object obj) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Enumeration<String> getAll() {
        return null;
    }

    @Override
    public String get(int posn) {
        return null;
    }

    @Override
    public Name getPrefix(int posn) {
        return null;
    }

    @Override
    public Name getSuffix(int posn) {
        return null;
    }

    @Override
    public boolean startsWith(Name n) {
        return false;
    }

    @Override
    public boolean endsWith(Name n) {
        return false;
    }

    @Override
    public Name addAll(Name suffix) throws InvalidNameException {
        return null;
    }

    @Override
    public Name addAll(int posn, Name n) throws InvalidNameException {
        return null;
    }

    @Override
    public Name add(String comp) throws InvalidNameException {
        return null;
    }

    @Override
    public Name add(int posn, String comp) throws InvalidNameException {
        return null;
    }

    @Override
    public Object remove(int posn) throws InvalidNameException {
        return null;
    }

    @Override
    public Object clone() {
        return null;
    }
}
