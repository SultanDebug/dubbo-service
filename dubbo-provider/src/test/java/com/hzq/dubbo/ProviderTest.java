package com.hzq.dubbo;

import static org.junit.Assert.assertTrue;

import com.hzq.dubbo.spi.Person;
import org.junit.Test;

import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Unit test for simple App.
 */
/*@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubboProviderApp.class)
@WebAppConfiguration*/
//@Ignore
public class ProviderTest
{


    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testSpi(){
        ServiceLoader<Person> load = ServiceLoader.load(Person.class);
        AtomicInteger i = new AtomicInteger(1);
        load.forEach(o->{

            System.out.println(o.getMsg(i.get()+""));
            i.getAndIncrement();
        });
    }

}
