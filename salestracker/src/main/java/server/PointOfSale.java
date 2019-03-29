package server;

public class PointOfSale {

    private long id;
    private String name;

    PointOfSale(final long id) {
        this.id = id;
        this.name = "pos" + id;
    }

    long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
