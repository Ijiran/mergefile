package top.pxyz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Ijiran
 * @Package top.pxyz
 * @date 2019-11-07 21:56
 */
@SpringBootApplication
@MapperScan("top.pxyz.*.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
