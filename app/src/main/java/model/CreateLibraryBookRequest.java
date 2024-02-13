package model;

public class CreateLibraryBookRequest {

    private int stock;

    public CreateLibraryBookRequest(int stock) {
        this.stock = stock;
    }

    public CreateLibraryBookRequest() {
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
