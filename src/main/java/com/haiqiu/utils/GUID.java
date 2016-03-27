package com.haiqiu.utils;

import java.io.Serializable;
import java.util.UUID;

public final class GUID implements Serializable, Comparable<GUID> {
    private static final long serialVersionUID = 6010836986783019344L;
    private static final GUID NONE = new GUID("");
    private String value;

    private GUID(String id) {
        this.value = id;
    }

    public boolean equals(Object obj) {
        return obj == null?false:(obj == this?true:(obj.getClass() == this.getClass()?this.value.equals(((GUID)obj).value):false));
    }

    public String getValue() {
        return this.value;
    }

    public boolean isNone() {
        return this.value.length() == 0;
    }

    public int compareTo(GUID obj) {
        return this.value.compareTo(obj.value);
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toString() {
        return this.value;
    }

    public UUID toUUID() {
        if(this.value.length() == 32) {
            StringBuilder buff = new StringBuilder(36);
            buff.append(this.value.substring(0, 8)).append("-").append(this.value.substring(8, 12)).append("-").append(this.value.substring(12, 16)).append("-").append(this.value.substring(16, 20)).append("-").append(this.value.substring(20));
            return UUID.fromString(buff.toString());
        } else {
            throw new IllegalArgumentException("Invalid UUID format: " + this.value);
        }
    }

    private static String shrink(String uuid) {
        StringBuilder buff = new StringBuilder(32);
        buff.append(uuid.substring(0, 8)).append(uuid.substring(9, 13)).append(uuid.substring(14, 18)).append(uuid.substring(19, 23)).append(uuid.substring(24));
        return buff.toString();
    }

    public static GUID next() {
        String uuid = UUID.randomUUID().toString();
        return new GUID(shrink(uuid).toUpperCase());
    }

    /** @deprecated */
    @Deprecated
    public static String nextID() {
        return next().toString();
    }

    public static GUID none() {
        return NONE;
    }

    public static GUID of(String id) {
        assert id != null;

        return id.length() == 36?new GUID(shrink(id)):new GUID(id);
    }

    public String toJSONString() {
        return "\"" + this.value + "\"";
    }

    public String toJSONString(boolean compact) {
        return "\"" + this.value + "\"";
    }
}
