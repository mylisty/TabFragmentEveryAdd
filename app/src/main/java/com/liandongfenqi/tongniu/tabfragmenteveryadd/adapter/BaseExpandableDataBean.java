package com.liandongfenqi.tongniu.tabfragmenteveryadd.adapter;

import java.io.Serializable;
import java.util.List;

/**
 * <br>
 * //   When I wrote this, only God and I understood what I was doing<br>
 * //   Now, God only knows<br>
 * //   作者： Anh Lai/竹井詩織里<br>
 * //   邮箱：ymback@sayyoulove.me<br>
 * //   创建时间：2017-07-07 12:43<br>
 * //   这玩意的用处：<br>
 */
public class BaseExpandableDataBean<G, C> implements Serializable {
    public G groupObject;
    public List<C> childList;
}
