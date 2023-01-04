package service;

import model.TasksModel;
import repository.TasksRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class TasksService {
    /**
     * phương thức lấy danh sách task từ Database
     *
     * @return (trả về danh sách task từ Database)
     */
    public List<TasksModel> getTasksList() {
        TasksRepository tasksRepository = new TasksRepository();
        return tasksRepository.getTasksList();
    }

    /**
     * phương thức thêm task vào Database
     *
     * @param name      (tham số name của task muốn thêm)
     * @param startDate (tham số startDate của task muốn thêm)
     * @param endDate   (tham số endDate của task muốn thêm)
     * @param userId    (tham số userId của task muốn thêm)
     * @param jobId     (tham số jobId của task muốn thêm)
     * @param statusId  (tham số statusId của task muốn thêm)
     * @return (trả về true nếu thêm task thành công, nếu không thì trả về false)
     */
    public boolean addTask(String name, String startDate, String endDate, int userId, int jobId, int statusId) {
        //kiểm tra dữ liệu nhập hợp lệ
        if (!dataValidate(name, startDate, endDate, userId, jobId, statusId)) {
            return false;
        }

        //chuyển đổi định dạng chuỗi ngày dd/MM/yyyy thành yyyy-MM-dd
        startDate = convertDate(startDate);
        endDate = convertDate(endDate);

        TasksRepository tasksRepository = new TasksRepository();
        int result = tasksRepository.addTask(name, startDate, endDate, userId, jobId, statusId);
        return result > 0;
    }

    /**
     * phương thức xóa task dựa trên id
     *
     * @param id (tham số id của task cần xóa)
     * @return (trả về true nếu xóa thành công, nếu không thì trả về false)
     */
    public boolean deleteTask(int id) {
        TasksRepository tasksRepository = new TasksRepository();
        int result = tasksRepository.deleteTask(id);
        return result > 0;
    }

    /**
     * phương thức kiểm tra dữ liệu nhập hợp lệ
     *
     * @param name      (tham số name do người dùng nhập)
     * @param startDate (tham số startDate do người dùng nhập)
     * @param endDate   (tham số endDate do người dùng nhập)
     * @param userId    (tham số userId do người dùng nhập)
     * @param jobId     (tham số jobId do người dùng nhập)
     * @param statusId  (tham số statusId do người dùng nhập)
     * @return (trả về true nếu dữ liệu nhập hợp lệ, nếu không thì trả về false)
     */
    private boolean dataValidate(String name, String startDate, String endDate, int userId, int jobId, int statusId) {
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
     * phương thức kiểm tra chuỗi ngày tháng có đúng định dạng hay không
     *
     * @param date (tham số chuỗi ngày tháng cần kiểm tra định dạng)
     * @return (trả về true nếu chuỗi ngày tháng đúng định dạng, nếu không thì trả về false)
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
     * @param endDate   (tham số endDate do người dùng nhập)
     * @return (trả về true nếu endDate là ngày ở sau startDate, nếu không thì trả về false)
     */
    private boolean dateValidate(String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        boolean valid = false;

        try {
            if ((sdf.parse(endDate).compareTo(sdf.parse(startDate)) >= 0)) {
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
