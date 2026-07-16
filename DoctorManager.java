import java.util.ArrayList;

public class DoctorManager implements IManager<Doctor> {
    private ArrayList<Doctor> listDoctors = new ArrayList<>();

    @Override
    public void Add(Doctor doctor) {
        try {
            if (doctor == null) {
                System.out.println("Đối tượng bác sĩ không hợp lệ.");
                return;
            }
            listDoctors.add(doctor);
            System.out.println("Đã thêm bác sĩ thành công " + doctor.getMaBS());
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi khi thêm " + e.getMessage());
        }
    }

    public Doctor getDoctor(String maBS) {
        try {
            if (maBS == null || maBS.trim().isEmpty()) {
                return null;
            }
            for (Doctor doctor : listDoctors) {
                if (doctor.getMaBS().equals(maBS)) {
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
    public void Update(String maBS, Doctor newDoctor) {
        try {
            if (newDoctor == null) {
                System.out.println("Dữ liệu cập nhật không hợp lệ.");
                return;
            }
            for (int i = 0; i < listDoctors.size(); i++) {
                if (listDoctors.get(i).getMaBS().equals(maBS)) {
                    listDoctors.set(i, newDoctor);
                    System.out.println("Đã cập nhật thông tin thành công!");
                    return;
                }
            }
            System.out.println("Không tìm thấy bác sĩ có mã " + maBS);
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống khi cập nhật thông tin: " + e.getMessage());
        }
    }

    @Override
    public void Delete(String maBS) {
        try {
            if (maBS == null || maBS.trim().isEmpty()) {
                System.out.println("Mã bác sĩ không hợp lệ.");
                return;
            }
            for (int i = 0; i < listDoctors.size(); i++) {
                if (listDoctors.get(i).getMaBS().equals(maBS)) {
                    listDoctors.remove(i);
                    System.out.println("Đã xóa bác sĩ " + maBS);
                    return;
                }
            }
            System.out.println("Không tìm thấy bác sĩ có mã " + maBS + " để xóa.");
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa bác sĩ: " + e.getMessage());
        }
    }

    @Override
    public void ShowAll() {
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
}
