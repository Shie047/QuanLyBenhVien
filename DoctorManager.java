import java.io.*;
import java.util.ArrayList;

public class DoctorManager implements IManager<Doctor> {
    private ArrayList<Doctor> listDoctors = new ArrayList<>();

    @Override
    public void add(Doctor doctor) {
        try {
            if (doctor == null) {
                System.out.println("Đối tượng bác sĩ không hợp lệ.");
                return;
            }
            listDoctors.add(doctor);
            System.out.println("Đã thêm bác sĩ thành công " + doctor.getIdDoctor());
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi khi thêm " + e.getMessage());
        }
    }

    public Doctor getDoctor(String idDoctor) {
        try {
            if (idDoctor == null || idDoctor.trim().isEmpty()) {
                return null;
            }
            for (Doctor doctor : listDoctors) {
                if (doctor.getIdDoctor().equals(idDoctor)) {
                    return doctor;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi khi trích xuất dữ liệu bác sĩ: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void update(String idDoctor, Doctor newDoctor) {
        try {
            if (newDoctor == null) {
                System.out.println("Dữ liệu cập nhật không hợp lệ.");
                return;
            }
            for (int i = 0; i < listDoctors.size(); i++) {
                if (listDoctors.get(i).getIdDoctor().equals(idDoctor)) {
                    listDoctors.set(i, newDoctor);
                    System.out.println("Đã cập nhật thông tin thành công!");
                    return;
                }
            }
            System.out.println("Không tìm thấy bác sĩ có mã " + idDoctor);
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống khi cập nhật thông tin: " + e.getMessage());
        }
    }

    @Override
    public void delete(String idDoctor) {
        try {
            if (idDoctor == null || idDoctor.trim().isEmpty()) {
                System.out.println("Mã bác sĩ không hợp lệ.");
                return;
            }
            for (int i = 0; i < listDoctors.size(); i++) {
                if (listDoctors.get(i).getIdDoctor().equals(idDoctor)) {
                    listDoctors.remove(i);
                    System.out.println("Đã xóa bác sĩ " + idDoctor);
                    return;
                }
            }
            System.out.println("Không tìm thấy bác sĩ có mã " + idDoctor + " để xóa.");
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa bác sĩ: " + e.getMessage());
        }
    }

    @Override
    public void showAll() {
        try {
            if (listDoctors.isEmpty()) {
                System.out.println("Danh sách trống");
                return;
            }
            System.out.println("DANH SÁCH BÁC SĨ");
            for (Doctor doctor : listDoctors) {
                doctor.showInfo();
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi hiển thị danh sách: " + e.getMessage());
        }
    }

    public void saveFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("doctors.txt"))) {
            for (Doctor d : listDoctors) {
                bw.write(d.getIdDoctor() + "," + d.getName() + "," + d.getAge() + "," + d.getDepartment());
                bw.newLine();
            }
            System.out.println("Đã lưu dữ liệu thành công.");
        } catch (Exception e) {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        }
    }
    public void loadFile() {
        File file = new File("doctors.txt");
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int maxId = 0;
            listDoctors.clear();
            while ((line = br.readLine()) != null) {
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
                }
            }
            Doctor.setIdCounter(maxId);
            System.out.println("Đã tải dữ liệu từ file thành công.");
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }
}