import com.dao.IUserDao;
import com.domain.User;
import com.mybatis.io.Resource;
import com.mybatis.sqlsession.SqlSession;
import com.mybatis.sqlsession.SqlSessionFactory;
import com.mybatis.sqlsession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    public static void main(String[] args)throws Exception {
        //1.读取配置文件
        InputStream in= Resource.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //4.使用sqlsession创建Dao接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        //5.使用代理对象执行方法
        User user = userDao.findOne();
//        for(User user :users){
//            System.out.println(user);
//        }
        System.out.println(user);
        session.close();
        in.close();
    }
}
