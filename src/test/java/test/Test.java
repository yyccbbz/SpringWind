package test;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Test{

    public static CacheManager cacheManager = CacheManager.create();

    public static void main(String[] args) {

        // Create a cache manager
//        CacheManager cacheManager = new CacheManager();

        // create the cache called "helloworld"
        Cache cache = cacheManager.getCache("helloworld");

        // create a key to map the data to
        String key = "greeting";

        // Create a data element
        Element putGreeting = new Element(key, "Hello, World!");

        // Put the element into the data store
        cache.put(putGreeting);

        // Retrieve the data element
        Element getGreeting = cache.get(key);

        // Print the value
        System.out.println(getGreeting.getObjectValue());
    }

}
