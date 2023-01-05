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
     * phương thức lấy danh sách task của user từ Database
     *
     * @param userId (tham số id của user cần lấy danh sách task)
     * @return (trả về danh sách task của user)
     */
    public List<TasksModel> getTasksListWhereUserId(int userId) {
        TasksRepository tasksRepository = new TasksRepository();
        return tasksRepository.getTasksListWhereUserId(userId);
    }

    /**
     * phương thức lấy danh sách task của project từ Database
     *
     * @param jobId (tham số id của project cần lấy danh sách task)
     * @return (trả về danh sách task của project)
     */
    public List<TasksModel> getTasksListWhereJobId(int jobId) {
        TasksRepository tasksRepository = new TasksRepository();
        return tasksRepository.getTasksListWhereJobId(jobId);
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
     * phương thức cập nhật task trong Database
     *
     * @param id        (tham số id của task cần cập nhật)
     * @param name      (tham số name của task cần cập nhật)
     * @param startDate (tham số startDate của task cần cập nhật)
     * @param endDate   (tham số endDate của task cần cập nhật)
     * @param userId    (tham số userId của task cần cập nhật)
     * @param jobId     (tham số jobId của task cần cập nhật)
     * @param statusId  (tham số statusId của task cần cập nhật)
     * @return (trả về true nếu cập nhật task thành công, nếu không thì trả về false)
     */
    public boolean updateTask(int id, String name, String startDate, String endDate, int userId, int jobId, int statusId) {
        //kiểm tra dữ liệu nhập hợp lệ
        if (!dataValidate(name, startDate, endDate, userId, jobId, statusId)) {
            return false;
        }

        //chuyển đổi định dạng chuỗi ngày dd/MM/yyyy thành yyyy-MM-dd
        startDate = convertDate(startDate);
        endDate = convertDate(endDate);

        TasksRepository tasksRepository = new TasksRepository();
        int result = tasksRepository.updateTask(id, name, startDate, endDate, userId, jobId, statusId);
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
     * phương thức trả về task dựa trên id
     *
     * @param id (tham số id của task cần tìm)
     * @return (trả về task nếu tìm thấy, nếu không thì trả về null)
     */
    public TasksModel getTaskById(int id) {
        TasksRepository tasksRepository = new TasksRepository();
        return tasksRepository.getTaskById(id);
    }

    /**
     * phương thức đếm tổng số task trong Database
     *
     * @return (trả về tổng số task trong Database)
     */
    public int countAllTask() {
        TasksRepository tasksRepository = new TasksRepository();
        List<TasksModel> tasksModelList = tasksRepository.getTasksList();

        return tasksModelList.size();
    }

    /**
     * phương thức đếm tổng số task trong danh sách
     *
     * @param tasksModelList (tham số danh sách chứa task)
     * @return (trả về tổng số task trong danh sách)
     */
    public int countAllTask(List<TasksModel> tasksModelList) {
        return tasksModelList.size();
    }

    /**
     * phương thức đếm tổng số task dựa trên mã trạng thái trong Database
     *
     * @return (trả về tổng số task dựa trên mã trạng thái trong Database)
     */
    public int countAllTaskWhereStatusId(int statusId) {
        TasksRepository tasksRepository = new TasksRepository();
        List<TasksModel> tasksModelList = tasksRepository.getTasksList();
        int count = 0;

        for (TasksModel task : tasksModelList) {
            if (task.getStatusModel().getId() == statusId) {
                count++;
            }
        }
        return count;
    }

    /**
     * phương thức đếm tổng số task dựa trên mã trạng thái trong danh sách
     *
     * @param tasksModelList (tham số danh sách chứa task)
     * @return (trả về tổng số task dựa trên mã trạng thái trong danh sách)
     */
    public int countAllTaskWhereStatusId(List<TasksModel> tasksModelList, int statusId) {
        int count = 0;

        for (TasksModel task : tasksModelList) {
            if (task.getStatusModel().getId() == statusId) {
                count++;
            }
        }
        return count;
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
