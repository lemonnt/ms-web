package com.lemonnt.ms.lsf.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import org.apache.log4j.Logger;
import com.google.common.cache.*;

public class Caches<K, V> {

	private static Logger logger = Logger.getLogger(Caches.class);

	private LoadingCache<K, V> loadingCache;

	private int cacheMaxSize;

	private int expireTime;
	
	private Cache<K, V> cache;

	public Caches() {
		this.cacheMaxSize = 100;
		this.expireTime = 30*60*1000;
		this.cache = createCache();

	}

	/**
	 * @param cacheMaxSize <unit></unit>
	 * @param expireTime <unit>seconds</unit>
	 * @param refreshTime <unit>seconds</unit>
	 */
	public Caches(int cacheMaxSize, int expireTime) {
		this.cacheMaxSize = cacheMaxSize;
		this.expireTime = expireTime;
		this.cache = createCache();
	}

	public Cache<K, V> createCache() {
		Cache<K, V> cache = CacheBuilder.newBuilder().maximumSize(cacheMaxSize)
				.expireAfterWrite(expireTime, TimeUnit.SECONDS).build();
		return cache;
	}
	
	public void put(K k,V v){    
	    this.cache.put(k, v);
	}
	
	public Cache<K,V> cache(){
	    return this.cache;
	}
	public List<V> getAll(List<K> k){
		List<V> result = new ArrayList<V>();
		for(int i = k.size()-1;i >= 0;i--){
			V v = get(k.get(i));
			if(v != null){
				result.add(v);
				k.remove(i);
			}
			
		}
		return result;
		
	}
	
	public void invalidate(K k){
		if(null != cache && null != k)
			this.cache.invalidate(k);
	}
	
	
	public V get(K k){
		if(null == cache)
			return null;
		V v = cache.getIfPresent(k);
		//cache.invalidate(k);
		if(null == v)
			logger.warn("There is no value from the key { " + k + " }");
		return v;
			
	}
	
	public V gets(K k){
		if(null == cache)
			return null;
		V v = cache.getIfPresent(k);
//		if(null == v)
//			logger.warn("There is no value from the key { " + k + " }");
		return v;
			
	}

	public LoadingCache<K, V> cache(final K k, final V v) throws Exception {
		loadingCache = cached(new CacheLoader<K, V>() {
			@Override
			public V load(K key) throws Exception {
				return v;
			}
		});
		return loadingCache;
	}

	private LoadingCache<K, V> cached(CacheLoader<K, V> cacheLoader) {
		LoadingCache<K, V> cache = CacheBuilder.newBuilder().maximumSize(cacheMaxSize).weakKeys().softValues()
				.expireAfterWrite(expireTime, TimeUnit.SECONDS).removalListener(new RemovalListener<K, V>() {
					@Override
					public void onRemoval(RemovalNotification<K, V> rn) {
						logger.info("The " + rn.getKey()
								+ " was remove ,because the numbers in the cache were to maxValue");
					}
				}).build(cacheLoader);
		return cache;
	}

	public static void main(String[] args) throws Exception {
	    Caches<String, String> houndCache = new Caches<String, String>(10,10);
		//Cache<String, String> cache = houndCache.createCache();		
	    houndCache.put("test", "test");
		long time = System.currentTimeMillis();
	    while(true){
	    	
	    	System.out.println(houndCache.gets("test")+" : time -> "+(System.currentTimeMillis()-time));
	    	Thread.sleep(1000);
	    }
	    


	}
}
