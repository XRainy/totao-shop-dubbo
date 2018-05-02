import com.taotao.common.utils.JsonUtils;
import com.taotao.dubbo.service.ContentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * @author : dx
 * @date : 2017/12/5
 * Description :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})//,"classpath:spring-config-mvc.xml"
public class DubboTest {
    @Autowired
    ContentService contentService;
    @Test
    public  void testDubbo(){
        System.out.println(1);
        System.out.println(1);

        System.out.println(1);
        try{
            System.out.println(JsonUtils.objectToJson(contentService.getContentList(89)));
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
