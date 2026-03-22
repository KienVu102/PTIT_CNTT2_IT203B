package Ex_5;

public class Doctor {
    private String id;
    private String name;
    private String spec;

    public Doctor(String id, String name, String spec) {
        this.id = id;
        this.name = name;
        this.spec = spec;
    }
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getSpec() { return spec; }
}