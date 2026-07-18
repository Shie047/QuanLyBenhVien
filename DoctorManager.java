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
    public void delete(String IDDoctor) {
        try {
            if (IDDoctor == null || IDDoctor.trim().isEmpty()) {
                System.out.println("Mã bác sĩ không hợp lệ.");
                return;
            }
            for (int i = 0; i < listDoctors.size(); i++) {
                if (listDoctors.get(i).getIdDoctor().equals(IDDoctor)) {
                    listDoctors.remove(i);
                    System.out.println("Đã xóa bác sĩ " + IDDoctor);
                    return;
                }
            }
            System.out.println("Không tìm thấy bác sĩ có mã " + IDDoctor + " để xóa.");
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
}
