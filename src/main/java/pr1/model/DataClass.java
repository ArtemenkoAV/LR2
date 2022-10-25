package pr1.model;

import java.util.Objects;

public class DataClass {
    private String data;
    private int val;

    public DataClass(String data) {
        this.data = data;
    }

    public DataClass(String data, int val) {
        this.data = data;
        this.val = val;
    }

    public String getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataClass dataClass = (DataClass) o;
        return Objects.equals(data, dataClass.data) && val == ((DataClass) o).val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, val);
    }
}
