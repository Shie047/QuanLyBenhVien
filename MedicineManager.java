import java.io.*;
import java.util.ArrayList;

public class MedicineManager implements IManager<Medicine> {
    private ArrayList<Medicine> listMedicines = new ArrayList<>();
    private final String FILE_NAME = "medicines.txt";

    @Override
    public void add(Medicine medicine) {
        try {
            if (medicine == null) {
                System.out.println("Thuốc không hợp lệ.");
                return;
            }
            if (getMedicine(medicine.getCodeMedicine()) != null) {
                System.out.println("Mã thuốc " + medicine.getCodeMedicine() + " đã tồn tại");
                return;
            }
            listMedicines.add(medicine);
            System.out.println("Đã thêm thuốc thành công: " + medicine.getCodeMedicine());
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi khi thêm thuốc: " + e.getMessage());
        }
    }

    public Medicine getMedicine(String codeMedicine) {
        try {
            if (codeMedicine == null || codeMedicine.trim().isEmpty()) {
                return null;
            }
            for (Medicine med : listMedicines) {
                if (med.getCodeMedicine().equals(codeMedicine)) {
                    return med;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi dữ liệu: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void update(String codeMedicine, Medicine newMedicine) {
        try {
            if (newMedicine == null) {
                System.out.println("Dữ liệu không hợp lệ.");
                return;
            }
            for (int i = 0; i < listMedicines.size(); i++) {
                if (listMedicines.get(i).getCodeMedicine().equals(codeMedicine)) {
                    listMedicines.set(i, newMedicine);
                    System.out.println("Đã cập nhật thông tin thành công!");
                    return;
                }
            }
            System.out.println("Không tìm thấy thuốc có mã " + codeMedicine);
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật: " + e.getMessage());
        }
    }

    @Override
    public void delete(String codeMedicine) {
        try {
            if (codeMedicine == null || codeMedicine.trim().isEmpty()) {
                System.out.println("Mã thuốc không hợp lệ.");
                return;
            }
            for (int i = 0; i < listMedicines.size(); i++) {
                if (listMedicines.get(i).getCodeMedicine().equals(codeMedicine)) {
                    listMedicines.remove(i);
                    System.out.println("Đã xóa thuốc " + codeMedicine);
                    return;
                }
            }
            System.out.println("Không tìm thấy thuốc có mã " + codeMedicine + " để xóa.");
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa: " + e.getMessage());
        }
    }

    @Override
    public void showAll() {
        try {
            if (listMedicines.isEmpty()) {
                System.out.println("Danh sách trống.");
                return;
            }
            System.out.println("DANH SÁCH THUỐC");
            for (Medicine med : listMedicines) {
                System.out.println(med.showInfo());
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi hiển thị danh sách: " + e.getMessage());
        }
    }

    public void saveFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Medicine m : listMedicines) {
                bw.write(m.toFileLine());
                bw.newLine();
            }
            System.out.println("Đã lưu dữ liệu thuốc thành công.");
        } catch (Exception e) {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        }
    }

    public void loadFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            listMedicines.clear();
            while ((line = br.readLine()) != null) {

                Medicine m = Medicine.fromFileLine(line);
                if (m != null) {
                    listMedicines.add(m);
                }
            }
            System.out.println("Đã tải dữ liệu thuốc từ file thành công.");
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }
}
