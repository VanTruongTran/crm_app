package service;

import model.JobsModel;
import repository.JobsRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class JobsService {
    /**
     * phương thức lấy danh sách job từ Database
     *
     * @return (trả về danh sách chứa các job)
     */
    public List<JobsModel> getJobsList() {
        JobsRepository jobsRepository = new JobsRepository();
        return jobsRepository.getJobsList();
    }

    /**
     * phương thức thêm job vào Database
     *
     * @param name      (tham số name của job)
     * @param startDate (tham số startDate của job)
     * @param endDate   (tham số endDate của job)
     * @return (trả về true nếu thêm job thành công, nếu không thì trả về false)
     */
    public boolean addJob(String name, String startDate, String endDate) {
        //kiểm tra dữ liệu nhập hợp lệ
        if (!dataValidate(name, startDate, endDate)) {
            return false;
        }

        //chuyển đổi định dạng chuỗi ngày dd/MM/yyyy thành yyyy-MM-dd
        startDate = convertDate(startDate);
        endDate = convertDate(endDate);

        JobsRepository jobsRepository = new JobsRepository();
        int result = jobsRepository.addJob(name, startDate, endDate);
        return result > 0;
    }

    /**
     * phương thức xóa job theo id
     *
     * @param id (tham số id của job cần xóa)
     * @return (trả về true nếu xóa job thành công, nếu không thì trả về false)
     */
    public boolean deleteJobById(int id) {
        JobsRepository jobsRepository = new JobsRepository();
        int result = jobsRepository.deleteJobById(id);
        return result > 0;
    }

    /**
     * phương thức trả về job theo id
     *
     * @param id (tham số id của job cần tìm)
     * @return (trả về đối tượng job nếu tìm thấy, nếu không thì trả về null)
     */
    public JobsModel getJobById(int id) {
        JobsRepository jobsRepository = new JobsRepository();
        return jobsRepository.getJobById(id);
    }

    /**
     * phương thức cập nhật job trong Database theo id
     *
     * @param id        (tham số id của job cần cập nhật)
     * @param name      (tham số nam của job cần cập nhật)
     * @param startDate (tham số startDate của job cần cập nhật)
     * @param endDate   (tham số endDate của job cần cập nhật)
     * @return (trả về 1 nếu cập nhật thành công, nếu không thì trả về 0)
     */
    public boolean updateJobById(int id, String name, String startDate, String endDate) {
        //kiểm tra dữ liệu nhập hợp lệ
        if (!dataValidate(name, startDate, endDate)) {
            return false;
        }

        //chuyển đổi định dạng chuỗi ngày dd/MM/yyyy thành yyyy-MM-dd
        startDate = convertDate(startDate);
        endDate = convertDate(endDate);

        JobsRepository jobsRepository = new JobsRepository();
        int result = jobsRepository.updateJobById(id, name, startDate, endDate);
        return result > 0;
    }

    /**
     * phương thức kiểm tra dữ liệu nhập hợp lệ
     *
     * @param name      (tham số name do người dùng nhập)
     * @param startDate (tham số startDate do người dùng nhập)
     * @param endDate   (tham số endDate do người dùng nhập)
     * @return (trả về true nếu dữ liệu hợp lệ, nếu không thì trả về false)
     */
    private boolean dataValidate(String name, String startDate, String endDate) {
        if (name.equals("") || startDate.equals("") || endDate.equals("")) {
            return false;
        }

        if (!dateValidate(startDate) || !dateValidate(endDate)) {
            return false;
        }

        if (!dateValidate(startDate, endDate)) {
            return false;
        }
        return true;
    }

    /**
     * phương thức kiểm tra chuỗi ngày có đúng định dạnh hay không
     *
     * @param date (chuỗi ngày cần kiểm tra định dạng)
     * @return (trả về true nếu chuỗi ngày hợp lệ, nếu không thì trả về false)
     */
    private boolean dateValidate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        try {
            sdf.parse(date);
            return true;
        } catch (ParseException ignored) {

        }
        return false;
    }

    /**
     * phương thức kiểm tra endDate có phải ngày ở sau startDate hay không
     *
     * @param startDate (tham số startDate do người dùng nhập)
     * @param endDate   (tham số endDate do người dùn nhập)
     * @return (trả về true nếu endDate là ngày ở sau startDate, nếu không thì trả về false)
     */
    private boolean dateValidate(String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        boolean valid = false;

        try {
            if (sdf.parse(endDate).compareTo(sdf.parse(startDate)) >= 0) {
                valid = true;
            }
        } catch (ParseException ignored) {

        }
        return valid;
    }

    /**
     * phương thức chuyển đổi định dạng chuỗi ngày dd/MM/yyyy thành yyyy-MM-dd
     *
     * @param date (tham số chuỗi ngày cần đổi định dạng)
     * @return (trả về chuỗi ngày sau khi đổi định dạng)
     */
    private String convertDate(String date) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        sdf1.setLenient(false);
        sdf2.setLenient(false);

        try {
            date = sdf2.format(sdf1.parse(date));
        } catch (ParseException ignored) {

        }
        return date;
    }
}
