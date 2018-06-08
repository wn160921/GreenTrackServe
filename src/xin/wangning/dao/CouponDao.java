package xin.wangning.dao;

import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import xin.wangning.domain.Coupon;

import java.sql.SQLException;
import java.util.List;

public class CouponDao {
    private QueryRunner qr = new TxQueryRunner();

    public List<Coupon> findAllProvider(){
        try{
            String sql = "select * from coupon where isProvider = 1";
            return qr.query(sql,new BeanListHandler<>(Coupon.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Coupon> findAllConsumer(){
        try{
            String sql = "select * from coupon where isProvider = 0";
            return qr.query(sql,new BeanListHandler<>(Coupon.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //我觉得对于更新是否成功在这里应该控制一下，userdao并没有在这层处理
    public void update(String uuid,int number){
        try{
            String sql = "update coupon set numberRest = numberRest + ? where uuid = ?";
            Object[] params = {uuid,number};
            int result = qr.update(sql,params);
            if (result == 0){
                throw new RuntimeException("未更新");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


}
