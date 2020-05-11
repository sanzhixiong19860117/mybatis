import com.joy.dao.IEmpDao;
import com.joy.dao.bean.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author joy
 * @version 1.0
 * @date 2020/5/11 18:25
 */
public class Test {
    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = null;
            try {
                session = sqlSessionFactory.openSession();
                IEmpDao mapper = session.getMapper(IEmpDao.class);
                Emp blog = mapper.selectEmpByEmpno(1);
                System.out.println(blog);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                session.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void test1() {

    }
}
