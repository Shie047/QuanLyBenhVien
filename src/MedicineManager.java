import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineManager implements IManager<Medicine> {
    private ArrayList<Medicine> listMedicines = new ArrayList<>();
    private final String FILE_NAME = "medicines.txt";

    @Override
    public void Add(Prescription prescription) {

    }

    @Override
    public void Update(String ma, Prescription newPrescription) {

    }

    @Override
    public void Add(Patient patient) {

    }

    @Override
    public void Update(String ma, Patient newPatient) {

    }

    @Override
    public void Delete(String ma) {

    }

    @Override
    public void ShowAll() {

    }

    @Override
    public String add(Medicine medicine) {
        if (medicine == null) {
            return "Thuốc không hợp lệ.";
        }
        if (getMedicine(medicine.getIdMedicine()) != null) {
            return "Mã thuốc " + medicine.getIdMedicine() + " đã tồn tại.";
        }
        listMedicines.add(medicine);
        return "Đã thêm thuốc thành công: " + medicine.getIdMedicine();
    }

    public Medicine getMedicine(String codeMedicine) {
        if (codeMedicine == null || codeMedicine.trim().isEmpty()) {
            return null;
        }
        for (Medicine med : listMedicines) {
            if (med.getIdMedicine().equals(codeMedicine)) {
                return med;
            }
        }
        return null;
    }

    @Override
    public String update(String codeMedicine, Medicine newMedicine) {
        if (newMedicine == null) {
            return "Dữ liệu không hợp lệ.";
        }
        for (int i = 0; i < listMedicines.size(); i++) {
            if (listMedicines.get(i).getIdMedicine().equals(codeMedicine)) {
                listMedicines.set(i, newMedicine);
                return "Đã cập nhật thông tin thành công!";
            }
        }
        return "Không tìm thấy thuốc có mã " + codeMedicine;
    }

    @Override
    public String delete(String codeMedicine) {
        if (codeMedicine == null || codeMedicine.trim().isEmpty()) {
            return "Mã thuốc không hợp lệ.";
        }
        for (int i = 0; i < listMedicines.size(); i++) {
            if (listMedicines.get(i).getIdMedicine().equals(codeMedicine)) {
                listMedicines.remove(i);
                return "Đã xóa thuốc " + codeMedicine;
            }
        }
        return "Không tìm thấy thuốc có mã " + codeMedicine + " để xóa.";
    }

    @Override
    public void showAll() {

    }

    @Override
    public List<Medicine> getAll() {
        return listMedicines;
    }

    public String saveFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Medicine m : listMedicines) {
                bw.write(m.toFileLine());
                bw.newLine();
            }
            return "Đã lưu dữ liệu thuốc thành công.";
        } catch (Exception e) {
            return "Lỗi khi lưu file thuốc: " + e.getMessage();
        }
    }

    public String loadFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return null;
        }

        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            listMedicines.clear();
            while ((line = br.readLine()) != null) {
                Medicine m = Medicine.fromFileLine(line);
                if (m != null) {
                    listMedicines.add(m);
                } else if (!line.trim().isEmpty()) {
                    result.append("Bỏ qua dòng dữ liệu lỗi: ").append(line).append("\n");
                }
            }
            result.append("Đã tải dữ liệu thuốc từ file thành công.");
            return result.toString();
        } catch (Exception e) {
            return "Lỗi khi đọc file thuốc: " + e.getMessage();
        }
    }

    public Medicine findById(String part) {
        return null;
    }
}