package top.mrxiaom.sweet.biomeaction.utils;

import java.util.Objects;

public class Key {
    private String namespace;
    private String key;

    public Key(String namespace, String key) {
        this.namespace = assertValidNamespace(namespace, key);
        this.key = assertValidPath(namespace, key);
    }

    public String getNamespace() {
        return namespace;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return namespace + ":" + key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key1 = (Key) o;
        return Objects.equals(namespace, key1.namespace) && Objects.equals(key, key1.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespace, key);
    }

    public static boolean isValidPath(String path) {
        for (int i = 0; i < path.length(); i++) {
            if (!validPathChar(path.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static String assertValidNamespace(String namespace, String path) {
        if (!isValidNamespace(namespace)) {
            throw new IllegalArgumentException("Non [a-z0-9_.-] character in namespace of location: " + namespace + ":" + path);
        } else {
            return namespace;
        }
    }

    private static String assertValidPath(String namespace, String path) {
        if (!isValidPath(path)) {
            throw new IllegalArgumentException("Non [a-z0-9/._-] character in path of location: " + namespace + ":" + path);
        } else {
            return path;
        }
    }

    public static boolean isValidNamespace(String namespace) {
        for (int i = 0; i < namespace.length(); i++) {
            if (!validNamespaceChar(namespace.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean validPathChar(char character) {
        return character == '_'
                || character == '-'
                || character >= 'a' && character <= 'z'
                || character >= '0' && character <= '9'
                || character == '/'
                || character == '.';
    }

    private static boolean validNamespaceChar(char character) {
        return character == '_' || character == '-' || character >= 'a' && character <= 'z' || character >= '0' && character <= '9' || character == '.';
    }
}
