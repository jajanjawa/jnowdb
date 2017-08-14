package com.github.jajanjawa.jnowdb;

import static com.github.jajanjawa.jnowdb.NowDBProperty.ORWHERE_FIELD;
import static com.github.jajanjawa.jnowdb.NowDBProperty.ORWHERE_VALUE;
import static com.github.jajanjawa.jnowdb.NowDBProperty.WHERE_FIELD;
import static com.github.jajanjawa.jnowdb.NowDBProperty.WHERE_VALUE;
import static com.github.jajanjawa.jnowdb.NowDBProperty.WIN_FIELD;
import static com.github.jajanjawa.jnowdb.NowDBProperty.WIN_VALUE;
import static com.github.jajanjawa.jnowdb.NowDBProperty.WLIKE_FIELD;
import static com.github.jajanjawa.jnowdb.NowDBProperty.WLIKE_VALUE;
import static com.github.jajanjawa.jnowdb.NowDBProperty.WNOTIN_FIELD;
import static com.github.jajanjawa.jnowdb.NowDBProperty.WNOTIN_VALUE;

public enum Operation {

    COUNT_ALL("count_all/"),
    COUNT_WHERE("count_where/"),
    INSERT("insert/"),
    REMOVE_ALL("remove_all/"),
    REMOVE_ID("remove_id/"),
    REMOVE_WHERE("remove_where/"),
    REMOVE_OR_WHERE("remove_or_where/"),
    REMOVE_WHERE_LIKE("remove_where_like/"),
    REMOVE_WHERE_IN("remove_where_in/"),
    REMOVE_WHERE_NOT_IN("remove_where_not_in"),
    SELECT_ALL("select_all/"),
    SELECT_ID("select_id/"),
    SELECT_WHERE("select_where/"),
    SELECT_OR_WHERE("select_or_where/"),
    SELECT_WHERE_LIKE("select_where_like/"),
    SELECT_WHERE_IN("select_where_in/"),
    SELECT_WHERE_NOT_IN("select_where_not_in/"),
    UPDATE_ALL("update_all/"),
    UPDATE_ID("update_id/"),
    /**
     * Operasi untuk memanggil {@link NowDBQuery#and()}
     */
    UPDATE_WHERE("update_where/"),
    UPDATE_OR_WHERE("update_or_where/"),
    UPDATE_WHERE_LIKE("update_where_like/"),
    UPDATE_WHERE_IN("update_where_in/"),
    UPDATE_WHERE_NOT_IN("update_where_not_in/"),;

    private final String operation;

    private Operation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return operation;
    }

    /**
     * index 0 = nama field, index 1 = isi field.
     *
     * @return
     */
    public String[] keys() {
        String[] keys = new String[2];
        switch (this) {
            case SELECT_WHERE:
            case UPDATE_WHERE:
            case REMOVE_WHERE:
                keys[0] = WHERE_FIELD;
                keys[1] = WHERE_VALUE;
                break;
            case SELECT_OR_WHERE:
            case UPDATE_OR_WHERE:
            case REMOVE_OR_WHERE:
                keys[0] = ORWHERE_FIELD;
                keys[1] = ORWHERE_VALUE;
                break;
            case SELECT_WHERE_LIKE:
            case UPDATE_WHERE_LIKE:
            case REMOVE_WHERE_LIKE:
                keys[0] = WLIKE_FIELD;
                keys[1] = WLIKE_VALUE;
                break;
            case SELECT_WHERE_IN:
            case UPDATE_WHERE_IN:
            case REMOVE_WHERE_IN:
                keys[0] = WIN_FIELD;
                keys[1] = WIN_VALUE;
                break;
            case SELECT_WHERE_NOT_IN:
            case UPDATE_WHERE_NOT_IN:
            case REMOVE_WHERE_NOT_IN:
                keys[0] = WNOTIN_FIELD;
                keys[1] = WNOTIN_VALUE;
                break;
            default:
                break;
        }
        return keys;
    }
}
