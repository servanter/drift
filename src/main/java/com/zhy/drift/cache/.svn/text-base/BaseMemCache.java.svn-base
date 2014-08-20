package com.zhy.drift.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.zhy.drift.cache.bean.BottleCache;

public class BaseMemCache {

    /**
     * key=id
     */
    public static Map<String, BottleCache> bottleIdCache = Collections.synchronizedMap(new HashMap<String, BottleCache>());

    public static final String bottleIdCacheKey = "B.";

    //public static Map<String, LinkedList<Response>> responseCache = new HashMap<String, LinkedList<Response>>();

    public static final String responseCacheKeyWord = "L.";

    public synchronized static void setBottleId(String key, Long bottleId) {
        bottleIdCache.put(bottleIdCacheKey + key, new BottleCache(bottleId));
    }

    public synchronized static Long getBottleId(String key) {
    	BottleCache lastBottle = bottleIdCache.get(bottleIdCacheKey + key);
    	if(lastBottle != null && !lastBottle.isOver()){
    		return lastBottle.getBottleId();
    	}
        return null;
    }

//    public synchronized static LinkedList<Response> putResponseInfo(String key, Response response) {
//        LinkedList<Response> allLastResponse = new  LinkedList<Response>();
//        if (responseCache.get(responseCacheKeyWord + key) != null) {
//            allLastResponse = responseCache.get(responseCacheKeyWord + key);
//        }
//        allLastResponse.add(response);
//        responseCache.put(responseCacheKeyWord + key, allLastResponse);
//        return (LinkedList<Response>) allLastResponse;
//    }
    
//    public synchronized static Response getLastResponse(String key) {
//        if (responseCache.get(responseCacheKeyWord + key) == null) {
//            return null;
//        }
//        return responseCache.get(responseCacheKeyWord + key).getLast();
//    }
    
//    public static void weatherClear(){
//        bottleIdCache.clear();
//    }
}
