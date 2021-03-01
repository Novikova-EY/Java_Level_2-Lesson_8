package ru.geekbrains.java2.lesson8.server.auth;

import java.util.Set;

public class AuthenticationService {
    private static final Set<AuthEntry> entries = Set.of(
            new AuthEntry("l1", "p1", "n1"),
            new AuthEntry("l2", "p2", "n2"),
            new AuthEntry("l3", "p3", "n3")
    );

    public AuthEntry findUserByCredentials(String login, String password) {
        for (AuthEntry entry : entries) {
            if (entry.getLogin().equals(login) && entry.getPassword().equals(password)) {
                return entry;
            }
        }
        return null;
    }
}
