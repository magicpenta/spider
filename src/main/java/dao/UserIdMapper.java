package dao;

import entity.UserId;

import java.util.List;

/**
 * 用户 id mapper 接口
 *
 * @author panda
 * @date 2018/2/22
 */
public interface UserIdMapper {

    int selectMaxId();

    List<UserId> selectList(int id);

}
