public class Medicine {

    private final String idMedicine;
    private String name;
    private double unitPrice;
    private int quantity;

    public Medicine(String idMedicine, String name, double unitPrice, int quantity) {
        if (idMedicine == null || idMedicine.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã thuốc không được để trống.");
        }
        if (idMedicine.contains(",") || (name != null && name.contains(","))) {
            throw new IllegalArgumentException("Mã/Tên thuốc không được chứa dấu phẩy");
        }
        this.idMedicine = idMedicine;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Medicine(Medicine other) {
        this.idMedicine = other.idMedicine;
        this.name = other.name;
        this.unitPrice = other.unitPrice;
        this.quantity = other.quantity;
    }

    public String getIdMedicine() {
        return idMedicine;
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

    public void setName(String name) {
        if (name != null && name.contains(DELIMITER)) {
            throw new IllegalArgumentException("Tên thuốc không được chứa ký tự đặc biệt");
        }
        this.name = name;
    }

    public void setUnitPrice(double unitPrice) {
        if (unitPrice <= 0) {
            throw new IllegalArgumentException("Giá thuốc phải lớn hơn 0.");
        }
        this.unitPrice = unitPrice;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Số lượng phải lớn hơn 0.");
        }
        this.quantity = quantity;
    }

    public String showInfo() {
        return "ID: " + idMedicine
                + " | Name: " + name
                + " | Price: " + unitPrice
                + " | Quantity: " + quantity;
    }

    public String toFileLine() {
        return idMedicine + DELIMITER
                + name + DELIMITER
                + unitPrice + DELIMITER
                + quantity;
    }

    public static Medicine fromFileLine(String line) {
        try {
            String[] parts = line.split(DELIMITER);
            if (parts.length != 4) {
                return null;
            }
            return new Medicine(
                    parts[0],
                    parts[1],
                    Double.parseDouble(parts[2]),
                    Integer.parseInt(parts[3])
            );
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return showInfo();
    }
}
