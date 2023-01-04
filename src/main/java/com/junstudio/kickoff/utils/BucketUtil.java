package com.junstudio.kickoff.utils;

import io.github.bucket4j.Bandwidth;

import java.time.Duration;

public enum BucketUtil {
    AMATEUR {
        public Bandwidth limit() {
            return Bandwidth.simple(5, Duration.ofDays(1));
        }
    },

    SEMIPRO {
        public Bandwidth limit() {
            return Bandwidth.simple(10, Duration.ofDays(1));
        }
    },

    PRO {
        public Bandwidth limit() {
            return Bandwidth.simple(15, Duration.ofDays(1));
        }
    },

    WORLDCLASS {
        public Bandwidth limit() {
            return Bandwidth.simple(20, Duration.ofDays(1));
        }
    };

    public abstract Bandwidth limit();

    public static BucketUtil bucketLimit(String grade) {
        if (grade == null || grade.isEmpty()) {
            return AMATEUR;
        }

        if (grade.equals("세미프로")) {
            return SEMIPRO;
        }

        if (grade.equals("프로")) {
            return PRO;
        }

        if (grade.equals("월드클래스")) {
            return WORLDCLASS;
        }

        return AMATEUR;
    }
}
