package service;

import model.TasksModel;
import repository.TasksRepository;

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
}
