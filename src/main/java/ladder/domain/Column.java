package ladder.domain;

import ladder.control.Preferences;
import ladder.exception.OutOfColumnRangeException;

import java.util.ArrayList;
import java.util.List;

public class Column {
    private static final List<Column> COLUMNS_CACHE = new ArrayList<>(Preferences.maxColumnPolicy() + 1);

    static {
        for (int i = 0; i <= Preferences.maxColumnPolicy(); i++) {
            COLUMNS_CACHE.add(i, new Column(i));
        }
    }

    private final int value;

    private Column(int value) {
        this.value = value;
    }

    public static Column of(int value) {
        if ((0 <= value) && (value <= Preferences.maxColumnPolicy())) {
            return COLUMNS_CACHE.get(value);
        }
        throw new OutOfColumnRangeException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Column otherColumn = (Column) o;
        return this.hashCode() == otherColumn.hashCode();
    }

    @Override
    public int hashCode() {
        return value;
    }

    public boolean isAdjacent(Column other) {
        return Math.abs(this.value - other.value) <= 1;
    }

    public boolean isSame(Column other) {
        return equals(other);
    }
}
