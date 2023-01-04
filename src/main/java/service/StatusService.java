package service;

import model.StatusModel;
import repository.StatusRepository;

import java.util.List;

public class StatusService {
    /**
     * phương thức lấy danh sách status từ Database
     *
     * @return (trả về danh sách status)
     */
    public List<StatusModel> getStatusList() {
        StatusRepository statusRepository = new StatusRepository();
        return statusRepository.getStatusList();
    }

    /**
     * phương thức trả về status dựa trên id
     *
     * @param id (tham số id của status)
     * @return (trả về đối tượng StatusModel nếu tìm thấy, nếu không thì trả về null)
     */
    public StatusModel getStatusById(int id) {
        StatusRepository statusRepository = new StatusRepository();
        return statusRepository.getStatusById(id);
    }
}
