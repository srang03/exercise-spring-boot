package org.example.exercisespringallabout.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class LicenseTest {
    @Test
    void 활성화되어있으면_true를_반환한다(){
        // given
        License license = new License("1", 1, "licenseKey", null, true);

        // when
        boolean result = license.isActive();

        // then
        assertThat(result).isTrue();
    }
    @Test
    void 활성화되어있지않으면_false를_반환한다(){
        // given
        License license = new License("1", 1, "licenseKey", null, false);

        // when
        boolean result = license.isActive();

        // then
        assertThat(result).isFalse();
    }
}