package customer;
import org.mindrot.jbcrypt.BCrypt;
import java.util.Objects;

public class Customer {
    //region Properties
    private String name;
    private String surname;
    private String password;
//endregion

    //region Constructor
    public Customer(String name, String surname, String password) {
    this.name = name;
    this.surname = surname;
    this.password = password;
}

    public Customer() {
    }

    //endregion

    //region Getter and Setter
    public String getName() {
    return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBCryptPassword() {
        return BCrypt.hashpw(getPassword(), BCrypt.gensalt());
    }


    //endregion

    //region toString HashCode and equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) &&
                Objects.equals(surname, customer.surname) &&
                Objects.equals(password, customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, password);
    }

    @Override
    public String toString() {
        return "customer.Customer{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    //endregion
}