package io.ab.shop.utils.test;

import io.ab.shop.utils.id.SnowFlakeFactory;
import io.ab.shop.utils.id.SnowFlakeLoader;
import org.junit.Test;

public class SnowFlakeFactoryTest {

    @Test
    public void testCreateDefaultID(){
        for(int i = 0; i < 100; i++){
            System.out.println(SnowFlakeFactory.getSnowFlakeFromCache().nextId());
        }
    }

    @Test
    public void testCreateID(){
        for(int i = 0; i < 100; i++){
            System.out.println(SnowFlakeFactory.getSnowFlakeByDataCenterIdAndMachineIdFromCache(SnowFlakeLoader.getDataCenterId(), SnowFlakeLoader.getDataCenterId()).nextId());
        }
    }

}
