package com.junstudio.kickoff.exceptions;

public class BucketNotEnough extends RuntimeException {
    public BucketNotEnough() {
        super("이용 횟수를 초과하였습니다.");
    }
}
