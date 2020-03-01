package book;

import java.util.Objects;

public class Book {

    //region Properties

    private String name;
    private String description;
    private int numberOfPages;
    private String state;
    //endregion

    //region Constructor
    public Book(String name, String description, int numberOfPages, String state) {
        this.name = name;
        this.description = description;
        this.numberOfPages = numberOfPages;
        if (state.equals(String.valueOf(State.valueOf(state)))) {
            this.state = state;
        } else {
            throw new RuntimeException("!!!!!!");
        }
    }
    public Book() {
    }

    //endregion

    //region Getter and Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    //endregion

    //region toString HashCode and equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return numberOfPages == book.numberOfPages &&
                Objects.equals(name, book.name) &&
                Objects.equals(description, book.description) &&
                Objects.equals(state, book.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, numberOfPages, state);
    }

    @Override
    public String toString() {
        return "book.Book{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", state='" + state + '\'' +
                '}';
    }

    //endregion

}