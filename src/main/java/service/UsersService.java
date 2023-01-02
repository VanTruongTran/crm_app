package service;

import model.UsersModel;
import repository.UsersRepository;

import java.util.List;
import java.util.regex.Pattern;

public class UsersService {
    /**
     * phương thức tìm user theo email và password
     *
     * @param email    (tham số email do người dùng nhập)
     * @param password (tham số password do người dùng nhập)
     * @return (trả về đối tượng UsersModel nếu tìm thấy, nếu không thì trả về null)
     */
    public UsersModel getUserByEmailAndPassword(String email, String password) {
        UsersRepository usersRepository = new UsersRepository();
        return usersRepository.getUserByEmailAndPassword(email, password);
    }

    /**
     * phương thức thêm user vào Database
     *
     * @param email    (tham số email của user muốn thêm)
     * @param password (tham số password của user muốn thêm)
     * @param fullname (tham số fullname của user muốn thêm)
     * @param avatar   (tham số avatar của user muốn thêm)
     * @param roleId   (tham số roleId của user muốn thêm)
     * @return (trả về true nếu thêm thành công, nếu không thì trả về false)
     */
    public boolean addUser(String email, String password, String fullname, String avatar, int roleId) {
        if (!dataValidate(email, password, fullname, avatar, roleId)) {
            return false;
        }

        UsersRepository usersRepository = new UsersRepository();
        int result = usersRepository.addUser(email, password, fullname, avatar, roleId);
        return result > 0;
    }

    /**
     * phương thức lấy danh sách user từ Database
     *
     * @return (trả về danh sách user từ Database)
     */
    public List<UsersModel> getUsersList() {
        UsersRepository usersRepository = new UsersRepository();
        return usersRepository.getUsersList();
    }

    /**
     * phương thức kiểm tra dữ liệu nhập hợp lệ
     *
     * @param email    (tham số email của user muốn thêm)
     * @param password (tham số password của user muốn thêm)
     * @param fullname (tham số fullname của user muốn thêm)
     * @param avatar   (tham số avatar của user muốn thêm)
     * @param roleId   (tham số roleId của user muốn thêm)
     * @return (trả về true nếu dữ liệu nhập hợp lệ, nếu không thì trả về false)
     */
    private boolean dataValidate(String email, String password, String fullname, String avatar, int roleId) {
        if (email.equals("") || password.equals("") || fullname.equals("")) {
            return false;
        }

        if (!emailValidate(email)) {
            return false;
        }
        return true;
    }

    /**
     * phương thức kiểm tra email hợp lệ
     *
     * @param email (tham số email do người dùng nhập)
     * @return (trả về true nếu email hợp lệ, nếu không thì trả về false)
     */
    private boolean emailValidate(String email) {
        Pattern emailPattern = Pattern.compile("^[a-zA-Z]([a-zA-Z0-9]+)?@[a-zA-Z]+(\\.[a-zA-Z]+)+$");
        return emailPattern.matcher(email).find();
    }
}
