package org.example.exercisespringallabout.domain.user;

public class UserContext {
    private static final ThreadLocal<Role> user = new ThreadLocal<>();

    public static void setRole(Role role) {
        user.set(role);
    }

    public static Role getRole() {
        return user.get();
    }

    public static void clear() {
        user.remove();
    }
}
