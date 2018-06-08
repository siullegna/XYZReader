package com.hap.xyzreader.detail.model;

/**
 * Created by luis on 6/7/18.
 */

public enum BodyType {
    UNKNOWN(0),
    PHOTO_HEADER(1),
    MESSAGE(2);

    private final int type;

    BodyType(final int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static BodyType fromType(final int type) {
        BodyType bodyType = UNKNOWN;

        for (final BodyType currentType : values()) {
            if (currentType.type == type) {
                bodyType = currentType;
                break;
            }
        }

        return bodyType;
    }
}
