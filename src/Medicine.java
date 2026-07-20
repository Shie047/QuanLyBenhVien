public class Medicine {

    private final String codeMedicine;
    private String name;
    private double unitPrice;
    private int quantity;
    private static final String delimiter = ",";

    public Medicine(String codeMedicine, String name, double unitPrice, int quantity) {
        this.codeMedicine = codeMedicine;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    // Getter
    public String getCodeMedicine() {
        return codeMedicine;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Hiển thị thông tin
    public String showInfo() {
        return "Code: " + codeMedicine
                + " | Name: " + name
                + " | Price: " + unitPrice
                + " | Quantity: " + quantity;
    }

    // Lưu xuống file
    public String toFileLine() {
        return codeMedicine + "|"
                + name + "|"
                + unitPrice + "|"
                + quantity;
    }

    // Đọc từ file
    public static Medicine fromFileLine(String line) {

        String[] parts = line.split("\\|");

        if (parts.length != 4) {
            return null;
        }

        return new Medicine(
                parts[0],
                parts[1],
                Double.parseDouble(parts[2]),
                Integer.parseInt(parts[3])
        );
    }

    @Override
    public String toString() {
        return showInfo();
    }

    public String getIdMedicine() {
        return "";
    }
}