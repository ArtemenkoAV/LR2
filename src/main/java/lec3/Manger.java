package lec3;

public class Manger extends Worker implements Departable {
    private String department;

    @Override
    public String getDepartment() {
        return department;
    }
}
