package DTO;

public class CreateLibraryBookRequestDTO {

    private int stock;

    public CreateLibraryBookRequestDTO(int stock) {
        this.stock = stock;
    }

    public CreateLibraryBookRequestDTO() {
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
