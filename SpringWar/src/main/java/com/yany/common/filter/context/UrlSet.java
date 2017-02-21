package com.yany.common.filter.context;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yanyong on 2017/2/20.
 */
public class UrlSet {

    public final static String WHITETAG = "WHITE";

    private UrlSet() {
    }

    private final static UrlSet URL_SET = new UrlSet();

    private Set<String> whilteUrlSet = new HashSet<>();

    public static UrlSet getInstance() {
        return URL_SET;
    }

    public Set<String> getWhilteUrlSet() {
        return whilteUrlSet;
    }

}
