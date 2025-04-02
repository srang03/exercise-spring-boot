package org.example.exercisespringallabout.vo;

public class Email {
    private final String value;
    public Email(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("이메일은 null 이거나 공백일 수 없습니다.");
        }
        if (!value.matches("^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,3}$")) {
            throw new IllegalArgumentException("이메일 형식이 잘못되었습니다.");
        }
        this.value = value;
    }
}
