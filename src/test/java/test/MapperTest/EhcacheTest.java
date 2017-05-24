package test.MapperTest;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017/5/24
 * @Time: 12:54
 * @Description: ehcache 测试
 * <p>
 * Element：     缓存的元素，它维护着一个键值对。
 * Cache：       它是Ehcache的核心类，它有多个 Element ，并被 CacheManager 管理。它实现了对缓存的逻辑行为。
 * CacheManager：Cache 的容器对象，并管理着 Cache 的生命周期。
 */
public class EhcacheTest {

    private static final Logger log = LoggerFactory.getLogger(EhcacheTest.class);

    private static CacheManager manager = CacheManager.create();

    @Test
    public void test1() {
        // Create a cache manager
        // CacheManager manager = new CacheManager();

        // create the cache called "helloworld"
        Cache cache = manager.getCache("helloworld");

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

    /**
     * 创建CacheManager
     *
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        // 使用Ehcache默认配置获取单例的CacheManager实例
        CacheManager manager3 = CacheManager.create();
        String[] cacheNames3 = CacheManager.getInstance().getCacheNames();
        System.out.println("cacheNames3 = " + cacheNames3);

        /*// 使用Ehcache默认配置新建一个CacheManager实例
        CacheManager manager4 = CacheManager.newInstance();
        String[] cacheNames4 = manager.getCacheNames();
        System.out.println("cacheNames4 = " + cacheNames4);

        // 使用不同的配置文件分别创建一个CacheManager实例
        CacheManager manager1 = CacheManager.newInstance("src/config/ehcache1.xml");
        CacheManager manager2 = CacheManager.newInstance("src/config/ehcache2.xml");
        String[] cacheNamesForManager1 = manager1.getCacheNames();
        String[] cacheNamesForManager2 = manager2.getCacheNames();
        System.out.println("cacheNamesForManager1 = " + cacheNamesForManager1);
        System.out.println("cacheNamesForManager2 = " + cacheNamesForManager2);

        // 基于classpath下的配置文件创建CacheManager实例
        URL url = getClass().getResource("/anotherconfigurationname.xml");
        CacheManager manager5 = CacheManager.newInstance(url);

        // 基于文件流得到配置文件，并创建CacheManager实例
        InputStream fis = new FileInputStream(new File("src/config/ehcache.xml").getAbsolutePath());
        try {
            CacheManager manager6 = CacheManager.newInstance(fis);
        } finally {
            fis.close();
        }*/
    }

    /**
     * 添加缓存
     * Cache 对象在用 addCache 方法添加到 CacheManager 之前，是无效的。
     */
    @Test
    public void test3() {

        // 除了可以使用xml文件中配置的缓存，你也可以使用API动态增删缓存
        // 添加缓存
        /*manager.addCache(cacheName);

        // 使用默认配置添加缓存
        CacheManager manager1 = CacheManager.create();
        manager1.addCache("testCache");
        Cache test1 = manager1.getCache("testCache");

        // 使用自定义配置添加缓存，注意缓存未添加进CacheManager之前并不可用
        CacheManager manager2 = CacheManager.create();
        Cache memoryOnlyCache = new Cache("testCache", 5000, false, false, 5, 2);
        manager2.addCache(memoryOnlyCache);
        Cache test2 = manager2.getCache("testCache");

        // 使用特定的配置添加缓存
        CacheManager cacheManager = CacheManager.create();
        Cache testCache = new Cache(
                new CacheConfiguration("testCache", maxEntriesLocalHeap)
                        .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)
                        .eternal(false)
                        .timeToLiveSeconds(60)
                        .timeToIdleSeconds(30)
                        .diskExpiryThreadIntervalSeconds(0)
                        .persistence(new PersistenceConfiguration()
                            .strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP)
                        ));
        cacheManager.addCache(testCache);
        */
    }

    /**
     * 删除缓存
     */
    @Test
    public void test4() {
        CacheManager singletonManager = CacheManager.create();
        singletonManager.removeCache("sampleCache1");
    }

    /**
     * 实现基本缓存操作
     */
    public static void main(String[] args) {

        CacheManager manager = CacheManager.newInstance("src/test/resources/ehcache.xml");

        // 获得Cache的引用
        Cache cache = manager.getCache("userCache");

        // 将一个Element添加到Cache
        cache.put(new Element("key1", "value1"));

        // 获取Element，Element类支持序列化，所以下面两种方法都可以用
        Element element1 = cache.get("key1");
        // 获取非序列化的值
        log.debug("key:{}, value:{}", element1.getObjectKey(), element1.getObjectValue());
        // 获取序列化的值
        log.debug("key:{}, value:{}", element1.getKey(), element1.getValue());

        System.err.println("key:{}, value:{}" + "---" + element1.getObjectKey() + ":" + element1.getObjectValue());
        System.err.println("key:{}, value:{}" + "---" + element1.getKey() + ":" + element1.getValue());

        // 更新Cache中的Element
        cache.put(new Element("key1", "value2"));
        Element element2 = cache.get("key1");
        log.debug("key:{}, value:{}", element2.getObjectKey(), element2.getObjectValue());

        System.err.println("key:{}, value:{}" + "---" + element2.getObjectKey() + ":" + element2.getObjectValue());


        // 获取Cache的元素数
        log.debug("cache size:{}", cache.getSize());

        System.err.println("cache size:{} = "+ cache.getSize());

        // 获取MemoryStore的元素数
        log.debug("MemoryStoreSize:{}", cache.getMemoryStoreSize());

        // 获取DiskStore的元素数
        log.debug("DiskStoreSize:{}", cache.getDiskStoreSize());

        // 移除Element
        cache.remove("key1");
        log.debug("cache size:{}", cache.getSize());

        // 关闭当前CacheManager对象
        manager.shutdown();

        // 关闭CacheManager单例实例
        CacheManager.getInstance().shutdown();

    }

    @Test
    public void test6() {

        Cache cache = manager.getCache("permissionCache");

        String name = cache.getName();
        System.out.println("name = " + name);

    }

    @Test
    public void test7() {
    }

    @Test
    public void test8() {
    }


}
