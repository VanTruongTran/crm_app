package service;

import model.RolesModel;
import repository.RolesRepository;

import java.util.List;

public class RolesService {
    /**
     * phương thức thêm role vào Database
     *
     * @param name        (tham số name do người dùng nhập)
     * @param description (tham số description do người dùng nhập)
     * @return (nếu thêm role thành công thì trả về true, nếu không thì trả về false)
     */
    public boolean addRole(String name, String description) {
        if (!dataValidate(name, description)) {
            return false;
        }

        if (description.equals("")) {
            description = null;
        }

        RolesRepository rolesRepository = new RolesRepository();
        int result = rolesRepository.addRole(name, description);
        return result > 0;
    }

    /**
     * phương thức lấy danh sách role từ Database
     *
     * @return (trả về danh sách đối tượng RolesModel)
     */
    public List<RolesModel> getRolesList() {
        RolesRepository rolesRepository = new RolesRepository();
        return rolesRepository.getRolesList();
    }

    /**
     * phương thức xóa role trong Database dựa trên id
     *
     * @param id (tham số id của role cần xóa)
     * @return (trả về true nếu xóa role thành công, nếu không thì trả về false)
     */
    public boolean deleteRoleById(int id) {
        RolesRepository rolesRepository = new RolesRepository();
        int result = rolesRepository.deleteRoleById(id);
        return result > 0;
    }

    /**
     * phương thức trả về role dựa trên id
     *
     * @param id (tham số id do người dùng nhập)
     * @return (trả về đối tượng role nếu tìm thấy, nếu không thì trả về null)
     */
    public RolesModel getRoleById(int id) {
        RolesRepository rolesRepository = new RolesRepository();
        return rolesRepository.getRoleById(id);
    }

    /**
     * phương thức cập nhật role trong Database
     *
     * @param id          (tham số id của role cần update)
     * @param name        (tham số name của role cần update)
     * @param description (tham số description của role cần update)
     * @return (trả về 1 nếu role được update thành công, nếu không thì trả về 0)
     */
    public boolean updateRole(int id, String name, String description) {
        if (!dataValidate(name, description)) {
            return false;
        }

        if (description.equals("")) {
            description = null;
        }

        RolesRepository rolesRepository = new RolesRepository();
        int result = rolesRepository.updateRole(id, name, description);
        return result > 0;
    }

    /**
     * phương thức kiểm tra dữ liệu hợp lệ
     *
     * @param name        (tham số name do người dùng nhập)
     * @param description (tham số description do người dùng nhập)
     * @return (trả về true nếu dữ liệu nhập hợp lệ, nếu không thì trả về false)
     */
    private boolean dataValidate(String name, String description) {
        return !name.equals("");
    }
}
