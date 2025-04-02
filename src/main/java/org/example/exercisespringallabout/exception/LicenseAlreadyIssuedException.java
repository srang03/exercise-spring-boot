package org.example.exercisespringallabout.exception;

public class LicenseAlreadyIssuedException extends AbstractBusinessException {
    public LicenseAlreadyIssuedException(String id) {
        super("이미 발급된 라이선스 id 입니다.");
    }
    @Override
    public String getErrorCode() {
        return "LICENSE_ALREADY_ISSUED";
    }
}
