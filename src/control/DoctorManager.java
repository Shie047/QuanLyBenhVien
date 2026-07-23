package control;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import repository.*;
public class DoctorManager implements IManager<Doctor> {
    private ArrayList<Doctor> listDoctors = new ArrayList<>();
    private final String FILE_NAME = "doctors.txt";
    @Override
    public String add(Doctor doctor) {
        if (doctor == null) {
            return "Đối tượng bác sĩ không hợp lệ.";
        }
        if (getDoctor(doctor.getIdDoctor()) != null) {
            return "Mã bác sĩ " + doctor.getIdDoctor() + " đã tồn tại.";
        }
        listDoctors.add(doctor);
        return "Đã thêm bác sĩ thành công " + doctor.getIdDoctor();
    }
    public Doctor getDoctor(String idDoctor) {
        if (idDoctor == null || idDoctor.trim().isEmpty()) {
            return null;
        }
        for (Doctor doctor : listDoctors) {
            if (doctor.getIdDoctor().equals(idDoctor)) {
                return doctor;
            }
        }
        return null;
    }

    @Override
    public String update(String idDoctor, Doctor newDoctor) {
        if (newDoctor == null) {
            return "Dữ liệu cập nhật không hợp lệ.";
        }
        for (int i = 0; i < listDoctors.size(); i++) {
            if (listDoctors.get(i).getIdDoctor().equals(idDoctor)) {
                listDoctors.set(i, newDoctor);
                return "Đã cập nhật thông tin thành công!";
            }
        }
        return "Không tìm thấy bác sĩ có mã " + idDoctor;
    }

    @Override
    public String delete(String idDoctor) {
        if (idDoctor == null || idDoctor.trim().isEmpty()) {
            return "Mã bác sĩ không hợp lệ.";
        }
        for (int i = 0; i < listDoctors.size(); i++) {
            if (listDoctors.get(i).getIdDoctor().equals(idDoctor)) {
                listDoctors.remove(i);
                return "Đã xóa bác sĩ " + idDoctor;
            }
        }
        return "Không tìm thấy bác sĩ có mã " + idDoctor + " để xóa.";
    }

    @Override
    public void showAll() {

    }

    @Override
    public List<Doctor> getAll() {
        return listDoctors;
    }

    public String saveFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Doctor d : listDoctors) {
                bw.write(d.getIdDoctor() + "," + d.getName() + "," + d.getAge() + "," + d.getDepartment());
                bw.newLine();
            }
            return "Đã lưu dữ liệu bác sĩ thành công.";
        } catch (Exception e) {
            return "Lỗi khi lưu file bác sĩ: " + e.getMessage();
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
            int maxId = 0;
            listDoctors.clear();
            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        String id = parts[0];
                        String name = parts[1];
                        int age = Integer.parseInt(parts[2]);
                        String dept = parts[3];
                        Doctor d = new Doctor(age, name, dept);
                        d.setIdDoctor(id);
                        listDoctors.add(d);
                        try {
                            int currentIdNum = Integer.parseInt(id.replace("BS", ""));
                            if (currentIdNum > maxId) {
                                maxId = currentIdNum;
                            }
                        } catch (NumberFormatException ignored) {}
                    } else if (!line.trim().isEmpty()) {
                        result.append("Bỏ qua dòng dữ liệu bác sĩ không hợp lệ: ").append(line).append("\n");
                    }
                } catch (Exception lineEx) {
                    result.append("Bỏ qua dòng dữ liệu bác sĩ không hợp lệ: ").append(line).append("\n");
                }
            }
            Doctor.setIdCounter(maxId);
            result.append("Đã tải dữ liệu bác sĩ từ file thành công.");
            return result.toString();
        } catch (Exception e) {
            return "Lỗi khi đọc file bác sĩ: " + e.getMessage();
        }
    }
}