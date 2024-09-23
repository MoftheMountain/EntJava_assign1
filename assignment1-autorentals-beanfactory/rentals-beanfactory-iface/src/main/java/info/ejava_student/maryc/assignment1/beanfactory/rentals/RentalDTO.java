package info.ejava_student.maryc.assignment1.beanfactory.rentals;

public class RentalDTO {
    private String name;

    public RentalDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" + name + "}";
    }
}
