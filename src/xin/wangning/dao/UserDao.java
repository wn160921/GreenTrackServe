package xin.wangning.dao;

import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import xin.wangning.domain.User;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    private QueryRunner qr = new TxQueryRunner();

    public List<User> findAll(){
        try{
            String sql = "select * from User";
            return qr.query(sql,new BeanListHandler<>(User.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public User findById(String uuid){
        try{
            String sql = "select * from User where uuid = ?";
            return qr.query(sql,new BeanHandler<>(User.class),uuid);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * @param up 积分变化
     * @param uuid ID
     */
    public void updateRewardPoints(int up,String uuid){
        try{
            String sql = "update User set rewardPoints = RewardPoints + ? where uuid = ?";
            Object[] params = {up,uuid};
            qr.update(sql,params);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * @param user
     */
    public void add(User user){
        try{
            String sql="insert into User values(?,?,?,?,?,?)";
            Object[] params = {user.getUuid(),user.getUsername(),user.getPassword(),
                user.getRewardPoints(),user.getMailAddress(),user.getCreatedData()};
            qr.update(sql,params);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public User findByUsername(String username){
        try{
            String sql = "select * from User where username = ?";
            return qr.query(sql,new BeanHandler<>(User.class),username);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public User findByMailAddress(String mailAddress){
        try{
            String sql = "select * from User where mailAddress = ?";
            return qr.query(sql,new BeanHandler<>(User.class),mailAddress);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
