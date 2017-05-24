package test;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

public class Test{

    @Autowired
    private static CacheManager cacheManager;

    public static void main(String[] args) {

        Cache cache = cacheManager.getCache("permissionCache");

        int size = cache.getSize();
        System.out.println("size = " + size);


    }

}
