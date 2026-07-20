package repository;
public class PrescriptionDetail {

    private Medicine medicine;
    private int quantity;
    private String dosage;

    public PrescriptionDetail(Medicine medicine, int quantity, String dosage) {
        this.medicine = medicine;
        this.quantity = quantity;
        this.dosage = dosage;
    }

    // Getter

    public Medicine getMedicine() {
        return medicine;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDosage() {
        return dosage;
    }

    // Setter

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    // Hiển thị

    public String showInfo() {

        return "Medicine: " + medicine.getName()
                + " | Quantity: " + quantity
                + " | Dosage: " + dosage;
    }

    // Lưu file

    public String toFileLine() {

        return medicine.getCodeMedicine() + "|"
                + quantity + "|"
                + dosage;
    }

    // Đọc file

    public static PrescriptionDetail fromFileLine(String line,
                                                  MedicineManager medicineManager) {

        String[] parts = line.split("\\|");

        if (parts.length != 3) {
            return null;
        }

        Medicine medicine = medicineManager.findById(parts[0]);

        if (medicine == null) {
            return null;
        }

        return new PrescriptionDetail(
                medicine,
                Integer.parseInt(parts[1]),
                parts[2]
        );
    }

    @Override
    public String toString() {
        return showInfo();
    }

}