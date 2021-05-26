import com.dao.IUserDao;
import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {
//    private InputStream in;
//    private SqlSession session;
//    private IUserDao userDao;
//    @Before
//    public void init() throws IOException {
//        in= Resources.getResourceAsStream("SqlMapConfig.xml");
//        //2.创建SqlSessionFactory工厂
//        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//        SqlSessionFactory factory = builder.build(in);
//        //3.使用工厂生产SqlSession对象
//        session = factory.openSession();
//        //4.使用sqlsession创建Dao接口的代理对象
//        userDao = session.getMapper(IUserDao.class);
//    }
//    @After
//    public void close() throws IOException {
//        session.commit();
//        in.close();
//        session.close();
//    }
//    @Test
//    public void testSelectAll(){
//        List<User> users = userDao.findAll();
//        for(User user :users){
//            System.out.println(user);
//        }
//    }
//    @Test
//    public void testAdd(){
//        User user=new User();
//        user.setUsername("于康乐");
//        user.setAddress("西安市");
//        user.setSex("n");
//        user.setBirthday(new Date());
//        userDao.addUser(user);
//    }
//    @Test
//    public void testDelete(){
//        userDao.deleteUser(56);
//    }
//
//    @Test
//    public void testUpdate(){
//        User user=new User();
//        user.setId(49);
//        user.setUsername("小于");
//        user.setAddress("西安市");
//        user.setSex("n");
//        user.setBirthday(new Date());
//        userDao.updateUser(user);
//    }
        public static void main(String[] args) throws IOException {
            //1.读取配置文件
            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
            //2.创建一个SqlSessionFactory工厂
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            //3.创建一个SqlSession对象
            SqlSession sqlSession = sqlSessionFactory.openSession();
            //4.使用SqlSession创建Dao接口的代理对象
            IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
            //5.使用代理对象执行方法
            List<User> users = iUserDao.findAll();
            for(User user : users){
                System.out.println(user);
            }
            //6.释放资源
            sqlSession.close();
            in.close();

        }
}
